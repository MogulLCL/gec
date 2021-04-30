package com.mogul.gec.service.impl;

import com.mogul.gec.dao.DeptDao;
import com.mogul.gec.dao.EmployeeDao;
import com.mogul.gec.dao.JobDao;
import com.mogul.gec.dao.impl.DeptDaoImpl;
import com.mogul.gec.dao.impl.EmployeeDaoImpl;
import com.mogul.gec.dao.impl.JobDaoImpl;
import com.mogul.gec.pojo.DeptInf;
import com.mogul.gec.pojo.EmployeeInf;
import com.mogul.gec.pojo.JobInf;
import com.mogul.gec.service.EmployeeService;
import com.mogul.gec.utils.Page;
import com.mogul.gec.vo.EmployeeVo;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDao employeeDao=new EmployeeDaoImpl();
    private DeptDao deptDao=new DeptDaoImpl();
    private JobDao jobDao=new JobDaoImpl();

    @Override
    public Page getEmployeeList(EmployeeVo employeeVo) {
        int count = employeeDao.count(employeeVo);
        Page page=new Page(null,count,employeeVo.getSize(),employeeVo.getLimit());
        employeeVo.setLimit((employeeVo.getLimit()-1)*employeeVo.getSize());
        List<EmployeeInf> employeeInfs=employeeDao.getList(employeeVo);
        page.setList(employeeInfs);
        return page;
    }

    @Override
    public List<DeptInf> getDepts() {
        return deptDao.list();
    }

    @Override
    public List<JobInf> getJobs() {
        return jobDao.list();
    }

    @Override
    public void addEmployee(EmployeeInf employeeInf) {
        employeeDao.insert(employeeInf);
    }

    @Override
    public void deleteEmployee(long id) {
        employeeDao.delete(id);
    }

    @Override
    public EmployeeInf getEmployee(long id) {
        return employeeDao.get(id);
    }

    @Override
    public void updateEmployee(EmployeeInf employeeInf) {
        employeeDao.update(employeeInf);
    }
}
