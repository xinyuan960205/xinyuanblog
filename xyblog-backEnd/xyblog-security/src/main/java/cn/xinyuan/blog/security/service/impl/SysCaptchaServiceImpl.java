package cn.xinyuan.blog.security.service.impl;

import cn.xinyuan.blog.common.constants.RedisKeyConstants;
import cn.xinyuan.blog.common.exception.ErrorEnum;
import cn.xinyuan.blog.common.exception.MyException;
import cn.xinyuan.blog.common.util.RedisUtils;
import cn.xinyuan.blog.security.service.SysCaptchaService;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;

/**
 * @ClassName: SysCaptchaServiceImpl
 * @Description: java类作用描述
 * @Author: xinyuan
 * @CreateDate: 2020/1/22 21:18
 */
@Service
public class SysCaptchaServiceImpl implements SysCaptchaService {

    @Autowired
    private Producer producer;

    @Autowired
    private RedisUtils redisUtils;

    /**  验证码过期时长5秒 */
    public final static long CAPTCHA_EXPIRE = 60 * 5;

    @Override
    public BufferedImage getCaptcha(String uuid) {
        if(StringUtils.isEmpty(uuid)){
            throw new MyException(ErrorEnum.NO_UUID);
        }
        //生成文字验证码
        String code = producer.createText();
        // 存进redis,5分钟后过期
        redisUtils.set(genRedisKey(uuid),code,CAPTCHA_EXPIRE);
        return producer.createImage(code);
    }

    @Override
    public boolean validate(String uuid, String code) {
        if(StringUtils.isEmpty(uuid)||StringUtils.isEmpty(code)){
            return false;
        }
        // 从redis中取
        String redisKey=genRedisKey(uuid);
        String captchaCode=redisUtils.get(redisKey);
        //删除验证码
        redisUtils.delete(redisKey);
        if(code.equalsIgnoreCase(captchaCode)){
            return true;
        }
        return false;
    }

    /**
     * 生成redis key
     * @param uuid
     * @return
     */
    private String genRedisKey(String uuid){
        return RedisKeyConstants.MANAGE_SYS_CAPTCHA+uuid;
    }
}
