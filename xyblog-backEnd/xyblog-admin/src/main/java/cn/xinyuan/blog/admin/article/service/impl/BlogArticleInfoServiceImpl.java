package cn.xinyuan.blog.admin.article.service.impl;


import cn.xinyuan.blog.admin.article.service.IBlogArticleContentService;
import cn.xinyuan.blog.admin.article.service.IBlogArticleInfoService;
import cn.xinyuan.blog.admin.operation.service.IBlogArticleTagService;
import cn.xinyuan.blog.admin.operation.service.IBlogCategoryInfoService;
import cn.xinyuan.blog.admin.operation.service.IBlogTagInfoService;
import cn.xinyuan.blog.common.enums.ModuleEnum;
import cn.xinyuan.blog.common.util.PageUtils;
import cn.xinyuan.blog.common.util.Query;
import cn.xinyuan.blog.entity.article.DO.BlogArticleInfo;
import cn.xinyuan.blog.entity.article.DTO.ArticleContentDTO;
import cn.xinyuan.blog.entity.article.DTO.ArticleDTO;
import cn.xinyuan.blog.entity.operation.BlogTagInfo;
import cn.xinyuan.blog.mapper.article.BlogArticleInfoMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 文章信息表 服务实现类
 * </p>
 *
 * @author xinyuan
 * @since 2020-02-09
 */
@Service
public class BlogArticleInfoServiceImpl extends ServiceImpl<BlogArticleInfoMapper, BlogArticleInfo> implements IBlogArticleInfoService {

    @Autowired
    private IBlogArticleContentService iBlogArticleContentService;

    @Autowired
    private IBlogArticleTagService iBlogArticleTagService;

    @Autowired
    private IBlogTagInfoService iBlogTagInfoService;

    @Autowired
    private IBlogCategoryInfoService iBlogCategoryInfoService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ArticleDTO> page = new Query<ArticleDTO>(params).getPage();
        List<ArticleDTO> articleDTOList = baseMapper.listArticleDTO(page, params);
        page.setRecords(articleDTOList);
        //添加tagList
        for (ArticleDTO article : articleDTOList) {
            List<BlogTagInfo> tagList = iBlogArticleTagService.getTagsByArticleId(article.getId());
            article.setTagList(tagList);
            String categorysByArticleId = iBlogCategoryInfoService.getCategorysByArticleId(article.getCategoryId(), ModuleEnum.ARTICLE.getValue());
            article.setCategoryListStr(categorysByArticleId);
        }
        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteArticlePatch(Long[] articleIds) {
        this.baseMapper.deleteBatchIds(Arrays.asList(articleIds));
        iBlogArticleContentService.deleteBatchIds(articleIds);
        iBlogArticleTagService.deleteByArticleIds(articleIds);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveArticle(ArticleContentDTO article) {
        this.baseMapper.insert(article);
        iBlogArticleContentService.saveArticle(article);
        iBlogArticleTagService.saveArticleTag(article);
        return true;
    }

    @Override
    public ArticleContentDTO getArticle(Long articleId) {
        ArticleContentDTO articleContentDTO = new ArticleContentDTO();
        BlogArticleInfo blogArticleInfo = baseMapper.selectById(articleId);
        BeanUtils.copyProperties(blogArticleInfo, articleContentDTO);
        articleContentDTO.setTagList(iBlogArticleTagService.getTagsByArticleId(articleId));
        articleContentDTO.setContent(iBlogArticleContentService.getContentByArticleId(articleId));
        return articleContentDTO;
    }

    @Override
    public int updateArticle(ArticleContentDTO article) {
        //更新tag，先删除，再添加新的
        iBlogArticleTagService.deleteByArticleIds(new Long[]{article.getId()});
        iBlogArticleTagService.saveArticleTag(article);
        //更新文章内容
        iBlogArticleContentService.updateArticle(article);
        //更新文章信息
        baseMapper.updateById(article);
        return 1;
    }

    @Override
    public boolean checkByCategory(Long id) {
        List<BlogArticleInfo> list = baseMapper.selectList(new QueryWrapper<BlogArticleInfo>().lambda()
                .like(BlogArticleInfo::getCategoryId, id));
        return list.size()>0;
    }

//    /**
//     * 更改文章推荐状态，同时还要更改推荐列表
//     * @param article
//     * @return
//     */
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public int updateArticleRecommend(ArticleContentDTO article) {
//        baseMapper.updateById(article);
//        if(article.getIsRecommend()){//增加一条推荐信息
//            BlogRecommend blogRecommend = new BlogRecommend();
//            blogRecommend.setType(0);
//            blogRecommend.setLinkId(article.getId());
//            iBlogRecommendService.save(blogRecommend);
//        }else{
//            iBlogRecommendService.remove(new QueryWrapper<BlogRecommend>().lambda()
//                    .eq(BlogRecommend::getLinkId, article.getId()));
//        }
//        return 0;
//    }
}
