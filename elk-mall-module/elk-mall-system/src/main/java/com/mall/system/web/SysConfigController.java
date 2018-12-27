package com.mall.system.web;

import com.mall.common.annotation.DuplicateFilter;
import com.mall.common.annotation.Log;
import com.mall.common.base.model.Response;
import com.mall.common.util.JsonResponse;
import com.mall.common.util.Page;
import com.mall.common.util.validator.ValidatorUtils;
import com.mall.system.model.SysConfig;
import com.mall.system.service.SysConfigService;
import com.mall.utils.Query;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Api(value = "系统配置", description = "系统配置")
@RequestMapping("/sys/config")
public class SysConfigController extends AbstractController {
    @Autowired
    private SysConfigService sysConfigService;

    /**
     * 所有配置列表
     */
    @ApiOperation(value = "配置列表",response=Response.class, notes = "权限编码（sys:config:list）")
    @GetMapping("/list")
    @RequiresPermissions("sys:config:list")
    public JsonResponse list(@RequestParam Map<String, Object> params){
        Query query = new Query(params,SysConfig.class);
        Page page = sysConfigService.getPage(query);
        return JsonResponse.success(page);
    }


    /**
     * 配置信息
     */
    @ApiOperation(value = "配置信息",response=Response.class, notes = "权限编码（sys:config:info）")
    @GetMapping("/info/{id}")
    @RequiresPermissions("sys:config:info")
    public JsonResponse info(@PathVariable("id") Object id){
        SysConfig config = sysConfigService.queryObject(id);
        return JsonResponse.success(config);
    }

    /**
     * 保存配置
     */
    @Log("保存配置")
    @ApiOperation(value = "保存配置",response=Response.class, notes = "权限编码（sys:config:save）")
    @PostMapping("/save")
    //@RequiresPermissions("sys:config:save")
    //@DuplicateFilter(check=true)
    public JsonResponse save(@RequestBody(required = false) SysConfig config){
        ValidatorUtils.validateEntity(config);
        SysConfig cof = new SysConfig();
        cof.setKey("pay");
        cof.setValue("支付");
        cof.setStatus(true);
        cof.setRemark("支付组件");
        sysConfigService.save(config);
        return JsonResponse.success();
    }

    /**
     * 修改配置
     */
    @Log(value ="修改配置",update=true,serviceClass=SysConfigService.class)
    @ApiOperation(value = "修改配置",response=Response.class, notes = "权限编码（sys:config:update）")
    @PostMapping("/update")
    @RequiresPermissions("sys:config:update")
    public JsonResponse update(@RequestBody(required = false) SysConfig config){
        ValidatorUtils.validateEntity(config);
        sysConfigService.update(config);
        return JsonResponse.success();
    }

    /**
     * 删除配置
     */
    @Log(value ="删除配置",delete=true,serviceClass=SysConfigService.class)
    @ApiOperation(value = "删除配置",response=Response.class, notes = "权限编码（sys:config:delete）")
    @PostMapping("/delete")
    @RequiresPermissions("sys:config:delete")
    public JsonResponse delete(@RequestBody(required = false) Long[] ids){
        sysConfigService.deleteBatch(ids);
        return JsonResponse.success();
    }

}
