package cn.xinyuan.blog.entity.sys.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long userId;


    private String token;
}
