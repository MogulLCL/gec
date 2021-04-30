package com.mogul.gec.dao;

import com.mogul.gec.pojo.DeptInf;
import com.mogul.gec.pojo.JobInf;
import com.mogul.gec.vo.QueryVo;

import java.util.List;

public interface JobDao {

    List<JobInf> getList(QueryVo queryVo);

    int count(QueryVo queryVo);

    void insert(JobInf jobInf);

    void delete(long id);

    JobInf get(long id);

    void update(JobInf jobInf);

    List<JobInf> list();
}
