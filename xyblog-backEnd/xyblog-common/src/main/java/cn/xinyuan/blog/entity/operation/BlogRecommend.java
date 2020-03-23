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
 * 推荐表
 * </p>
 *
 * @author xinyuan
 * @since 2020-03-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BlogRecommend extends baseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 推荐的文章id
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long linkId;

    /**
     * 推荐类型
     */
    private Integer type;

    /**
     * 顺序
     */
    private Integer orderNum;

    /**
     * 标题
     */
    private String title;

    /**
     * 置顶
     */
    private Boolean top;
}
