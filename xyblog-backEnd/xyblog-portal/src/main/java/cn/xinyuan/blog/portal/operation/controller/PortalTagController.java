package cn.xinyuan.blog.portal.operation.controller;

import cn.xinyuan.blog.common.api.Result;
import cn.xinyuan.blog.entity.operation.BlogTagInfo;
import cn.xinyuan.blog.portal.operation.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName: PortalTagController
 * @Description: java类作用描述
 * @Author: xinyuan
 * @CreateDate: 2020/3/15 10:28
 */
@RestController
@RequestMapping("/operation")
public class PortalTagController {

    @Autowired
    private ITagService iTagService;

    @GetMapping("/tags")
    public Result listTag() {
        List<BlogTagInfo> tagList = iTagService.listTag();
        return Result.success(tagList);
    }
}
