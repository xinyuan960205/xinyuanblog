package cn.xinyuan.blog.entity.sys.DO;

import cn.xinyuan.blog.common.base.baseEntity;
import cn.xinyuan.blog.common.validator.group.AddGroup;
import cn.xinyuan.blog.common.validator.group.UpdateGroup;
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
 * 角色菜单对应关系表
 * </p>
 *
 * @author xinyuan
 * @since 2020-03-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysRoleMenu extends baseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long roleId;

    /**
     * 菜单ID
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long menuId;

}
