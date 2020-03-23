package cn.xinyuan.blog.admin.operation.controller;

import cn.xinyuan.blog.admin.article.service.IBlogArticleInfoService;
import cn.xinyuan.blog.admin.operation.service.IBlogCategoryInfoService;
import cn.xinyuan.blog.common.api.Result;
import cn.xinyuan.blog.common.enums.CategoryRankEnum;
import cn.xinyuan.blog.common.exception.MyException;
import cn.xinyuan.blog.common.validator.ValidatorUtils;
import cn.xinyuan.blog.entity.operation.BlogCategoryInfo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: CategoryController
 * @Description: java类作用描述
 * @Author: xinyuan
 * @CreateDate: 2020/3/13 21:49
 */
@RestController
@RequestMapping("/admin/operation/category")
public class CategoryController {

    @Autowired
    private IBlogCategoryInfoService iBlogCategoryInfoService;

    @Autowired
    private IBlogArticleInfoService iBlogArticleInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public Result list(@RequestParam Map<String, Object> params){
        List<BlogCategoryInfo> categoryList = iBlogCategoryInfoService.queryWithParentName(params);
        return Result.success(categoryList);
    }

    /**
     * 树状列表
     */
    @RequestMapping("/select")
    public Result select(Integer type){
        List<BlogCategoryInfo> categoryList = iBlogCategoryInfoService.list(new QueryWrapper<BlogCategoryInfo>().lambda()
                .eq(type!=null,BlogCategoryInfo::getType,type));

        //添加顶级分类
        BlogCategoryInfo root = new BlogCategoryInfo();
        root.setId(-1L);
        root.setCategoryName("根目录");
        root.setParentId(-1L);
        categoryList.add(root);

        return Result.success(categoryList);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public Result info(@PathVariable("id") Long id){
        BlogCategoryInfo category = iBlogCategoryInfoService.getById(id);

        return Result.success(category);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public Result save(@RequestBody BlogCategoryInfo category){
        // 数据校验
        ValidatorUtils.validateEntity(category);
        verifyCategory(category);
        iBlogCategoryInfoService.save(category);
        return Result.success();
    }

    /**
     * 数据校验
     * @param category
     */
    private void verifyCategory(BlogCategoryInfo category) {
        //上级分类级别
        int parentRank = CategoryRankEnum.ROOT.getValue();
        if (category.getCategoryRank() != CategoryRankEnum.FIRST.getValue()
                && category.getParentId() != CategoryRankEnum.ROOT.getValue()) {
            BlogCategoryInfo parentCategory = iBlogCategoryInfoService.getById(category.getParentId());
            parentRank = parentCategory.getCategoryRank();
        }

        // 一级
        if (category.getCategoryRank() == CategoryRankEnum.FIRST.getValue()) {
            if (category.getParentId() != CategoryRankEnum.ROOT.getValue()){
                throw new MyException("上级目录只能为根目录");
            }
        }

        //二级
        if (category.getCategoryRank() == CategoryRankEnum.SECOND.getValue()) {
            if (parentRank != CategoryRankEnum.FIRST.getValue()) {
                throw new MyException("上级目录只能为一级类型");
            }
        }

        //三级
        if (category.getCategoryRank() == CategoryRankEnum.THIRD.getValue()) {
            if (parentRank != CategoryRankEnum.SECOND.getValue()) {
                throw new MyException("上级目录只能为二级类型");
            }
        }
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public Result update(@RequestBody BlogCategoryInfo category){
        // 数据校验
        ValidatorUtils.validateEntity(category);
        verifyCategory(category);
        iBlogCategoryInfoService.updateById(category);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Long id){

        //判断是否有子菜单或按钮
        List<BlogCategoryInfo> categoryList = iBlogCategoryInfoService.queryListParentId(id);
        if(categoryList.size() > 0){
            return Result.failed("请先删除子级别");
        }
        // 判断是否有文章
        if(iBlogArticleInfoService.checkByCategory(id)) {
            return Result.failed("该类别下有文章，无法删除");
        }
//        // 判断是否有图书
//        if(bookService.checkByCategory(id)){
//            return Result.failed("该类别下有图书，无法删除");
//        }
//        // 判断是否有笔记
//        if(bookNoteService.checkByCategory(id)) {
//            return Result.failed("该类别下有笔记，无法删除");
//        }

        iBlogCategoryInfoService.removeById(id);

        return Result.success();
    }

}
