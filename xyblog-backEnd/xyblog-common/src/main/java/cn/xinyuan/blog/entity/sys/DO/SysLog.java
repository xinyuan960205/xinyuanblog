package cn.xinyuan.blog.entity.sys.DO;

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
 * 日志表
 * </p>
 *
 * @author xinyuan
 * @since 2020-03-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysLog extends baseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 操作地址的IP
     */
    private String logIp;

    /**
     * 操作内容
     */
    private String remark;

    /**
     * 操作的访问地址
     */
    private String operateUrl;

    /**
     * 操作的浏览器
     */
    private String operateBy;

}
