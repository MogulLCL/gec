package com.mogul.gec.dao;

import com.mogul.gec.pojo.DocumentInf;
import com.mogul.gec.vo.QueryVo;

import java.util.List;

public interface DocumentDao {
    List<DocumentInf> getList(QueryVo queryVo);

    int count(QueryVo queryVo);

    void insert(DocumentInf documentInf);

    void delete(long id);

    DocumentInf get(long id);

    void update(DocumentInf documentInf);
}
