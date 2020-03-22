package cn.xinyuan.blog.portal.operation.controller;

import cn.xinyuan.blog.admin.operation.service.IBlogLinkService;
import cn.xinyuan.blog.common.api.Result;
import cn.xinyuan.blog.common.constants.RedisCacheNames;
import cn.xinyuan.blog.entity.operation.BlogLink;
import cn.xinyuan.blog.portal.operation.service.ILinkService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: PortalLinkController
 * @Description: 友链前台接口
 * @Author: xinyuan
 * @CreateDate: 2020/3/17 14:24
 */
@RequestMapping("/operation")
@RestController("LinkPortalController")
public class PortalLinkController {
    @Resource
    private ILinkService linkService;

    @RequestMapping("/links")
    public Result listLink() {
        List<BlogLink> linkList = linkService.listLink();
        return Result.success(linkList);
    }
}
