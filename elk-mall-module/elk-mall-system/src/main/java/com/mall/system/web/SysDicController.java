package com.mall.system.web;

import com.mall.common.annotation.DuplicateFilter;
import com.mall.common.annotation.Log;
import com.mall.common.base.model.Response;
import com.mall.common.util.JsonResponse;
import com.mall.common.util.constant.Constant;
import com.mall.common.util.exception.RRException;
import com.mall.system.model.SysDic;
import com.mall.system.model.SysDicTerm;
import com.mall.system.service.SysDicService;
import com.mall.utils.Query;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(value = "数据字典", description = "数据字典")
@RequestMapping("/sys/dic")
public class SysDicController extends AbstractController{
    @Autowired
    private SysDicService sysDicService;

    /**
     * 列表
     */
    @ApiOperation(value = "数据字典列表",notes = "权限编码（sysdic:list）")
    @GetMapping("/list")
    @RequiresPermissions("sys:dic:list")
    public List<SysDic> list(@RequestParam Map<String, Object> params){
        List<SysDic> dics = null;
        Query query = new Query(params,SysDic.class);
        dics = sysDicService.queryList(query);
        return dics;
    }

    /**
     *  根据字典编码查询字典
     * @param code
     * @return
     */
    @ApiOperation(value = "根据字典编码查询数据字典",response=Response.class)
    @GetMapping("/query/{code}")
    public JsonResponse query(@ApiParam(name="code",value="字典编码",required=true) @PathVariable("code") Object code){
        List<SysDicTerm> dics = sysDicService.queryDicTerm(code);
        return JsonResponse.success(dics);
    }

    /**
     * 字典目录(添加、修改字典)
     */
    @ApiOperation(value = "字典目录选择",response=Response.class, notes = "权限编码（sys:dic:select）")
    @GetMapping("/select")
    @RequiresPermissions("sys:dic:select")
    public JsonResponse select(){
        //查询目录类型数据
        Map<String, Object> params = new HashMap<>();
        params.put("type", 0);
        params.put("status", true);
        Query query = new Query(params,SysDic.class);
        List<SysDic> menuList = sysDicService.queryList(query);

        //添加顶级菜单
        SysDic root = new SysDic();
        root.setId(0L);
        root.setName("一级菜单");
        root.setParentId(-1L);
        root.setOpen(true);
        menuList.add(root);
        return JsonResponse.success(menuList);
    }


    /**
     * 信息
     */
    @ApiOperation(value = "查询信息",response=Response.class,notes = "权限编码（sysdic:info）")
    @GetMapping("/info/{id}")
    @RequiresPermissions("sys:dic:info")
    public JsonResponse info(@PathVariable("id") Object id){
        SysDic sysDic = sysDicService.queryObject(id);
        return JsonResponse.success(sysDic);
    }

    /**
     * 保存
     */
    @Log("数据字典保存")
    @ApiOperation(value = "保存",response=Response.class,notes = "权限编码（sysdic:save）")
    @PostMapping("/save")
    @RequiresPermissions("sys:dic:save")
    @DuplicateFilter(check=true)
    public JsonResponse save(@RequestBody(required = false) SysDic sysDic){
        sysDic.setCreateBy(getUser().getUsername());
        sysDic.setCreateTime(new Date());
        verifyForm(sysDic);
        sysDicService.save(sysDic);
        return JsonResponse.success();
    }

    /**
     * 修改
     */
    @Log(value="数据字典修改",update=true,serviceClass=SysDicService.class)
    @ApiOperation(value = "修改",response=Response.class,notes = "权限编码（sysdic:update）")
    @PostMapping("/update")
    @RequiresPermissions("sys:dic:update")
    public JsonResponse update(@RequestBody(required = false) SysDic sysDic){
        verifyForm(sysDic);
        sysDic.setUpdateBy(getUser().getUsername());
        sysDic.setUpdateTime(new Date());
        sysDicService.update(sysDic);
        return JsonResponse.success();
    }

    /**
     * 删除
     */
    @Log(value="数据字典删除",delete=true,serviceClass=SysDicService.class)
    @ApiOperation(value = "删除",response=Response.class,notes = "权限编码（sysdic:delete）")
    @PostMapping("/delete")
    @RequiresPermissions("sys:dic:delete")
    public JsonResponse delete(Long id){

        List<SysDic> sysDic = sysDicService.queryParentList(id);
        if(sysDic.size() > 0){
            return JsonResponse.error("请先删除字典项再删目录");
        }
        sysDicService.deleteBatch(new Object[]{id});
        return JsonResponse.success();
    }

    /**
     * 验证参数是否正确
     */
    private void verifyForm(SysDic sysDic){
        if(sysDic.getType() == Constant.DicType.CATALOG.getValue()){//字典目录
            if(StringUtils.isBlank(sysDic.getName())){
                throw new RRException("字典名称不能为空");
            }
            if(StringUtils.isBlank(sysDic.getCode())){
                throw new RRException("字典编码不能为空");
            }
        }
        if(sysDic.getType() == Constant.DicType.TERM.getValue()){//字典项
            if(StringUtils.isBlank(sysDic.getValue())){
                throw new RRException("字典值不能为空");
            }
            if(StringUtils.isBlank(sysDic.getText())){
                throw new RRException("字典项不能为空");
            }
            if(StringUtils.isBlank(sysDic.getParentName())){
                throw new RRException("字典所在目录不能为空");
            }
        }

        //上级目录类型
        int parentType = Constant.DicType.CATALOG.getValue();
        if(sysDic.getParentId() != 0){
            SysDic parentSysDic = sysDicService.queryObject(sysDic.getParentId());
            parentType = parentSysDic.getType();
        }

        //字典项
        if(sysDic.getType() == Constant.DicType.TERM.getValue()){
            if(parentType != Constant.DicType.CATALOG.getValue()){
                throw new RRException("上级目录只能为目录类型");
            }
            return ;
        }
    }
}
