package com.mogul.gec.service;

import com.mogul.gec.pojo.DocumentInf;
import com.mogul.gec.utils.Page;
import com.mogul.gec.vo.QueryVo;

public interface DocumentService {

    Page getDocumentList(QueryVo documentVo);

    void addDocumentInfo(DocumentInf documentInf);

    void delete(long id);

    DocumentInf getDocumentInfo(long id);

    void updateDocumentInfo(DocumentInf documentInf);
}
