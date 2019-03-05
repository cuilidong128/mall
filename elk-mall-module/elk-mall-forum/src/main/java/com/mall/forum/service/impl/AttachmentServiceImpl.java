package com.mall.forum.service.impl;

import com.mall.forum.dao.AttachmentDao;
import com.mall.forum.dao.CategoryDao;
import com.mall.forum.modle.Attachment;
import com.mall.forum.service.AttachmentService;
import com.mall.forum.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by cuilidong on 2019/2/24.
 */
@Service("attachmentService")
public class AttachmentServiceImpl implements AttachmentService {
    @Autowired
    public AttachmentDao attachmentDao;

    /**
     * 统计附件下载次数
     * @param attachmentId
     * @return
     */
    public Attachment getAttachmentForDownload(int attachmentId){
        Attachment attachment =attachmentDao.selectAttachmentById(attachmentId);
        attachment.incrementDownloadCount();
        return attachment;
    }

    public List<AttachedFile> processNewAttachments(HttpServletRequest request){

    }

}
