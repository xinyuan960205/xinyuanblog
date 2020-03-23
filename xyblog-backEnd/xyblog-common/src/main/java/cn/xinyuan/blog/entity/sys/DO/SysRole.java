package cn.xinyuan.blog.entity.sys.DO;

import cn.xinyuan.blog.common.base.baseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author xinyuan
 * @since 2020-03-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysRole extends baseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建者ID
     */
    private String createUserId;

    @TableField(exist=false)
    private List<Integer> menuIdList;
}
