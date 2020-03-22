package cn.xinyuan.blog.security.controller;

import cn.xinyuan.blog.common.api.Result;
import cn.xinyuan.blog.common.base.AbstractController;
import cn.xinyuan.blog.common.exception.ErrorEnum;
import cn.xinyuan.blog.entity.sys.DO.SysUser;
import cn.xinyuan.blog.entity.sys.Vo.SysLoginForm;
import cn.xinyuan.blog.mapper.sys.SysUserMapper;
import cn.xinyuan.blog.security.service.SysCaptchaService;
import cn.xinyuan.blog.security.service.SysUserTokenService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @ClassName: SysLoginController
 * @Description: login界面接口
 * @Author: xinyuan
 * @CreateDate: 2020/1/22 14:30
 */
@RestController
public class SysLoginController extends AbstractController{

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysCaptchaService sysCaptchaService;

    @Autowired
    private SysUserTokenService sysUserTokenService;

    @GetMapping("captcha.jpg")
    public void captcha(HttpServletResponse response, String uuid) throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        //获取图片验证码
        BufferedImage image = sysCaptchaService.getCaptcha(uuid);

        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        IOUtils.closeQuietly(out);
    }

    @PostMapping("admin/sys/login")
    public Result login(@RequestBody SysLoginForm sysLoginForm){
        //验证码
        Boolean captcha = sysCaptchaService.validate(sysLoginForm.getUuid(), sysLoginForm.getCaptcha());
        if(!captcha){
            //验证码不正确
            return Result.failed(ErrorEnum.CAPTCHA_WRONG);
        }
        //用户信息
        SysUser user = sysUserMapper.selectOne(new QueryWrapper<SysUser>()
                .lambda()
                .eq(SysUser :: getUsername, sysLoginForm.getUsername()));
        if(user == null || !user.getPassword().equals(sysLoginForm.getPassword())){
            //用户名或密码错误
            return Result.failed(ErrorEnum.USERNAME_OR_PASSWORD_WRONG);
        }
        if(user.getStatus() == 0){
            return Result.failed(ErrorEnum.ACCOUNT_LOCK);
        }

        //生成token,并保存到redis
        return sysUserTokenService.createToken(user.getId());
    }

    /**
     * 退出
     */
    @PostMapping("/sys/logout")
    public Result logout() {
        sysUserTokenService.logout(getUserId());
        return Result.success("success");
    }
}
