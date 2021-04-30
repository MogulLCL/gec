package com.mogul.gec.dao.impl;

import com.mogul.gec.dao.DocumentDao;
import com.mogul.gec.pojo.DocumentInf;
import com.mogul.gec.utils.JdbcUtils;
import com.mogul.gec.vo.QueryVo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DocumentDaoImpl implements DocumentDao {

    @Override
    public List<DocumentInf> getList(QueryVo documentVo) {
        List<DocumentInf> documentInfs=null;
        String sql="SELECT document_inf.ID,\n" +
                "document_inf.filename,\n" +
                "document_inf.REMARK,\n" +
                "document_inf.CREATE_DATE,\n" +
                "(SELECT username FROM user_inf WHERE document_inf.USER_ID = user_inf.ID) USERNAME FROM document_inf WHERE filename Like concat('%',?,'%') LIMIT ?,?";
        try (Connection connection=JdbcUtils.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,documentVo.getKeyWords());
            preparedStatement.setInt(2,documentVo.getLimit());
            preparedStatement.setInt(3,documentVo.getSize());
            ResultSet resultSet = preparedStatement.executeQuery();
            documentInfs=new ArrayList<>();
            while (resultSet.next()){
                DocumentInf documentInf=new DocumentInf();
                documentInf.setId(resultSet.getInt(1));
                documentInf.setFilename(resultSet.getString(2));
                documentInf.setRemark(resultSet.getString(3));
                documentInf.setCreateDate(resultSet.getDate(4));
                documentInf.setUsername(resultSet.getString(5));
                documentInfs.add(documentInf);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return documentInfs;
    }

    @Override
    public int count(QueryVo documentVo) {
        String sql="SELECT count(id) FROM document_inf WHERE filename Like concat('%',?,'%')";
        try (Connection connection=JdbcUtils.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,documentVo.getKeyWords());
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
                return resultSet.getInt(1);
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void insert(DocumentInf documentInf) {
        String sql="INSERT INTO document_inf (filename,REMARK,CREATE_DATE,USER_ID) values (?,?,?,?)";
        try(Connection connection=JdbcUtils.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,documentInf.getFilename());
            preparedStatement.setString(2,documentInf.getRemark());
            preparedStatement.setDate(3,new Date(new java.util.Date().getTime()));
            preparedStatement.setLong(4,documentInf.getUserId());
            preparedStatement.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(long id) {
        String sql="DELETE FROM document_inf WHERE id = ?";
        try(Connection connection=JdbcUtils.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,id);
            preparedStatement.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public DocumentInf get(long id) {
        DocumentInf documentInf=null;
        String sql="SELECT * FROM document_inf WHERE id = ?";
        try(Connection connection=JdbcUtils.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                documentInf = new DocumentInf();
                documentInf.setId(resultSet.getLong(1));
                documentInf.setFilename(resultSet.getString(2));
                documentInf.setRemark(resultSet.getString(3));
                documentInf.setCreateDate(resultSet.getDate(4));
                documentInf.setUserId(resultSet.getLong(5));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return documentInf;
    }

    @Override
    public void update(DocumentInf documentInf) {
        String sql="UPDATE document_inf SET filename = ?,REMARK = ?,CREATE_DATE = ?,USER_ID = ? WHERE id = ?";
        try(Connection connection=JdbcUtils.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,documentInf.getFilename());
            preparedStatement.setString(2,documentInf.getRemark());
            preparedStatement.setDate(3,new Date(new java.util.Date().getTime()));
            preparedStatement.setLong(4,documentInf.getUserId());
            preparedStatement.setLong(5,documentInf.getId());
            preparedStatement.execute();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
