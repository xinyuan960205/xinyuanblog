package cn.xinyuan.blog.portal.operation.controller;

import cn.xinyuan.blog.common.api.Result;
import cn.xinyuan.blog.entity.operation.BlogCategoryInfo;
import cn.xinyuan.blog.portal.operation.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


/**
 * @ClassName: CategoryController
 * @Description: java类作用描述
 * @Author: xinyuan
 * @CreateDate: 2020/3/13 21:49
 */
@RestController
@RequestMapping("operation")
public class PortalCategoryController {
    @Autowired
    private ICategoryService iCategoryService;

    @GetMapping("/categories")
    public Result listCategory(@RequestParam Map<String,Object> params) {
        List<BlogCategoryInfo> categoryList = iCategoryService.listCategory(params);
        return Result.success(categoryList);
    }

}
