package cn.xinyuan.blog.admin.operation.service;

import cn.xinyuan.blog.entity.operation.BlogCategoryInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 分类信息表 服务类
 * </p>
 *
 * @author xinyuan
 * @since 2020-03-14
 */
public interface IBlogCategoryInfoService extends IService<BlogCategoryInfo> {

    List<BlogCategoryInfo> queryWithParentName(Map<String, Object> params);

    String getCategorysByArticleId(String id, int value);

    List<BlogCategoryInfo> queryListParentId(Integer id);
}
