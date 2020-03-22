package cn.xinyuan.blog.admin.sys.service;

import cn.xinyuan.blog.entity.sys.DO.SysRole;
import cn.xinyuan.blog.entity.sys.DO.SysRoleMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色菜单对应关系表 服务类
 * </p>
 *
 * @author xinyuan
 * @since 2020-03-19
 */
public interface ISysRoleMenuService extends IService<SysRoleMenu> {

    int insertByRole(SysRole role);

    int deleteBatch(Integer[] roleIdList);

    List<Integer> queryMenuIdList(Integer roleId);
}
