package cn.xinyuan.blog.portal.article.service;

import cn.xinyuan.blog.common.util.PageUtils;
import cn.xinyuan.blog.entity.article.DO.BlogArticleInfo;
import cn.xinyuan.blog.entity.article.VO.ArticleVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * @ClassName: ArticleService
 * @Description: java类作用描述
 * @Author: xinyuan
 * @CreateDate: 2020/2/10 17:40
 */
public interface ArticleService extends IService<BlogArticleInfo> {

    PageUtils getArticleList(Map<String, Object> params);

    ArticleVO getArticleById(Long articleId);
}
