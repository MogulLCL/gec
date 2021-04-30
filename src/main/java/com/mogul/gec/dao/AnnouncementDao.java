package com.mogul.gec.dao;

import com.mogul.gec.pojo.AnnouncementInfo;

import java.util.List;

public interface AnnouncementDao {

    List<AnnouncementInfo> getAnnouncementInfos(int limit,int size);

    int count();

    void insert(AnnouncementInfo announcementInfo);

    void delete(long id);

    AnnouncementInfo get(long id);

    void update(AnnouncementInfo announcementInfo);
}
