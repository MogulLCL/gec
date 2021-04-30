package com.mogul.gec.dao.impl;

import com.mogul.gec.dao.UserDao;
import com.mogul.gec.pojo.UserInf;
import com.mogul.gec.utils.JdbcUtils;
import com.mogul.gec.vo.UserVo;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDaoImpl implements UserDao {

    @Override
    public List<UserInf> userByList(UserVo userVo) {
        String sql="SELECT * FROM user_inf WHERE username LIKE concat('%',?,'%') AND status LIKE CONCAT('%',?,'%') LIMIT ?, ?";
        List<UserInf> users=null;
        try(Connection connection=JdbcUtils.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,userVo.getUsername());
            preparedStatement.setString(2,userVo.getStatus());
            preparedStatement.setInt(3,userVo.getLimit());
            preparedStatement.setInt(4,userVo.getSize());
            ResultSet resultSet = preparedStatement.executeQuery();
            users=new ArrayList<>();
            while (resultSet.next()){
                UserInf user=new UserInf();
                user.setId(resultSet.getInt(1));
                user.setLoginname(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
                user.setStatus(resultSet.getLong(4));
                Timestamp timestamp=resultSet.getTimestamp(5);
                user.setCreatedate(new Date(timestamp.getTime()));
                user.setUsername(resultSet.getString(6));
                user.setImgname(resultSet.getString(7));
                users.add(user);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public int count(UserVo userVo) {
        String sql="SELECT count(id) FROM user_inf WHERE username LIKE concat('%',?,'%') AND status LIKE CONCAT('%',?,'%')";
        try (Connection connection=JdbcUtils.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,userVo.getUsername());
            preparedStatement.setString(2,userVo.getStatus());
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
                return resultSet.getInt(1);
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int countUser(String username) {
        String sql="SELECT count(id) FROM user_inf WHERE loginname = ?";
        try (Connection connection=JdbcUtils.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
                return resultSet.getInt(1);
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public UserInf loginUser(String username) {
        UserInf user=null;
        String sql="SELECT * FROM user_inf WHERE loginname = ?";
        try (Connection connection=JdbcUtils.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                user=new UserInf();
                user.setId(resultSet.getInt(1));
                user.setLoginname(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
                user.setStatus(resultSet.getLong(4));
                Timestamp timestamp=resultSet.getTimestamp(5);
                user.setCreatedate(new Date(timestamp.getTime()));
                user.setUsername(resultSet.getString(6));
                user.setImgname(resultSet.getString(7));
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    @Override
    public void insert(UserInf userInf) {
        String sql="INSERT INTO user_inf (loginname,PASSWORD,STATUS,createdate,username,imgname) values (?,?,?,?,?,?)";
        try(Connection connection=JdbcUtils.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            if(countUser(userInf.getLoginname())>0)return;
            preparedStatement.setString(1,userInf.getLoginname());
            preparedStatement.setString(2,userInf.getPassword());
            preparedStatement.setLong(3,userInf.getStatus());
            preparedStatement.setTimestamp(4,new java.sql.Timestamp(new Date().getTime()));
            preparedStatement.setString(5,userInf.getUsername());
            preparedStatement.setString(6,userInf.getImgname());
            preparedStatement.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(long id) {
        String sql="DELETE FROM user_inf WHERE id = ?";
        try(Connection connection=JdbcUtils.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,id);
            preparedStatement.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public UserInf get(long id) {
        UserInf userInf=null;
        String sql="SELECT * FROM user_inf WHERE id = ?";
        try(Connection connection=JdbcUtils.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                userInf = new UserInf();
                userInf.setId(resultSet.getInt(1));
                userInf.setLoginname(resultSet.getString(2));
                userInf.setPassword(resultSet.getString(3));
                userInf.setStatus(resultSet.getLong(4));
                Timestamp timestamp=resultSet.getTimestamp(5);
                userInf.setCreatedate(new Date(timestamp.getTime()));
                userInf.setUsername(resultSet.getString(6));
                userInf.setImgname(resultSet.getString(7));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return userInf;
    }

    @Override
    public void update(UserInf userInf) {
        String sql="UPDATE user_inf SET loginname = ?,STATUS = ?,createdate = ?,username = ? WHERE id = ?";
        try(Connection connection=JdbcUtils.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            if(countUser(userInf.getLoginname())>0)return;
            preparedStatement.setString(1,userInf.getLoginname());
            preparedStatement.setLong(2,userInf.getStatus());
            preparedStatement.setDate(3,new java.sql.Date(new Date().getTime()));
            preparedStatement.setString(4,userInf.getUsername());
            preparedStatement.setLong(5,userInf.getId());
            preparedStatement.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
