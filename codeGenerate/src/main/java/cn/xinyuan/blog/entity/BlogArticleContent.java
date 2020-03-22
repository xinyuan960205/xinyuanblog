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
 * 文章内容表
 * </p>
 *
 * @author xinyuan
 * @since 2020-02-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BlogArticleContent implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键，自增
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 对应的文章ID
     */
    private Long articleId;

    /**
     * 文章内容
     */
    private String content;

    /**
     * 创建时间
     */
    private LocalDateTime createBy;

    /**
     * 修改时间
     */
    private LocalDateTime modifiedBy;


}
