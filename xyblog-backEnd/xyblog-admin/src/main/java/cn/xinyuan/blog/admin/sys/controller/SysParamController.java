package cn.xinyuan.blog.admin.sys.controller;

import cn.xinyuan.blog.admin.sys.service.SysParamService;
import cn.xinyuan.blog.common.api.Result;
import cn.xinyuan.blog.common.base.AbstractController;
import cn.xinyuan.blog.common.util.PageUtils;
import cn.xinyuan.blog.common.validator.ValidatorUtils;
import cn.xinyuan.blog.entity.sys.DO.SysParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: SysParamController
 * @Description:
 * @Author: xinyuan
 * @CreateDate: 2020/1/24 14:22
 */
@RestController
@Slf4j
@RequestMapping("/admin/sys/param")
public class SysParamController extends AbstractController{

    @Autowired
    private SysParamService sysParamService;

    /**
     * 获取所有参数
     */
    @GetMapping("/all")
    public Result listAll(){
        List<SysParam> sysParamList = sysParamService.list(null);
        return Result.success(sysParamList);
    }

    /**
     * 列表
     */
    @GetMapping("/list")
    @RequiresPermissions("sys:param:list")
    public Result list(@RequestParam Map<String, Object> params){
        PageUtils page = sysParamService.queryPage(params);

        return Result.success(page);
    }

    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @RequiresPermissions("sys:param:info")
    public Result info(@PathVariable("id") String id){
        SysParam param = sysParamService.getById(id);

        return Result.success(param);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("sys:param:save")
    public Result save(@RequestBody SysParam param){
        ValidatorUtils.validateEntity(param);
        sysParamService.save(param);

        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    @RequiresPermissions("sys:param:update")
    public Result update(@RequestBody SysParam param){
        ValidatorUtils.validateEntity(param);
        sysParamService.updateById(param);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    @RequiresPermissions("sys:param:delete")
    public Result delete(@RequestBody String[] ids){
        sysParamService.removeByIds(Arrays.asList(ids));

        return Result.success();
    }
}
