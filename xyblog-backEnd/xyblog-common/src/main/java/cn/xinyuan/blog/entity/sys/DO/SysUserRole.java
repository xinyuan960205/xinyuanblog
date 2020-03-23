package cn.xinyuan.blog.entity.sys.DO;

import cn.xinyuan.blog.common.base.baseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 用户与角色对应关系
 * </p>
 *
 * @author xinyuan
 * @since 2020-03-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysUserRole extends baseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long userId;

    /**
     * 角色ID
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long roleId;


}
