package cn.xinyuan.blog.entity.sys.Vo;

import lombok.Data;

/**
 * @ClassName: SysLoginForm
 * @Description: java类作用描述
 * @Author: xinyuan
 * @CreateDate: 2020/1/22 16:12
 */
@Data
public class SysLoginForm {
    private String username;
    private String password;
    private String captcha;
    private String uuid;
}
