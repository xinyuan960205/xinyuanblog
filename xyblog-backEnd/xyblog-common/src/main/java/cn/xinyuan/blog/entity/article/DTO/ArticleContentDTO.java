package cn.xinyuan.blog.entity.article.DTO;

import cn.xinyuan.blog.common.validator.group.AddGroup;
import cn.xinyuan.blog.common.validator.group.UpdateGroup;
import cn.xinyuan.blog.entity.article.DO.BlogArticleInfo;
import cn.xinyuan.blog.entity.operation.BlogTagInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @ClassName: ArticleContentDTO
 * @Description: 带有文章内容的实体类，主要用于保存文章，显示文章
 * @Author: xinyuan
 * @CreateDate: 2020/2/10 15:01
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ArticleContentDTO extends BlogArticleInfo {

    private List<BlogTagInfo> tagList;

    @NotBlank(message="博文内容不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String content;

    @NotBlank(message="博文内容格式不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String contentFormat;
}
