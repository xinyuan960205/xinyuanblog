package cn.xinyuan.blog.admin.article.service;

import cn.xinyuan.blog.entity.article.DO.BlogArticleContent;
import cn.xinyuan.blog.entity.article.DTO.ArticleContentDTO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 文章内容表 服务类
 * </p>
 *
 * @author xinyuan
 * @since 2020-02-10
 */
public interface IBlogArticleContentService extends IService<BlogArticleContent> {
    boolean saveArticle(ArticleContentDTO article);

    int deleteBatchIds(Long[] articleIds);

    String getContentByArticleId(Long articleId);

    int updateArticle(ArticleContentDTO article);
}
