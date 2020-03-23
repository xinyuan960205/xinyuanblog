package cn.xinyuan.blog.entity.operation;

import cn.xinyuan.blog.common.base.baseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

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
public class BlogArticleTag extends baseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 标签id
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long tagId;

    /**
     * 文章id
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long articleId;

    /**
     * 是否有效，默认为1有效，为0无效
     */
    private Boolean isEffective;
}
