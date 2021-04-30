package com.mogul.gec.service;

import com.mogul.gec.pojo.DeptInf;
import com.mogul.gec.pojo.EmployeeInf;
import com.mogul.gec.pojo.JobInf;
import com.mogul.gec.utils.Page;
import com.mogul.gec.vo.EmployeeVo;

import java.util.List;

public interface EmployeeService {
    Page getEmployeeList(EmployeeVo employeeVo);

    List<DeptInf> getDepts();

    List<JobInf> getJobs();

    void addEmployee(EmployeeInf employeeInf);

    void deleteEmployee(long id);

    EmployeeInf getEmployee(long id);

    void updateEmployee(EmployeeInf employeeInf);
}
