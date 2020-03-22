package cn.xinyuan.blog.entity.article.VO;

import cn.xinyuan.blog.entity.article.DO.BlogArticleInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @ClassName: ArticleVO
 * @Description: 文章内容显示数据
 * @Author: xinyuan
 * @CreateDate: 2020/3/13 14:36
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ArticleVO extends BlogArticleInfo{
    /**
     * 文章内容
     */
    private String contentFormat;
}
