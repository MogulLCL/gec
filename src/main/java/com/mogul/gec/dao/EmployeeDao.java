package com.mogul.gec.dao;

import com.mogul.gec.pojo.EmployeeInf;
import com.mogul.gec.vo.EmployeeVo;
import com.mogul.gec.vo.QueryVo;

import java.util.List;

public interface EmployeeDao {
    List<EmployeeInf> getList(EmployeeVo employeeVo);

    int count(EmployeeVo employeeVo);

    boolean insert(EmployeeInf employeeInf);

    void delete(long id);

    EmployeeInf get(long id);

    void update(EmployeeInf employeeInf);
}
