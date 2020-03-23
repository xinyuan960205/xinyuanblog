package cn.xinyuan.blog.security.service;

import cn.xinyuan.blog.common.api.Result;
import cn.xinyuan.blog.entity.sys.DTO.SysUserToken;

/**
 * @ClassName: SysUserTokenService
 * @Description: java类作用描述
 * @Author: xinyuan
 * @CreateDate: 2020/1/22 22:35
 */
public interface SysUserTokenService {
    /**
     * 生成Token
     * @param userId
     * @return
     */
    Result createToken(Long userId);

    /**
     * 查询token
     * @param token
     * @return
     */
    SysUserToken queryByToken(String token);

    /**
     * 退出登录
     * @param userId
     */
    void logout(Long userId);

    /**
     * 续期
     * @param userId
     * @param token
     */
    void refreshToken(Long userId, String token);
}
