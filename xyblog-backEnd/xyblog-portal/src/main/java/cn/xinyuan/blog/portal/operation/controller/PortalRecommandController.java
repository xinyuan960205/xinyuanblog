package cn.xinyuan.blog.portal.operation.controller;

import cn.xinyuan.blog.common.api.Result;
import cn.xinyuan.blog.common.constants.RedisCacheNames;
import cn.xinyuan.blog.entity.operation.VO.BlogRecommendVO;
import cn.xinyuan.blog.portal.operation.service.IRecommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName: PortalRecommandController
 * @Description: 推荐 前台接口
 * @Author: xinyuan
 * @CreateDate: 2020/3/17 16:31
 */
@RestController("recommendPortalController")
@RequestMapping("/operation")
public class PortalRecommandController {

    @Autowired
    private IRecommandService iRecommandService;
    
    @RequestMapping("/recommends")
    public Result listRecommend() {
        List<BlogRecommendVO> recommendList = iRecommandService.listRecommendVo();
        return Result.success(recommendList);
    }
}
