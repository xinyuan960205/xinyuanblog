package cn.xinyuan.blog.security.service.impl;

import cn.xinyuan.blog.common.api.Result;
import cn.xinyuan.blog.common.constants.RedisKeyConstants;
import cn.xinyuan.blog.common.util.RedisUtils;
import cn.xinyuan.blog.entity.sys.DTO.SysUserToken;
import cn.xinyuan.blog.security.service.SysUserTokenService;
import cn.xinyuan.blog.security.util.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: SysUserTokenServiceImpl
 * @Description: java类作用描述
 * @Author: xinyuan
 * @CreateDate: 2020/1/22 22:35
 */
@Service
public class SysUserTokenServiceImpl implements SysUserTokenService {

    //12小时后过期
    private final static int EXPIRE = 3600 * 12;

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 生成Token
     *
     * @param userId
     * @return
     */
    @Override
    public Result createToken(Integer userId) {
        //生成一个token
        String token = TokenGenerator.generateValue();

        String tokenKey= RedisKeyConstants.MANAGE_SYS_USER_TOKEN+token;
        String userIdKey= RedisKeyConstants.MANAGE_SYS_USER_TOKEN+userId;

        //判断是否生成过token
        String tokenInRedis=redisUtils.get(userIdKey);
        if(!StringUtils.isEmpty(tokenInRedis)){
            // 将原来的token删除
            redisUtils.delete(RedisKeyConstants.MANAGE_SYS_USER_TOKEN+tokenInRedis);
        }
        // 将token存进redis
        redisUtils.set(tokenKey,userId,EXPIRE);
        redisUtils.set(userIdKey,token,EXPIRE);

        Map<String, Object> map = new HashMap<>();
        map.put("token",token);
        map.put("expire", EXPIRE);

        return Result.success(map);
    }

    /**
     * 查询token
     *
     * @param token
     * @return
     */
    @Override
    public SysUserToken queryByToken(String token) {
        String userId=redisUtils.get(token);
        if(StringUtils.isEmpty(userId)){
            return null;
        }
        SysUserToken sysUserToken=new SysUserToken();
        sysUserToken.setToken(token);
        sysUserToken.setUserId(Integer.parseInt(userId));
        return sysUserToken;
    }

    /**
     * 退出登录
     *
     * @param userId
     */
    @Override
    public void logout(Integer userId) {
        String userIdKey= RedisKeyConstants.MANAGE_SYS_USER_TOKEN+userId;
        String token=redisUtils.get(userIdKey);
        String tokenKey= RedisKeyConstants.MANAGE_SYS_USER_TOKEN+token;
        redisUtils.delete(userIdKey);
        redisUtils.delete(tokenKey);
    }

    /**
     * 续期
     *
     * @param userId
     * @param token
     */
    @Override
    public void refreshToken(Integer userId, String token) {
        String tokenKey= RedisKeyConstants.MANAGE_SYS_USER_TOKEN+token;
        String userIdKey= RedisKeyConstants.MANAGE_SYS_USER_TOKEN+userId;
        redisUtils.updateExpire(tokenKey);
        redisUtils.updateExpire(userIdKey);
    }
}
