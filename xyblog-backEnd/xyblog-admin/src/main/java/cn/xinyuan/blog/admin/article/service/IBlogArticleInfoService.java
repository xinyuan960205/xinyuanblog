package cn.xinyuan.blog.admin.article.service;

import cn.xinyuan.blog.common.util.PageUtils;
import cn.xinyuan.blog.entity.article.DO.BlogArticleInfo;
import cn.xinyuan.blog.entity.article.DTO.ArticleContentDTO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 文章信息表 服务类
 * </p>
 *
 * @author xinyuan
 * @since 2020-02-09
 */
public interface IBlogArticleInfoService extends IService<BlogArticleInfo> {
    PageUtils queryPage(Map<String, Object> params);
    boolean deleteArticlePatch(Long[] articleIds);
    boolean saveArticle(ArticleContentDTO article);

    ArticleContentDTO getArticle(Long articleId);

    int updateArticle(ArticleContentDTO article);

    boolean checkByCategory(Long id);
}
