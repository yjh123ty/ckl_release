package tech.youmu.ckl.service.component;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import tech.youmu.ckl.domain.Employee;
import tech.youmu.ckl.mapper.EmployeeMapper;

@Component
public class EmployeeComponent {

    @Autowired
    private EmployeeMapper employeeMapper;
    
    /**
     * 
     *  <p>Author:xiongchuan;</p>
     *  <p>Date:2016年9月9日下午3:23:04;</p>
     *	<p>Description: 更新员工平均星数;</p>
     */
//    @Scheduled(fixedRate = 1000*60*60*24)//24小时执行一次
    @Scheduled(cron="0 0 23 * * ?")
    public void updateEmployeeAvgStar() {
        List<Employee> employees = employeeMapper.findAvgStarByOrder();
        employeeMapper.batchUpdateEmployeeAvgStar(employees);
    }
    
    
}
