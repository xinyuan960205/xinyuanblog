package cn.xinyuan.blog.admin.sys.service;

import cn.xinyuan.blog.common.util.PageUtils;
import cn.xinyuan.blog.entity.sys.DO.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: SysUserService
 * @Description: java类作用描述
 * @Author: xinyuan
 * @CreateDate: 2020/1/24 16:54
 */
public interface SysUserService extends IService<SysUser> {
    /**
     * 查询用户菜单
     * @param userId
     * @return
     */
    List<Integer> queryAllMenuId(Long userId);

    PageUtils queryPage(Map<String, Object> params);

    int deleteBatch(Long[] userIds);

    int saveUser(SysUser user);

    int updateUser(SysUser user);
}
