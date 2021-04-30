package com.mogul.gec.dao.impl;

import com.mogul.gec.dao.DeptDao;
import com.mogul.gec.pojo.DeptInf;
import com.mogul.gec.pojo.DocumentInf;
import com.mogul.gec.utils.JdbcUtils;
import com.mogul.gec.vo.QueryVo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DeptDaoImpl implements DeptDao {
    @Override
    public List<DeptInf> getList(QueryVo queryVo) {
        List<DeptInf> deptInfs=null;
        String sql="SELECT * FROM dept_inf WHERE NAME Like concat('%',?,'%') LIMIT ?,?";
        try (Connection connection= JdbcUtils.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,queryVo.getKeyWords());
            preparedStatement.setInt(2,queryVo.getLimit());
            preparedStatement.setInt(3,queryVo.getSize());
            ResultSet resultSet = preparedStatement.executeQuery();
            deptInfs=new ArrayList<>();
            while (resultSet.next()){
                DeptInf deptInf=DeptInf.builder()
                        .id(resultSet.getLong(1))
                        .name(resultSet.getString(2))
                        .remark(resultSet.getString(3))
                        .build();
                deptInfs.add(deptInf);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return deptInfs;
    }

    @Override
    public List<DeptInf> list() {
        List<DeptInf> deptInfs=null;
        String sql="SELECT ID,NAME FROM dept_inf";
        try (Connection connection= JdbcUtils.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            deptInfs=new ArrayList<>();
            while (resultSet.next()){
                DeptInf deptInf=DeptInf.builder()
                        .id(resultSet.getLong(1))
                        .name(resultSet.getString(2))
                        .build();
                deptInfs.add(deptInf);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return deptInfs;
    }

    @Override
    public int count(QueryVo queryVo) {
        String sql="SELECT count(id) FROM dept_inf WHERE NAME Like concat('%',?,'%')";
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
    public void insert(DeptInf deptInf) {
        String sql="INSERT INTO dept_inf (NAME,REMARK) values (?,?)";
        try(Connection connection=JdbcUtils.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,deptInf.getName());
            preparedStatement.setString(2,deptInf.getRemark());
            preparedStatement.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(long id) {
        String sql="DELETE FROM dept_inf WHERE id = ?";
        try(Connection connection=JdbcUtils.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,id);
            preparedStatement.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public DeptInf get(long id) {
        DeptInf deptInf=null;
        String sql="SELECT * FROM dept_inf WHERE id = ?";
        try(Connection connection=JdbcUtils.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                deptInf=DeptInf.builder()
                        .id(resultSet.getLong(1))
                        .name(resultSet.getString(2))
                        .remark(resultSet.getString(3))
                        .build();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return deptInf;
    }

    @Override
    public void update(DeptInf deptInf) {
        String sql="UPDATE dept_inf SET NAME = ?,REMARK = ? WHERE id = ?";
        try(Connection connection=JdbcUtils.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,deptInf.getName());
            preparedStatement.setString(2,deptInf.getRemark());
            preparedStatement.setLong(3,deptInf.getId());
            preparedStatement.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
