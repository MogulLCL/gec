package com.mogul.gec.dao.impl;

import com.mogul.gec.dao.EmployeeDao;
import com.mogul.gec.pojo.EmployeeInf;
import com.mogul.gec.pojo.JobInf;
import com.mogul.gec.utils.JdbcUtils;
import com.mogul.gec.vo.EmployeeVo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {
    @Override
    public List<EmployeeInf> getList(EmployeeVo employeeVo) {
        List<EmployeeInf> employeeInfs=null;
        String sql="SELECT\n" +
                "dept_inf.`NAME`,\n" +
                "job_inf.`NAME`,\n" +
                "employee_inf.ID,\n" +
                "employee_inf.`NAME`,\n" +
                "employee_inf.SEX,\n" +
                "employee_inf.PHONE,\n" +
                "employee_inf.EMAIL,\n" +
                "employee_inf.EDUCATION,\n" +
                "employee_inf.CREATE_DATE\n" +
                "FROM\n" +
                "employee_inf\n" +
                "LEFT JOIN dept_inf ON employee_inf.DEPT_ID = dept_inf.ID\n" +
                "LEFT JOIN job_inf ON employee_inf.JOB_ID = job_inf.ID\n" +
                "WHERE\n" +
                "employee_inf.`NAME` LIKE concat('%',?,'%') AND\n" +
                "employee_inf.DEPT_ID LIKE concat('%',?,'%')\n" +
                "LIMIT ?, ?";
        try (Connection connection= JdbcUtils.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,employeeVo.getName());
            preparedStatement.setString(2,employeeVo.getDeptId());
            preparedStatement.setInt(3,employeeVo.getLimit());
            preparedStatement.setInt(4,employeeVo.getSize());
            ResultSet resultSet = preparedStatement.executeQuery();
            employeeInfs=new ArrayList<>();
            while (resultSet.next()){
                EmployeeInf employeeInf=EmployeeInf.builder()
                        .deptName(resultSet.getString(1))
                        .jobName(resultSet.getString(2))
                        .id(resultSet.getLong(3))
                        .name(resultSet.getString(4))
                        .sex(resultSet.getLong(5))
                        .phone(resultSet.getString(6))
                        .email(resultSet.getString(7))
                        .education(resultSet.getString(8))
                        .createDate(resultSet.getDate(9))
                        .build();
                employeeInfs.add(employeeInf);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return employeeInfs;
    }

    @Override
    public int count(EmployeeVo employeeVo) {
        String sql="SELECT count(id) FROM employee_inf WHERE EMAIL Like concat('%',?,'%') AND DEPT_ID LIKE concat('%',?,'%')";
        try (Connection connection=JdbcUtils.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,employeeVo.getName());
            preparedStatement.setString(2,employeeVo.getDeptId());
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
                return resultSet.getInt(1);
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public boolean insert(EmployeeInf employeeInf) {
        String sql="INSERT INTO employee_inf (password,DEPT_ID,JOB_ID,NAME,CARD_ID,PHONE,EMAIL,SEX,PARTY,RACE,EDUCATION,CREATE_DATE,imgname) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try(Connection connection=JdbcUtils.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,employeeInf.getPassword());
            preparedStatement.setString(2,employeeInf.getDeptName());
            preparedStatement.setString(3,employeeInf.getJobName());
            preparedStatement.setString(4,employeeInf.getName());
            preparedStatement.setString(5,employeeInf.getCardId());
            preparedStatement.setString(6,employeeInf.getPhone());
            preparedStatement.setString(7,employeeInf.getEmail());
            preparedStatement.setLong(8,employeeInf.getSex());
            preparedStatement.setString(9,employeeInf.getParty());
            preparedStatement.setString(10,employeeInf.getRace());
            preparedStatement.setString(11,employeeInf.getEducation());
            preparedStatement.setDate(12,new Date(new java.util.Date().getTime()));
            preparedStatement.setString(13,employeeInf.getImgname());
            if(preparedStatement.executeUpdate()>0){
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void delete(long id) {
        String sql="DELETE FROM employee_inf WHERE id = ?";
        try(Connection connection=JdbcUtils.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,id);
            preparedStatement.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public EmployeeInf get(long id) {
        EmployeeInf employeeInf=null;
        String sql="SELECT\n" +
                "employee_inf.ID,\n" +
                "employee_inf.`NAME`,\n" +
                "employee_inf.PHONE,\n" +
                "employee_inf.EMAIL,\n" +
                "employee_inf.EDUCATION,\n" +
                "employee_inf.JOB_ID,\n" +
                "employee_inf.DEPT_ID\n" +
                "employee_inf.imgname\n" +
                "FROM\n" +
                "employee_inf\n" +
                "WHERE\n" +
                "employee_inf.ID = ?";
        try(Connection connection=JdbcUtils.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                employeeInf=EmployeeInf.builder()
                        .id(resultSet.getLong(1))
                        .name(resultSet.getString(2))
                        .phone(resultSet.getString(3))
                        .email(resultSet.getString(4))
                        .education(resultSet.getString(5))
                        .jobId(resultSet.getLong(6))
                        .imgname(resultSet.getString(7))
                        .deptId(resultSet.getLong(8))
                        .build();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return employeeInf;
    }

    @Override
    public void update(EmployeeInf employeeInf) {
        String sql="UPDATE employee_inf SET NAME = ?,PHONE = ?, DEPT_ID = ?, JOB_ID = ?, EMAIL = ?, EDUCATION = ? WHERE ID = ?";
        try(Connection connection=JdbcUtils.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,employeeInf.getName());
            preparedStatement.setString(2,employeeInf.getPhone());
            preparedStatement.setString(3,employeeInf.getDeptName());
            preparedStatement.setString(4,employeeInf.getJobName());
            preparedStatement.setString(5,employeeInf.getEmail());
            preparedStatement.setString(6,employeeInf.getEducation());
            preparedStatement.setLong(7,employeeInf.getId());
            preparedStatement.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
