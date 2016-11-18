/**
 * @Title: IUserService.java
 * @Package tech.youmu.ckl.service
 * Copyright: Copyright (c) 2016
 * Company:成都友木科技有限公司
 * 
 * @author youmu-yjh
 * @date 2016年8月2日 上午10:57:38
 * @version V1.0
 */

package tech.youmu.ckl.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import tech.youmu.ckl.app.vo.RidersInfo;
import tech.youmu.ckl.app.vo.RongTokenInfo;
import tech.youmu.ckl.app.vo.UserInfo;
import tech.youmu.ckl.domain.Sos;
import tech.youmu.ckl.domain.User;
import tech.youmu.ckl.domain.UserCenter;
import tech.youmu.ckl.query.BaseQuery;
import tech.youmu.ckl.query.PageList;
import tech.youmu.ckl.query.UserQuery;

/**
 * 
 * <p>Title:ISosService</p>
 * @author xiongchuan
 * @version	v1.0
 * <p>Date:2016年10月19日下午5:14:08</p>
 * <p>Description:TODO</p>
 */
public interface ISosService extends IBaseService<Sos>{

    void sos(Long userId, String lng, String lat, String message);

    Boolean handleById(Long id);

    /**
     *  <p>Author:yjh;</p>
     *  <p>Date:2016年10月20日下午3:31:49;</p>
     *	<p>Description: TODO;</p>
     *  @param query
     *  @return
     */
    PageList<Sos> getByEmployeeIsNull(BaseQuery query);


}
