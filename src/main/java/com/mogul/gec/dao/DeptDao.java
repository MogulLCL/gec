package com.mogul.gec.dao;

import com.mogul.gec.pojo.DeptInf;
import com.mogul.gec.pojo.DocumentInf;
import com.mogul.gec.vo.QueryVo;

import java.util.List;

public interface DeptDao {

    List<DeptInf> getList(QueryVo queryVo);

    int count(QueryVo queryVo);

    void insert(DeptInf deptInf);

    void delete(long id);

    DeptInf get(long id);

    void update(DeptInf deptInf);

    List<DeptInf> list();
}
