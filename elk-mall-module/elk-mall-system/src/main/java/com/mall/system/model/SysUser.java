package com.mall.system.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.mall.common.annotation.PropertyExt;
import com.mall.common.util.validator.group.AddGroup;
import com.mall.common.util.validator.group.UpdateGroup;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class SysUser implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -2744695388848203079L;

    /**
     * 用户ID
     */
    @Id
    @ApiModelProperty("用户id")
    //@PropertyExt(isExport=false)
    private Long userId;

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
   // @NotBlank(message="用户名不能为空", groups = {AddGroup.class, UpdateGroup.class})
    @Length(max=20)
    @PropertyExt
    private String username;

    /**
     * 密码
     */
//	@ApiModelProperty("密码")
    @NotBlank(message="密码不能为空", groups = AddGroup.class)
    private String password;

    /**
     * 盐
     */
//	@ApiModelProperty("盐")
    private String salt;

    /**
     * 邮箱
     */
    @ApiModelProperty("邮箱")
    @NotBlank(message="邮箱不能为空", groups = {AddGroup.class, UpdateGroup.class})
    @Email(message="邮箱格式不正确", groups = {AddGroup.class, UpdateGroup.class})
    @PropertyExt
    private String email;

    /**
     * 手机号
     */
    @ApiModelProperty("手机号")
    @Length(max=20)
    @PropertyExt
    private String mobile;

    /**
     * 状态  0：禁用   1：正常
     */
    @ApiModelProperty("状态  0：禁用   1：正常")
    @PropertyExt
    private Boolean status;

    /**
     * 角色ID列表
     */
    @ApiModelProperty("角色ID列表")
    private List<Long> roleIdList;

    /**
     * 部门ID
     */
    @ApiModelProperty("部门ID")
    @NotNull(message="部门不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private Long deptId;

    /**
     * 部门名称
     */
    @ApiModelProperty("部门名称")
    @PropertyExt(isExport=false)
    private String deptName;

    /**
     * 职位
     */
    @ApiModelProperty("职位")
    @PropertyExt
    private String position;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    @PropertyExt
    private Date createTime;

    private String token;

    /**
     * 设置：
     * @param userId
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取：
     * @return Long
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置：用户名
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取：用户名
     * @return String
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置：密码
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取：密码
     * @return String
     */
    @JSONField(serialize = false)
    public String getPassword() {
        return password;
    }

    /**
     * 设置：邮箱
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取：邮箱
     * @return String
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置：手机号
     * @param mobile 手机号
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取：手机号
     * @return String
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置：状态  0：禁用   1：正常
     * @param status 状态  0：禁用   1：正常
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     * 获取：状态  0：禁用   1：正常
     * @return Integer
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * 设置：创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取：创建时间
     * @return Date
     */
    public Date getCreateTime() {
        return createTime;
    }

    public List<Long> getRoleIdList() {
        return roleIdList;
    }

    public void setRoleIdList(List<Long> roleIdList) {
        this.roleIdList = roleIdList;
    }

    @JSONField(serialize = false)
    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @JSONField(serialize = false)
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


}
