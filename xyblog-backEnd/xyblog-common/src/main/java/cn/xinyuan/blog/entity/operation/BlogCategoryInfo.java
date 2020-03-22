package cn.xinyuan.blog.entity.operation;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 分类信息表
 * </p>
 *
 * @author xinyuan
 * @since 2020-03-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BlogCategoryInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键，自增
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 分类名称
     */
    private String categoryName;

    /**
     * 该分类下的文章数量
     */
    private Integer categoryNumber;

    /**
     * 分类等级
     */
    private Integer categoryRank;

    /**
     * 父目录，默认-1表示当前等级是最高，没有父目录
     */
    @TableField("parent_id")
    private Long parentId;

    /**
     * 创建时间
     */
    private Date createBy;

    /**
     * 修改时间
     */
    private Date modifiedBy;

    /**
     * 是否有效，默认为1有效，为0无效
     */
    private Boolean isEffective;

    /**
     * 类型
     */
    private int type;

    /**
     * 新加，上一级名称
     */
    @TableField(exist = false)
    private String parentName;
}
