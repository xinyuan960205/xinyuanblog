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
 * 友链表
 * </p>
 *
 * @author xinyuan
 * @since 2020-03-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BlogLink extends baseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 友链名称
     */
    private String title;

    /**
     * 友链地址
     */
    private String url;

    /**
     * 头像
     */
    private String avator;
}
