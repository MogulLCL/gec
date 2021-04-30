package com.mogul.gec.service.impl;

import com.mogul.gec.dao.JobDao;
import com.mogul.gec.dao.impl.JobDaoImpl;
import com.mogul.gec.pojo.DeptInf;
import com.mogul.gec.pojo.JobInf;
import com.mogul.gec.service.JobService;
import com.mogul.gec.utils.Page;
import com.mogul.gec.vo.QueryVo;

import java.util.List;

public class JobServiceImpl implements JobService {

    private JobDao jobDao=new JobDaoImpl();

    @Override
    public Page getJobList(QueryVo queryVo) {
        int count = jobDao.count(queryVo);
        Page page=new Page(null,count,queryVo.getSize(),queryVo.getLimit());
        queryVo.setLimit((queryVo.getLimit()-1)*queryVo.getSize());
        List<JobInf> jobInfs=jobDao.getList(queryVo);
        page.setList(jobInfs);
        return page;
    }

    @Override
    public void addJob(JobInf jobInf) {
        jobDao.insert(jobInf);
    }

    @Override
    public void deleteJob(long id) {
        jobDao.delete(id);
    }

    @Override
    public JobInf getJob(long id) {
        return jobDao.get(id);
    }

    @Override
    public void updateJob(JobInf jobInf) {
        jobDao.update(jobInf);
    }
}
