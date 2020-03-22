package cn.xinyuan.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
public class BlogArticleInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键，文章ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 文章标题
     */
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
     * 发布状态
     */
    private Boolean isPublish;

    /**
     * 文章是否置顶
     */
    private Boolean isTop;

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

    /**
     * 创建时间
     */
    private LocalDateTime createBy;

    /**
     * 修改时间
     */
    private LocalDateTime modifiedBy;


}
