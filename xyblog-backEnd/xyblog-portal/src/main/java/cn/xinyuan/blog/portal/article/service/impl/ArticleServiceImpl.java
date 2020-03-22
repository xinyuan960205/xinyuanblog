package cn.xinyuan.blog.portal.article.service.impl;

import cn.xinyuan.blog.common.util.PageUtils;
import cn.xinyuan.blog.common.util.Query;
import cn.xinyuan.blog.entity.article.DO.BlogArticleInfo;
import cn.xinyuan.blog.entity.article.DTO.ArticleDTO;
import cn.xinyuan.blog.entity.article.VO.ArticleVO;
import cn.xinyuan.blog.mapper.article.BlogArticleInfoMapper;
import cn.xinyuan.blog.portal.article.service.ArticleService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: ArticleServiceImpl
 * @Description: java类作用描述
 * @Author: xinyuan
 * @CreateDate: 2020/2/10 17:40
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<BlogArticleInfoMapper, BlogArticleInfo> implements ArticleService {

    @Autowired
    private BlogArticleInfoMapper blogArticleInfoMapper;

    @Override
    public PageUtils getArticleList(Map<String, Object> params){
        Page<ArticleDTO> page = new Query<ArticleDTO>(params).getPage();
        List<ArticleDTO> list = this.baseMapper.queryArticlesCondition(page, params);
        page.setRecords(list);
        return new PageUtils(page);
    }

    @Override
    public ArticleVO getArticleById(Long articleId) {
        ArticleVO articleVO = blogArticleInfoMapper.queryArticleById(articleId);
        return articleVO;
    }


}
