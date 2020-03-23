package cn.xinyuan.blog.admin.operation.controller;

import cn.xinyuan.blog.admin.operation.service.IBlogLinkService;
import cn.xinyuan.blog.common.api.Result;
import cn.xinyuan.blog.common.util.PageUtils;
import cn.xinyuan.blog.common.validator.ValidatorUtils;
import cn.xinyuan.blog.entity.operation.BlogLink;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

/**
 * @ClassName: LinkController
 * @Description: 友链
 * @Author: xinyuan
 * @CreateDate: 2020/3/17 13:53
 */
@RestController
@RequestMapping("/admin/operation/link")
public class LinkController {
    @Autowired
    private IBlogLinkService iBlogLinkService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @RequiresPermissions("operation:link:list")
    public Result list(@RequestParam Map<String, Object> params){
        PageUtils page = iBlogLinkService.queryPage(params);
        return Result.success(page);
    }

    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    public Result info(@PathVariable("id") Long id){
        BlogLink link = iBlogLinkService.getById(id);
        return Result.success(link);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public Result save(@RequestBody BlogLink link){
        ValidatorUtils.validateEntity(link);
        iBlogLinkService.save(link);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result update(@RequestBody BlogLink link){
        ValidatorUtils.validateEntity(link);
        iBlogLinkService.updateById(link);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    public Result delete(@RequestBody Long[] ids){
        iBlogLinkService.removeByIds(Arrays.asList(ids));
        return Result.success();
    }

}
