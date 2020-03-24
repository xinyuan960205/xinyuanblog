package cn.xinyuan.blog.entity.sys.DO;

import cn.xinyuan.blog.common.base.baseEntity;
import cn.xinyuan.blog.common.validator.group.AddGroup;
import cn.xinyuan.blog.common.validator.group.UpdateGroup;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * @ClassName: SysUser
 * @Description: 用户表
 * @Author: xinyuan
 * @CreateDate: 2020/1/24 14:26
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value="SysUser对象", description="用户管理")
public class SysUser extends baseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "用户名不能为空" , groups = {AddGroup.class, UpdateGroup.class})
    @ApiModelProperty(value = "用户名")
    private String username;

    @NotBlank(message = "密码不能为空" ,groups = AddGroup.class)
    @ApiModelProperty(value = "密码")
    private String password;

    @NotBlank(message="邮箱不能为空", groups = {AddGroup.class, UpdateGroup.class})
    @Email(message="邮箱格式不正确", groups = {AddGroup.class, UpdateGroup.class})
    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "密码盐")
    private String salt;

    @ApiModelProperty(value = "0禁用，1正常")
    private Integer status;

    @ApiModelProperty(value = "创建者Id")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long createUserId;

    @TableField(exist=false)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private List<Long> roleIdList;

}
