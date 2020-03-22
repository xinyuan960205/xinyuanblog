package cn.xinyuan.blog.entity.article.DO;

import cn.xinyuan.blog.common.base.baseEntity;
import cn.xinyuan.blog.common.validator.group.AddGroup;
import cn.xinyuan.blog.common.validator.group.UpdateGroup;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 文章信息表
 * </p>
 *
 * @author xinyuan
 * @since 2020-02-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BlogArticleInfo extends baseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 文章标题
     */
    @NotBlank(message="博文标题不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String title;

    /**
     * 文章摘要
     */
    private String summary;

    /**
     * 作者
     */
    private String author;

    /**
     * 所属分类，以，间隔
     */
    private String categoryId;

    /**
     * 发布状态
     */
    private Boolean isPublish;

    /**
     * 文章是否置顶
     */
    private Boolean isTop;

    /**
     * 文章是否推荐
     */
    private Boolean isRecommend;

    /**
     * 阅读数量
     */
    private Integer readNum;

    /**
     * 评论数量
     */
    private Integer commentNum;

    /**
     * 点赞数量
     */
    private Integer likeNum;
}
