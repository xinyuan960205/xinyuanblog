package cn.xinyuan.blog.security.service;

import cn.xinyuan.blog.entity.sys.DO.SysUser;
import cn.xinyuan.blog.entity.sys.DTO.SysUserToken;

import java.util.Set;

/**
 * ShiroService
 *
 * @author bobbi
 * @date 2018/10/08 19:58
 * @email 571002217@qq.com
 * @description service接口类
 */
public interface ShiroService {

    /**
     * 获取用户的所有权限
     * @param userId
     * @return
     */
    Set<String> getUserPermissions(Long userId);

    /**
     * 查询token
     * @param token
     * @return
     */
    SysUserToken queryByToken(String token);

    /**
     * 查询用户信息
     * @param userId
     * @return
     */
    SysUser queryUser(Long userId);

    /**
     * 续期
     * @param userId
     * @param accessToken
     */
    void refreshToken(Long userId, String accessToken);
}
