package com.mall.forum.dao;

import com.mall.common.base.dao.BaseDao;
import com.mall.forum.modle.Avatar;
import com.mall.forum.modle.AvatarType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 头像
 */
public interface AvatarDao extends BaseDao<Avatar> {


    /**
     * 待开发
     */

    public List<Avatar> getAll();

    /**
     * 系统提供虚拟头像
     * @return
     */
    public List<Avatar> getGalleryAvatar();

    /**
     * 自己上传的头像
     * @return
     */
    public List<Avatar> getUploadedAvatar();

    /**
     * 根据类型获取头像
     * @param type
     * @return
     */
    public List<Avatar> getAllAvatars(@Param("type") AvatarType type);

    /**
     * 删除头像
     * @param avatarId
     */

    public void remove(@Param("avatarId") int avatarId ) ;


}
