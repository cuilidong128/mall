package com.mall.system.model;

import com.mall.common.util.validator.group.AddGroup;
import com.mall.common.util.validator.group.UpdateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.List;

/**
 * 部门管理
 * @author King chen
 * @email 396885563@qq.com
 * @date 2017年12月29日
 */
@ApiModel("部门管理")
public class SysDept implements Serializable,Comparable<SysDept>{
    private static final long serialVersionUID = 1L;

    //部门ID
    @Id
    @ApiModelProperty("部门ID")
    private Long deptId;
    //上级部门ID，一级部门为0
    @ApiModelProperty("上级部门ID，一级部门为0")
    private Long parentId;
    //部门名称
    @ApiModelProperty("部门名称")
    @NotBlank(message="部门名称不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String name;
    //上级部门名称
    @ApiModelProperty("上级部门名称")
    private String parentName;
    //排序
    @ApiModelProperty("排序")
    private Integer orderNum;
    //部门负责人
    @ApiModelProperty("部门负责人")
    private Integer manager;
    //是否删除  -1：已删除  0：正常
    @ApiModelProperty("是否删除  0：已删除  1：正常")
    private Boolean delFlag;
    /**
     * ztree属性
     */
    private Boolean open;

    private List<?> list;


    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getDeptId() {
        return deptId;
    }
    /**
     * 设置：上级部门ID，一级部门为0
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
    /**
     * 获取：上级部门ID，一级部门为0
     */
    public Long getParentId() {
        return parentId;
    }
    /**
     * 设置：部门名称
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * 获取：部门名称
     */
    public String getName() {
        return name;
    }
    /**
     * 设置：排序
     */
    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }
    /**
     * 获取：排序
     */
    public Integer getOrderNum() {
        return orderNum;
    }

    public Integer getManager() {
        return manager;
    }

    public void setManager(Integer manager) {
        this.manager = manager;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }

    public Boolean getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

    @Override
    public int compareTo(SysDept o) {
        return this.orderNum.compareTo(o.orderNum);
    }

}
