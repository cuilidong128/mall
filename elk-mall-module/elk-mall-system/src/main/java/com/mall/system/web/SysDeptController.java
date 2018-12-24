package com.mall.system.web;

import com.mall.common.annotation.DuplicateFilter;
import com.mall.common.annotation.Log;
import com.mall.common.base.model.Response;
import com.mall.common.util.JsonResponse;
import com.mall.common.util.constant.Constant;
import com.mall.common.util.validator.ValidatorUtils;
import com.mall.common.util.validator.group.AddGroup;
import com.mall.common.util.validator.group.UpdateGroup;
import com.mall.system.model.SysDept;
import com.mall.system.service.SysDeptService;
import com.mall.utils.Query;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@Api(value = "部门管理", description = "部门管理")
@RequestMapping("/sys/dept")
public class SysDeptController extends AbstractController {
    @Autowired
    private SysDeptService sysDeptService;

    /**
     * 列表
     */
    @ApiOperation(value = "部门列表", notes = "权限编码（sys:dept:list）")
    @GetMapping("/list")
    @RequiresPermissions("sys:dept:list")
    public List<SysDept> list(){
        Query query = new Query(new HashMap<String, Object>());
        List<SysDept> deptList = sysDeptService.queryList(query);
        return deptList;
    }

    /**
     * 选择部门(添加、修改菜单)
     */
    @ApiOperation(value = "选择部门",response=Response.class, notes = "权限编码（sys:dept:select）")
    @GetMapping("/select")
    @RequiresPermissions("sys:dept:select")
    public JsonResponse select(){
        Query query = new Query(new HashMap<String, Object>());
        List<SysDept> deptList = sysDeptService.queryList(query);
        //添加一级部门
        if(getUserId().equals(Constant.SUPER_ADMIN)){
            SysDept root = new SysDept();
            root.setDeptId(0L);
            root.setName("一级部门");
            root.setParentId(-1L);
            root.setOpen(true);
            deptList.add(root);
        }
        return JsonResponse.success(deptList);
    }

    /**
     * 上级部门Id(管理员则为0)
     */
    @ApiOperation(value = "部门树列表",response=Response.class, notes = "权限编码（sys:dept:list）")
    @GetMapping("/info")
    @RequiresPermissions("sys:dept:list")
    public JsonResponse info(){
        long deptId = 0;
        if(getUserId() != Constant.SUPER_ADMIN){
            SysDept dept = sysDeptService.queryObject(getDeptId());
            deptId = dept.getParentId();
        }
        return JsonResponse.success(deptId);
    }

    /**
     * 信息
     */
    @ApiOperation(value = "部门信息",response=Response.class, notes = "权限编码（sys:dept:info）")
    @GetMapping("/info/{deptId}")
    @RequiresPermissions("sys:dept:info")
    public JsonResponse info(@PathVariable("deptId") Object deptId){
        SysDept dept = sysDeptService.queryObject(deptId);
        return JsonResponse.success(dept);
    }

    /**
     * 保存
     */
    @Log("保存部门")
    @ApiOperation(value = "保存部门",response=Response.class, notes = "权限编码（sys:dept:save）")
    @PostMapping("/save")
    @RequiresPermissions("sys:dept:save")
    @DuplicateFilter(check=true)
    public JsonResponse save(@RequestBody(required = false) SysDept dept){
        ValidatorUtils.validateEntity(dept, AddGroup.class);
        sysDeptService.save(dept);
        return JsonResponse.success();
    }

    /**
     * 修改
     */
    @Log(value="保存部门",update=true,serviceClass=SysDeptService.class)
    @ApiOperation(value = "修改部门",response=Response.class, notes = "权限编码（sys:dept:update）")
    @PostMapping("/update")
    @RequiresPermissions("sys:dept:update")
    public JsonResponse update(@RequestBody(required = false) SysDept dept){
        ValidatorUtils.validateEntity(dept, UpdateGroup.class);
        sysDeptService.update(dept);
        return JsonResponse.success();
    }

    /**
     * 删除
     */
    @Log(value="删除部门",delete=true,serviceClass=SysDeptService.class)
    @ApiOperation(value = "删除部门",response=Response.class, notes = "权限编码（sys:dept:delete）")
    @PostMapping("/delete")
    @RequiresPermissions("sys:dept:delete")
    public JsonResponse delete(Long deptId){
        //判断是否有子部门
        List<Long> deptList = sysDeptService.queryDeptIdList(deptId);
        if(deptList.size() > 0){
            return JsonResponse.error("请先删除子部门");
        }
        sysDeptService.delete(deptId);
        return JsonResponse.success();
    }

}
