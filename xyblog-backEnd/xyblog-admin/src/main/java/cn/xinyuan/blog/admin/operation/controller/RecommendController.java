package cn.xinyuan.blog.admin.operation.controller;

import cn.xinyuan.blog.admin.operation.service.IBlogRecommendService;
import cn.xinyuan.blog.common.api.Result;
import cn.xinyuan.blog.common.constants.RedisCacheNames;
import cn.xinyuan.blog.common.util.PageUtils;
import cn.xinyuan.blog.common.validator.ValidatorUtils;
import cn.xinyuan.blog.entity.operation.BlogRecommend;
import cn.xinyuan.blog.entity.operation.VO.BlogRecommendVO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: RecommendController
 * @Description: java类作用描述
 * @Author: xinyuan
 * @CreateDate: 2020/3/17 14:51
 */
@RestController
@RequestMapping("/admin/operation/recommend")
public class RecommendController {

    @Autowired
    private IBlogRecommendService iBlogRecommendService;

    /**
     * 列表
     */
    @GetMapping("/list")
    public Result list(@RequestParam Map<String, Object> params){
        PageUtils page = iBlogRecommendService.queryPage(params);
        return Result.success(page);
    }

    @GetMapping("/select")
    public Result select () {
        List<BlogRecommendVO> recommendList = iBlogRecommendService.listSelect();
        return Result.success(recommendList);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    public Result info(@PathVariable("id") Long id){
        BlogRecommend recommend = iBlogRecommendService.getById(id);

        return Result.success(recommend);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    public Result save(@RequestBody BlogRecommend recommend){
        ValidatorUtils.validateEntity(recommend);
        iBlogRecommendService.saveRecommend(recommend);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result update(@RequestBody BlogRecommend recommend){
        ValidatorUtils.validateEntity(recommend);
        iBlogRecommendService.updateById(recommend);
        return Result.success();
    }

    @PutMapping("/top/{id}")
    public Result updateTop (@PathVariable Long id) {
        iBlogRecommendService.updateTop(id);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete")
    public Result delete(@RequestBody Long[] ids){
        iBlogRecommendService.removePatch(Arrays.asList(ids));
        return Result.success();
    }

    /**
     * 根据文章id删除
     */
    @DeleteMapping("/deleteByArticleId")
    public Result deleteByArticleId(@RequestBody Long[] ids){
        iBlogRecommendService.deleteByArticleId(Arrays.asList(ids));
        return Result.success();
    }
}
