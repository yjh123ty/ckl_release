/**
 * @Title: UserServiceImpl.java
 * @Package tech.youmu.ckl.service.impl
 * Copyright: Copyright (c) 2016
 * Company:成都友木科技有限公司
 * 
 * @author youmu-yjh
 * @date 2016年8月2日 上午10:58:35
 * @version V1.0
 */

package tech.youmu.ckl.service.impl;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.youmu.ckl.constants.StatusConst;
import tech.youmu.ckl.domain.AdminTask;
import tech.youmu.ckl.domain.Employee;
import tech.youmu.ckl.domain.Sos;
import tech.youmu.ckl.domain.User;
import tech.youmu.ckl.exception.BizExecption;
import tech.youmu.ckl.mapper.AdminTaskMapper;
import tech.youmu.ckl.mapper.PhoneMessageMapper;
import tech.youmu.ckl.mapper.SosMapper;
import tech.youmu.ckl.mapper.UserMapper;
import tech.youmu.ckl.query.BaseQuery;
import tech.youmu.ckl.query.PageList;
import tech.youmu.ckl.service.IAdminService;
import tech.youmu.ckl.service.IAdminTaskService;
import tech.youmu.ckl.service.ISosService;
import tech.youmu.ckl.utils.ConfigUtil;
import tech.youmu.ckl.utils.SendSmsUtil;
import tech.youmu.ckl.utils.UserContext;

/**
 * 
 * <p>Title:SosServiceImpl</p>
 * @author xiongchuan
 * @version	v1.0
 * <p>Date:2016年10月19日下午5:14:59</p>
 * <p>Description:TODO</p>
 */
@Service
public class SosServiceImpl extends BaseServiceImpl<Sos> implements ISosService {
    
    private SosMapper sosMapper;
    
    @Autowired
    private IAdminTaskService adminTaskService;
    
	/**
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年10月19日下午6:24:51;</p>
     *	<p>Description: TODO;</p>
     *  @param TODO 
     *  @throws	TODO
     */
    @Autowired
    public SosServiceImpl(SosMapper sosMapper) {
        super(sosMapper);
        this.sosMapper= sosMapper;
    }

    @Autowired
	private UserMapper userMapper;
	
	@Autowired
    private PhoneMessageMapper phoneMessageMapper;


    @Override
    public void sos(Long userId, String lng, String lat, String message) {
        User user = userMapper.getById(userId);
        List<String> phones = phoneMessageMapper.findPhone();
        StringBuffer buffer = new StringBuffer();
        for(String phone:phones){
            buffer.append(phone).append(",");
        }
        JSONObject json = new JSONObject();
        json.put("name", user.getRealName());
        json.put("phone", user.getMobile());
        String sex ="未知";
        if("1".equals(user.getSex())){
            sex="女";
        }else if("2".equals(user.getSex())){
            sex="男";
        }
        json.put("sex", sex);
        json.put("coordinate",user.getLongtitude()+","+user.getLatitude());
        json.put("message", message);
        if(buffer.length()>0){
            SendSmsUtil.sendMessage(buffer.toString(),json.toString(),ConfigUtil.getSMSTemplateSos());
        }
        sosMapper.save(new Sos(userId,lng,lat,message,StatusConst.FALSE));
        AdminTask task = new AdminTask(AdminTask.SOS_HANDLE_URL,message,StatusConst.ONE);
        adminTaskService.save(task);
        adminTaskService.push(task);
    }


    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.ISosService#handleById(java.lang.Long)
     */
    @Override
    public Boolean handleById(Long id) {
        Sos sos = sosMapper.getById(id);
        if(sos==null){
            throw new BizExecption("未找到该求助！");
        }
        
        Employee employee = UserContext.getUser();
        if(employee==null){
            throw new BizExecption("请先登陆！");
        }
        
        if(sos.getIsDel()){
            throw new BizExecption("已经处理！");
        }
        sos.setIsHandle(true);
        sos.setEmployee(employee);
        sos.setEmpId(employee.getId());
        sosMapper.update(sos);
        return true;
    }


    /* (non-Javadoc)
     * @see tech.youmu.ckl.service.ISosService#getByEmployeeIsNull(tech.youmu.ckl.query.BaseQuery)
     */
    @Override
    public PageList<Sos> getByEmployeeIsNull(BaseQuery query) {
        List<Sos> list = sosMapper.getByEmployeeIsNull(query);
        long count = sosMapper.getCountByEmployeeIsNull(query);
        PageList<Sos> pageList = new PageList<Sos>();
        pageList.setRows(list);
        pageList.setTotal(count);
        return pageList;
    }
}
