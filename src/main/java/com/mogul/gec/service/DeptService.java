package com.mogul.gec.service;

import com.mogul.gec.pojo.DeptInf;
import com.mogul.gec.pojo.DocumentInf;
import com.mogul.gec.utils.Page;
import com.mogul.gec.vo.QueryVo;

public interface DeptService {
    Page getDeptList(QueryVo queryVo);

    void addDept(DeptInf deptInf);

    void deleteDept(long id);

    DeptInf getDept(long id);

    void updateDept(DeptInf deptInf);
}
