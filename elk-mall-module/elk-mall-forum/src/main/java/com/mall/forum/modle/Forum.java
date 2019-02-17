package com.mall.forum.modle;

import com.mall.forum.dao.ForumDao;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



@Data
@Entity
@ToString
@Table(name = "jforum_forums")
public class Forum implements Serializable {
    @Id
    @Column(name = "forum_id")
    private int id;

    private Category category;

    @Column(name = "forum_name")
    private String name;

    @Column(name = "forum_description")
    private String description;

    @Column(name = "forum_order")
    private int displayOrder;

    @Column(name = "forum_moderated")
    private boolean moderated;

    @Column(name = "forum_last_post_id")
    private int lastPostId;

    @Column(name = "forum_allow_anonymous_posts")
    private boolean allowAnonymousPosts;

    private Post lastPost;

    @Transient
    private boolean unread;

    @Transient
    private ForumDao repository;

    public Forum() {}

    public Forum(int id) {
        this.id = id;
    }

//    @Autowired
//    public Forum(ForumRepository repository) {
//        this.repository = repository;
//    }

    public boolean isAllowAnonymousPosts() {
        return this.allowAnonymousPosts;
    }

    public void setAllowAnonymousPosts(boolean allowAnonymousPosts) {
        this.allowAnonymousPosts = allowAnonymousPosts;
    }

    /**
     * Get the last post in this forum
     * @return the last post of this forum
     */
//    public Post getLastPost() {
//        return this.lastPost;
//    }
//
//    public void setLastPost(Post post) {
//        this.lastPost = post;
//    }

    /**
     * Gets the forum's description
     *
     * @return String with the description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Get the moderators of this forum
     * @return the moderators
     */
//    public List<Group> getModerators() {
//        if (this.isModerated()) {
//            this.assertRepository();
//            return this.repository.getModerators(this);
//        }
//        else {
//            return new ArrayList<Group>();
//        }
//    }

    /**
     * Gets the forum's ID
     *
     * @return int value representing the ID
     */
    public int getId() {
        return this.id;
    }

    /**
     * Gets the category which the forum belongs to
     *
     * @return int value representing the ID of the category
     */
//    public Category getCategory() {
//        return this.category;
//    }

    /**
     * Checks if is a moderated forum
     *
     * @return boolean value. <code>true</code> if the forum is moderated, <code>false</code> if not.
     */
    public boolean isModerated() {
        return this.moderated;
    }

    /**
     * Gets the name of the forum
     *
     * @return String with the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the order
     *
     * @return int value representing the order of the forum
     */
    public int getDisplayOrder() {
        return this.displayOrder;
    }

    public boolean isUnread() {
        return this.unread;
    }

    /**
     * Sets the description.
     *
     * @param description The description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets the id.
     *
     * @param id The id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets the category id
     *
     * @param idCategories The ID of the category to set to the forum
     */
//    public void setCategory(Category category) {
//        this.category = category;
//    }

    /**
     * Sets the moderated flag to the forum
     *
     * @param moderated <code>true</code> or <code>false</code>
     */
    public void setModerated(boolean moderated) {
        this.moderated = moderated;
    }

    /**
     * Sets the name of the forum
     *
     * @param name The name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the order.
     *
     * @param order The order to set
     */
    public void setDisplayOrder(int order) {
        this.displayOrder = order;
    }

    public void setUnread(boolean status) {
        this.unread = status;
    }

    /**
     * Get the total of posts in this forum
     * @return the total of posts
     */
    public int getTotalPosts() {
        this.assertRepository();
        return this.repository.getTotalPosts(this.getId());
    }

    /**
     * Gets the total number of topics posted in the forum
     * @return int value with the total number of the topics
     */
//    public int getTotalTopics() {
//        this.assertRepository();
//        return this.repository.getTotalTopics(this);
//    }

    /**
     * @see {@link ForumRepository#getTopics(Forum, int, int)}
     */
//    public List<Topic> getTopics(int start, int count) {
//        this.assertRepository();
//        return this.repository.getTopics(this, start, count);
//    }

    /**
     * @see {@link ForumRepository#getTopicsPendingModeration(Forum)}
     */
//    public List<Topic> getTopicsPendingModeration() {
//        if (this.isModerated()) {
//            this.assertRepository();
//            return this.repository.getTopicsPendingModeration(this);
//        }
//        else {
//            return new ArrayList<Topic>();
//        }
//    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Forum)) {
            return false;
        }

        Forum f = (Forum) o;
        return f.getId() == this.getId();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return this.getId();
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return new StringBuilder(64)
                .append('[').append(this.getName())
                .append(", id=").append(this.getId())
                .append(", order=").append(this.getDisplayOrder())
                .append(']').toString();
    }

    private void assertRepository() {
        if (this.repository == null) {
            throw new IllegalStateException("repository was not initialized");
        }
    }
}
