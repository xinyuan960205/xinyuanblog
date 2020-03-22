package cn.xinyuan.blog.admin.operation.controller;

import cn.xinyuan.blog.admin.operation.service.IBlogTagInfoService;
import cn.xinyuan.blog.common.api.Result;
import cn.xinyuan.blog.common.enums.ModuleEnum;
import cn.xinyuan.blog.common.util.PageUtils;
import cn.xinyuan.blog.common.validator.ValidatorUtils;
import cn.xinyuan.blog.entity.operation.BlogArticleTag;
import cn.xinyuan.blog.entity.operation.BlogTagInfo;
import cn.xinyuan.blog.mapper.operation.BlogArticleTagMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: TagController
 * @Description: java类作用描述
 * @Author: xinyuan
 * @CreateDate: 2020/3/14 22:23
 */
@RestController
@RequestMapping("/admin/operation/tag")
public class TagController {

    @Autowired
    private IBlogTagInfoService iBlogTagInfoService;

    @Autowired
    private BlogArticleTagMapper blogArticleTagMapper;

    /**
     * 列表
     */
    @GetMapping("/list")
    public Result list(@RequestParam Map<String, Object> params){
        PageUtils page = iBlogTagInfoService.queryPage(params);
        return Result.success(page);
    }

    @GetMapping("/select")
    public Result select(@RequestParam("type") Integer type){
        List<BlogTagInfo> tagList = iBlogTagInfoService.list(type);
        return Result.success(tagList);
    }

    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    public Result info(@PathVariable("id") String id){
        BlogTagInfo tag = iBlogTagInfoService.getById(id);
        return Result.success(tag);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public Result save(@RequestBody BlogTagInfo tag){
        ValidatorUtils.validateEntity(tag);
        iBlogTagInfoService.save(tag);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result update(@RequestBody BlogTagInfo tag){
        ValidatorUtils.validateEntity(tag);
        iBlogTagInfoService.updateById(tag);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    public Result delete(@RequestBody String[] ids){
        for (String id : ids) {
            List<BlogArticleTag> tagLinkList = blogArticleTagMapper.selectList(new QueryWrapper<BlogArticleTag>().lambda()
                    .eq(BlogArticleTag::getTagId, id));
            if(!CollectionUtils.isEmpty(tagLinkList)) {
                BlogTagInfo blogTagInfo = iBlogTagInfoService.getById(id);
                if (blogTagInfo.getType().equals(ModuleEnum.ARTICLE.getValue())) {
                    return Result.failed("该标签下有文章，无法删除");
                }
                if (blogTagInfo.getType().equals(ModuleEnum.BOOK.getValue())) {
                    return Result.failed("该标签下有图书，无法删除");
                }
                if(blogTagInfo.getType().equals(ModuleEnum.BOOK_NOTE.getValue())) {
                    return Result.failed("该标签下有笔记，无法删除");
                }
            }
        }
        iBlogTagInfoService.removeByIds(Arrays.asList(ids));

        return Result.success();
    }
}
