package cn.xinyuan.blog.admin.operation.service.impl;

import cn.xinyuan.blog.admin.operation.service.IBlogArticleTagService;
import cn.xinyuan.blog.admin.operation.service.IBlogTagInfoService;
import cn.xinyuan.blog.entity.article.DTO.ArticleContentDTO;
import cn.xinyuan.blog.entity.operation.BlogArticleTag;
import cn.xinyuan.blog.entity.operation.BlogTagInfo;
import cn.xinyuan.blog.mapper.operation.BlogArticleTagMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 文章标签表 服务实现类
 * </p>
 *
 * @author xinyuan
 * @since 2020-03-14
 */
@Service
public class BlogArticleTagServiceImpl extends ServiceImpl<BlogArticleTagMapper, BlogArticleTag> implements IBlogArticleTagService {

    @Autowired
    private IBlogTagInfoService iBlogTagInfoService;

    @Autowired
    private BlogArticleTagMapper blogArticleTagMapper;

    @Override
    public int saveArticleTag(ArticleContentDTO article) {
        List<BlogTagInfo> blogTagInfos = article.getTagList();
        for (BlogTagInfo tagInfo : blogTagInfos) {
            BlogArticleTag blogArticleTag = new BlogArticleTag();
            blogArticleTag.setArticleId(article.getId());
            blogArticleTag.setTagId(tagInfo.getId());
            baseMapper.insert(blogArticleTag);
            //对应标签数+1
            iBlogTagInfoService.updateTagNum(tagInfo.getId(), 1);
        }
        return 1;
    }

    @Override
    public int deleteByArticleIds(Long[] articleIds) {
        for (Long id : articleIds) {
            //1. 先把taginfo表的数量-1
            List<BlogArticleTag> blogArticleTags = baseMapper.selectList(new QueryWrapper<BlogArticleTag>().lambda()
                    .eq(BlogArticleTag::getArticleId, id));
            for(BlogArticleTag blogArticleTag : blogArticleTags){
                iBlogTagInfoService.updateTagNum(blogArticleTag.getTagId(), -1);
            }
            //2. 删除对应记录
            baseMapper.delete(new QueryWrapper<BlogArticleTag>().lambda()
                    .eq(id != null, BlogArticleTag::getArticleId, id));

        }
        return 1;
    }

    @Override
    public List<BlogTagInfo> getTagsByArticleId(Long id) {
//        List<BlogArticleTag> blogArticleTags = baseMapper.selectList(new QueryWrapper<BlogArticleTag>().lambda()
//                .eq(id != null, BlogArticleTag::getArticleId, id));
        return blogArticleTagMapper.getTagsByArticleId(id);
    }
}
