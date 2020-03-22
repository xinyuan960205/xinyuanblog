package cn.xinyuan.blog.security.service;

import java.awt.image.BufferedImage;

/**
 * @ClassName: SysCaptchaService
 * @Description: 验证码类
 * @Author: xinyuan
 * @CreateDate: 2020/1/22 21:17
 */
public interface SysCaptchaService {
    /**
     * 获取验证码
     * @param uuid
     * @return
     */
    BufferedImage getCaptcha(String uuid);

    /**
     * 验证验证码
     * @param uuid
     * @param code
     * @return
     */
    boolean validate(String uuid, String code);
}
