package com.mogul.gec.service.impl;

import com.mogul.gec.dao.AnnouncementDao;
import com.mogul.gec.dao.impl.AnnouncementDaoImpl;
import com.mogul.gec.pojo.AnnouncementInfo;
import com.mogul.gec.service.AnnouncementService;
import com.mogul.gec.utils.Page;

import java.util.List;

public class AnnouncementServiceImpl implements AnnouncementService {
    private AnnouncementDao announcementDao=new AnnouncementDaoImpl();
    @Override
    public Page getAnnouncements(int limit, int size) {
        int totle=announcementDao.count();
        Page page=new Page(null,totle,size,limit);
        limit=(limit-1)*size;
        List<AnnouncementInfo> announcementInfos = announcementDao.getAnnouncementInfos(limit,size);
        page.setList(announcementInfos);
        return page;
    }

    @Override
    public void addAnnouncement(AnnouncementInfo announcementInfo) {
        announcementDao.insert(announcementInfo);
    }

    @Override
    public AnnouncementInfo getAnnouncement(long id) {
        return announcementDao.get(id);
    }

    @Override
    public void updateAnnouncement(AnnouncementInfo announcementInfo) {
        announcementDao.update(announcementInfo);
    }

    @Override
    public void delete(long id) {
        announcementDao.delete(id);
    }
    //    public static void main(String[] args) {
//        AnnouncementService announcementService=new AnnouncementServiceImpl();
//        Page announcements = announcementService.getAnnouncements(1, 10);
//        System.out.println(announcements.getList().size());
//        System.out.println(announcements.getTotalCount());
//    }
}
