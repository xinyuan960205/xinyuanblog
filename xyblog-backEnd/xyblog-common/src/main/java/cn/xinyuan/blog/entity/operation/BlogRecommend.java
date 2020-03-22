package cn.xinyuan.blog.entity.operation;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class BlogRecommend implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键，自增
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 推荐的文章id
     */
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

    /**
     * 创建时间
     */
    private Date createBy;

    /**
     * 修改时间
     */
    private Date modifiedBy;


}
