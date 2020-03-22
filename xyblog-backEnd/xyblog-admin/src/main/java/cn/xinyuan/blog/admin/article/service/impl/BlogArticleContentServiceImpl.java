package cn.xinyuan.blog.admin.article.service.impl;

import cn.xinyuan.blog.admin.article.service.IBlogArticleContentService;
import cn.xinyuan.blog.entity.article.DO.BlogArticleContent;
import cn.xinyuan.blog.entity.article.DTO.ArticleContentDTO;
import cn.xinyuan.blog.mapper.article.BlogArticleContentMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 文章内容表 服务实现类
 * </p>
 *
 * @author xinyuan
 * @since 2020-02-10
 */
@Service
public class BlogArticleContentServiceImpl extends ServiceImpl<BlogArticleContentMapper, BlogArticleContent> implements IBlogArticleContentService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveArticle(ArticleContentDTO article){
        BlogArticleContent blogArticleContent = new BlogArticleContent();
        blogArticleContent.setArticleId(article.getId());
        blogArticleContent.setContent(article.getContent());
        blogArticleContent.setContentFormat(article.getContentFormat());
        this.baseMapper.insert(blogArticleContent);
        return true;
    }

    @Override
    public int deleteBatchIds(Long[] articleIds) {
        if(articleIds==null || articleIds.length==0) return 0;
        for(Long id : articleIds){
            this.baseMapper.delete(new QueryWrapper<BlogArticleContent>().eq("article_id",id));
        }

        return 1;
    }

    @Override
    public String getContentByArticleId(Long articleId) {
        BlogArticleContent blogArticleContent = baseMapper.selectOne(new QueryWrapper<BlogArticleContent>().lambda()
                .eq(BlogArticleContent::getArticleId, articleId));
        return blogArticleContent.getContent();
    }

    @Override
    public int updateArticle(ArticleContentDTO article) {
        BlogArticleContent blogArticleContent = baseMapper.selectOne(new QueryWrapper<BlogArticleContent>().lambda()
                .eq(BlogArticleContent::getArticleId, article.getId()));
        blogArticleContent.setContent(article.getContent());
        blogArticleContent.setContentFormat(article.getContentFormat());
        baseMapper.updateById(blogArticleContent);
        return 1;
    }
}
