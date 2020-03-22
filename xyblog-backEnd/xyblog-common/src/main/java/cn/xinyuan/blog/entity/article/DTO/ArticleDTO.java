package cn.xinyuan.blog.entity.article.DTO;

import cn.xinyuan.blog.entity.article.DO.BlogArticleInfo;
import cn.xinyuan.blog.entity.operation.BlogTagInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @ClassName: ArticleDTO
 * @Description: 文章展示数据列表封装
 * @Author: xinyuan
 * @CreateDate: 2020/2/10 10:36
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ArticleDTO extends BlogArticleInfo{
    private List<BlogTagInfo> tagList;

    /**
     * 所属分类，以逗号分隔
     */
    private String categoryListStr;
}
