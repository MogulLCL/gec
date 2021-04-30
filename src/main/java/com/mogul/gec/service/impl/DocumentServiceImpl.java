package com.mogul.gec.service.impl;

import com.mogul.gec.dao.DocumentDao;
import com.mogul.gec.dao.impl.DocumentDaoImpl;
import com.mogul.gec.pojo.DocumentInf;
import com.mogul.gec.service.DocumentService;
import com.mogul.gec.utils.Page;
import com.mogul.gec.vo.QueryVo;

import java.util.List;

public class DocumentServiceImpl implements DocumentService {
    private DocumentDao documentDao=new DocumentDaoImpl();

    @Override
    public Page getDocumentList(QueryVo documentVo) {
        int count = documentDao.count(documentVo);
        Page page=new Page(null,count,documentVo.getSize(),documentVo.getLimit());
        documentVo.setLimit((documentVo.getLimit()-1)*documentVo.getSize());
        List<DocumentInf> documentVos=documentDao.getList(documentVo);
        page.setList(documentVos);
        return page;
    }

    @Override
    public void addDocumentInfo(DocumentInf documentInf) {
        documentDao.insert(documentInf);
    }

    @Override
    public void delete(long id) {
        documentDao.delete(id);
    }

    @Override
    public DocumentInf getDocumentInfo(long id) {
        return documentDao.get(id);
    }

    @Override
    public void updateDocumentInfo(DocumentInf documentInf){
        documentDao.update(documentInf);
    }
}
