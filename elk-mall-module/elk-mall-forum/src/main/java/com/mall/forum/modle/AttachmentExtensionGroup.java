package com.mall.forum.modle;

/**
 * Created by cuilidong on 2019/2/6.
 */
public class AttachmentExtensionGroup {

    public static final int DOWNLOAD_INLINE = 1;
    public static final int DOWNLOAD_PHYSICAL = 2;

    private int id;
    private String name;
    private boolean allow;
    private String uploadIcon;
    private int downloadMode;
    /**
     * @return Returns the downloadMode.
     */
    public int getDownloadMode()
    {
        return this.downloadMode;
    }

    /**
     * @param downloadMode The downloadMode to set.
     */
    public void setDownloadMode(int downloadMode)
    {
        this.downloadMode = downloadMode;
    }

    /**
     * @return Returns the allow.
     */
    public boolean isAllow()
    {
        return this.allow;
    }

    /**
     * @param allow The allow to set.
     */
    public void setAllow(boolean allow)
    {
        this.allow = allow;
    }

    /**
     * @return Returns the id.
     */
    public int getId()
    {
        return this.id;
    }

    /**
     * @param id The id to set.
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     * @return Returns the name.
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * @param name The name to set.
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return Returns the uploadIcon.
     */
    public String getUploadIcon()
    {
        return this.uploadIcon;
    }

    /**
     * @param uploadIcon The uploadIcon to set.
     */
    public void setUploadIcon(String uploadIcon)
    {
        this.uploadIcon = uploadIcon;
    }
}
