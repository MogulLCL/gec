package com.mogul.gec.service.impl;

import com.mogul.gec.dao.DeptDao;
import com.mogul.gec.dao.impl.DeptDaoImpl;
import com.mogul.gec.pojo.DeptInf;
import com.mogul.gec.pojo.DocumentInf;
import com.mogul.gec.service.DeptService;
import com.mogul.gec.utils.Page;
import com.mogul.gec.vo.QueryVo;

import java.util.List;

public class DeptServiceImpl implements DeptService {

    private DeptDao deptDao=new DeptDaoImpl();

    @Override
    public Page getDeptList(QueryVo queryVo) {
        int count = deptDao.count(queryVo);
        Page page=new Page(null,count,queryVo.getSize(),queryVo.getLimit());
        queryVo.setLimit((queryVo.getLimit()-1)*queryVo.getSize());
        List<DeptInf> deptInfs=deptDao.getList(queryVo);
        page.setList(deptInfs);
        return page;
    }

    @Override
    public void addDept(DeptInf deptInf) {
        deptDao.insert(deptInf);
    }

    @Override
    public void deleteDept(long id) {
        deptDao.delete(id);
    }

    @Override
    public DeptInf getDept(long id) {
        return deptDao.get(id);
    }

    @Override
    public void updateDept(DeptInf deptInf) {
        deptDao.update(deptInf);
    }
}
