package com.mogul.gec.dao.impl;

import com.mogul.gec.dao.AnnouncementDao;
import com.mogul.gec.pojo.AnnouncementInfo;
import com.mogul.gec.utils.JdbcUtils;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AnnouncementDaoImpl implements AnnouncementDao {

    @Override
    public List<AnnouncementInfo> getAnnouncementInfos(int limit, int size) {
        List<AnnouncementInfo> announcementInfos=null;
        String sql="SELECT * FROM announcement_info LIMIT ?,?";
        try(Connection connection=JdbcUtils.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,limit);
            preparedStatement.setInt(2,size);
            ResultSet resultSet = preparedStatement.executeQuery();
            announcementInfos=new ArrayList<>();
            while (resultSet.next()) {
                AnnouncementInfo announcementInfo = new AnnouncementInfo();
                announcementInfo.setId(resultSet.getLong(1));
                announcementInfo.setTitle(resultSet.getString(2));
                announcementInfo.setContent(resultSet.getString(3));
                announcementInfo.setCreatetime(resultSet.getDate(4));
                announcementInfo.setUid(resultSet.getInt(5));
                announcementInfos.add(announcementInfo);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return announcementInfos;
    }

    @Override
    public int count() {
        String sql="SELECT count(id) FROM announcement_info";
        try (Connection connection=JdbcUtils.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
                return resultSet.getInt(1);
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void insert(AnnouncementInfo announcementInfo) {
        String sql="INSERT INTO announcement_info (title,content,createtime,uid) VALUES (?,?,?,?)";
        try(Connection connection=JdbcUtils.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,announcementInfo.getTitle());
            preparedStatement.setString(2,announcementInfo.getContent());
            preparedStatement.setDate(3,new Date(new java.util.Date().getTime()));
            preparedStatement.setLong(4,announcementInfo.getUid());
            preparedStatement.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(long id) {
        String sql="DELETE FROM announcement_info WHERE id = ?";
        try(Connection connection=JdbcUtils.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,id);
            preparedStatement.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public AnnouncementInfo get(long id) {
        AnnouncementInfo announcementInfo=null;
        String sql="SELECT * FROM announcement_info WHERE id = ?";
        try(Connection connection=JdbcUtils.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                announcementInfo=new AnnouncementInfo();
                announcementInfo.setId(resultSet.getLong(1));
                announcementInfo.setTitle(resultSet.getString(2));
                announcementInfo.setContent(resultSet.getString(3));
                announcementInfo.setCreatetime(resultSet.getDate(4));
                announcementInfo.setUid(resultSet.getInt(5));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return announcementInfo;
    }

    @Override
    public void update(AnnouncementInfo announcementInfo) {
        String sql="UPDATE announcement_info SET title = ?, content = ?, createtime = ?, uid = ? WHERE id = ?";
        try (Connection connection=JdbcUtils.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(5,announcementInfo.getId());
            preparedStatement.setString(1,announcementInfo.getTitle());
            preparedStatement.setString(2,announcementInfo.getContent());
            preparedStatement.setDate(3,new Date(new java.util.Date().getTime()));
            preparedStatement.setLong(4,announcementInfo.getUid());
            preparedStatement.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
