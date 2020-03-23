package cn.xinyuan.blog.entity.operation;

import cn.xinyuan.blog.common.base.baseEntity;
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
 * 标签信息表
 * </p>
 *
 * @author xinyuan
 * @since 2020-03-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BlogTagInfo extends baseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 标签名称
     */
    private String tagName;

    /**
     * 标签类型，文章0，读后感1
     */
    private Integer type;

    /**
     * 该标签下的文章数量
     */
    private Integer tagNumber;

    /**
     * 是否有效，默认为1有效，为0无效
     */
    private Boolean isEffective;
}
