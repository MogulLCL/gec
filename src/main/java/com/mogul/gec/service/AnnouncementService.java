package com.mogul.gec.service;

import com.mogul.gec.pojo.AnnouncementInfo;
import com.mogul.gec.utils.Page;

public interface AnnouncementService {

    Page getAnnouncements(int limit,int size);

    void addAnnouncement(AnnouncementInfo announcementInfo);

    void delete(long id);

    AnnouncementInfo getAnnouncement(long id);

    void updateAnnouncement(AnnouncementInfo announcementInfo);
}
