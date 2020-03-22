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
 * 文章标签表
 * </p>
 *
 * @author xinyuan
 * @since 2020-03-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BlogArticleTag implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键，自增
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 标签id
     */
    private Long tagId;

    /**
     * 文章id
     */
    private Long articleId;

    /**
     * 创建时间
     */
    private LocalDateTime createBy;

    /**
     * 修改时间
     */
    private LocalDateTime modifiedBy;

    /**
     * 是否有效，默认为1有效，为0无效
     */
    private Boolean isEffective;


}
