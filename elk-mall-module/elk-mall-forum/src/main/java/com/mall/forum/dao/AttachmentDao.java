package com.mall.forum.dao;

import com.mall.common.base.dao.BaseDao;
import com.mall.forum.modle.Attachment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by cuilidong on 2019/2/6.
 */
public interface AttachmentDao extends BaseDao<Attachment> {

    /**
     * Adds a new attachment.
     *
     * @param a The attacment to add
     */
    public void addAttachment(Attachment a);

    /**
     * Updates an attachment.
     * Only the file comment is updated.
     *
     * @param a The attachment to update
     */
    public void updateAttachment(Attachment a);

    /**
     * Rovemos an attachment.
     *
     * @param id The attachment's id to remove
     * @param postId the post id
     */
    public void removeAttachment(@Param("id") int id ,@Param("postId") int postId);

    /**
     * Gets the attachments of some message.
     *
     * @param postId The post id associated with the attachments.
     * @return A list where each entry is a net.jforum.entities.Attachment
     * instance.
     */
    public List selectAttachments(@Param("postId") int postId);

    /**
     * Gets an attachment by its id
     *
     * @param attachId The attachment id
     * @return The attachment, or <code>null</code> if no record was found
     */
    public Attachment selectAttachmentById(@Param("attachId") int attachId);
}
