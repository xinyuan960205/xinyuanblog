package cn.xinyuan.blog.admin.sys.service;

import cn.xinyuan.blog.entity.sys.DO.SysUser;
import cn.xinyuan.blog.entity.sys.DO.SysUserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户与角色对应关系 服务类
 * </p>
 *
 * @author xinyuan
 * @since 2020-03-19
 */
public interface ISysUserRoleService extends IService<SysUserRole> {

    int deleteBatch(Integer[] userIds);

    List<Integer> queryRoleIdList(Integer userId);

    int insertByUser(SysUser user);
}
