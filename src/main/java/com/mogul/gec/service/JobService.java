package com.mogul.gec.service;

import com.mogul.gec.pojo.JobInf;
import com.mogul.gec.utils.Page;
import com.mogul.gec.vo.QueryVo;

public interface JobService {
    Page getJobList(QueryVo queryVo);

    void addJob(JobInf jobInf);

    void deleteJob(long id);

    JobInf getJob(long id);

    void updateJob(JobInf jobInf);
}
