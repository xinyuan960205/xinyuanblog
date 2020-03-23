package cn.xinyuan.blog.entity.article.DO;

import cn.xinyuan.blog.common.base.baseEntity;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 文章内容表
 * </p>
 *
 * @author xinyuan
 * @since 2020-02-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BlogArticleContent extends baseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 对应的文章ID
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long articleId;

    /**
     * 文章内容
     */
    @TableField(strategy = FieldStrategy.IGNORED)
    private String content;

    /**
     * 文章内容的网页显示格式
     */
    @TableField(strategy = FieldStrategy.IGNORED)
    private String contentFormat;
}
