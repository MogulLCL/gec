package com.mogul.gec.dao.impl;

import com.mogul.gec.dao.JobDao;
import com.mogul.gec.pojo.DeptInf;
import com.mogul.gec.pojo.JobInf;
import com.mogul.gec.utils.JdbcUtils;
import com.mogul.gec.vo.QueryVo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class JobDaoImpl implements JobDao {
    @Override
    public List<JobInf> getList(QueryVo queryVo) {
        List<JobInf> jobInfs=null;
        String sql="SELECT * FROM job_inf WHERE NAME Like concat('%',?,'%') LIMIT ?,?";
        try (Connection connection= JdbcUtils.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,queryVo.getKeyWords());
            preparedStatement.setInt(2,queryVo.getLimit());
            preparedStatement.setInt(3,queryVo.getSize());
            ResultSet resultSet = preparedStatement.executeQuery();
            jobInfs=new ArrayList<>();
            while (resultSet.next()){
                JobInf jobInf=JobInf.builder()
                        .id(resultSet.getLong(1))
                        .name(resultSet.getString(2))
                        .remark(resultSet.getString(3))
                        .build();
                jobInfs.add(jobInf);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return jobInfs;
    }

    @Override
    public int count(QueryVo queryVo) {
        String sql="SELECT count(id) FROM job_inf WHERE NAME Like concat('%',?,'%')";
        try (Connection connection=JdbcUtils.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,queryVo.getKeyWords());
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
                return resultSet.getInt(1);
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void insert(JobInf jobInf) {
        String sql="INSERT INTO job_inf (NAME,REMARK) values (?,?)";
        try(Connection connection=JdbcUtils.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,jobInf.getName());
            preparedStatement.setString(2,jobInf.getRemark());
            preparedStatement.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(long id) {
        String sql="DELETE FROM job_inf WHERE id = ?";
        try(Connection connection=JdbcUtils.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,id);
            preparedStatement.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public JobInf get(long id) {
        JobInf jobInf=null;
        String sql="SELECT * FROM job_inf WHERE id = ?";
        try(Connection connection=JdbcUtils.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                jobInf=JobInf.builder()
                        .id(resultSet.getLong(1))
                        .name(resultSet.getString(2))
                        .remark(resultSet.getString(3))
                        .build();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return jobInf;
    }

    @Override
    public List<JobInf> list() {
        List<JobInf> jobInfs=null;
        String sql="SELECT ID,NAME FROM job_inf";
        try (Connection connection= JdbcUtils.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            jobInfs=new ArrayList<>();
            while (resultSet.next()){
                JobInf jobInf=JobInf.builder()
                        .id(resultSet.getLong(1))
                        .name(resultSet.getString(2))
                        .build();
                jobInfs.add(jobInf);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return jobInfs;
    }

    @Override
    public void update(JobInf jobInf) {
        String sql="UPDATE job_inf SET NAME = ?,REMARK = ? WHERE id = ?";
        try(Connection connection=JdbcUtils.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,jobInf.getName());
            preparedStatement.setString(2,jobInf.getRemark());
            preparedStatement.setLong(3,jobInf.getId());
            preparedStatement.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
