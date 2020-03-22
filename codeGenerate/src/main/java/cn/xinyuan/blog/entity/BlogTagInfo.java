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
 * 标签信息表
 * </p>
 *
 * @author xinyuan
 * @since 2020-03-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BlogTagInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键，自增
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 标签名称
     */
    private String tagName;

    /**
     * 该标签下的文章数量
     */
    private Integer tagNumber;

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
