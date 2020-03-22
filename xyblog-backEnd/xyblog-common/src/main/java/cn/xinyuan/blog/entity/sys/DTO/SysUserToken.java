package cn.xinyuan.blog.entity.sys.DTO;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @ClassName: SysUserToken
 * @Description: 系统用户Token
 * @Author: xinyuan
 * @CreateDate: 2020/1/23 18:08
 */
@Data
@ApiModel(value="SysUserToken对象", description="系统用户Token")
public class SysUserToken {
    private static final long serialVersionUID = 1L;
    private Integer userId;
    private String token;
}
