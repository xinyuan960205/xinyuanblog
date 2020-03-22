package cn.xinyuan.blog.admin.operation.service;

import cn.xinyuan.blog.entity.article.DTO.ArticleContentDTO;
import cn.xinyuan.blog.entity.operation.BlogArticleTag;
import cn.xinyuan.blog.entity.operation.BlogTagInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 文章标签表 服务类
 * </p>
 *
 * @author xinyuan
 * @since 2020-03-14
 */
public interface IBlogArticleTagService extends IService<BlogArticleTag> {

    int saveArticleTag(ArticleContentDTO article);

    int deleteByArticleIds(Long[] articleIds);

    List<BlogTagInfo> getTagsByArticleId(Long id);
}
