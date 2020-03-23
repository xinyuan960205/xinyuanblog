package cn.xinyuan.blog.admin.sys.service;

import cn.xinyuan.blog.entity.sys.DO.SysMenu;
import cn.xinyuan.blog.entity.sys.DO.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @ClassName: SysMenuService
 * @Description: 系统菜单
 * @Author: xinyuan
 * @CreateDate: 2020/1/24 16:47
 */
public interface SysMenuService extends IService<SysMenu>{
    /**
     * 获取用户的所有菜单
     * @param userId
     * @return
     */
    List<SysMenu> listUserMenu(Long userId);

    /**
     * 根据父菜单，查询子菜单
     * @param parentId 父菜单ID
     * @param menuIdList  用户菜单ID
     */
    List<SysMenu> queryListParentId(Integer parentId, List<Integer> menuIdList);

    /**
     * 根据父菜单，查询子菜单
     * @param parentId 父菜单ID
     */
    List<SysMenu> queryListParentId(Integer parentId);

    /**
     * 获取不包含按钮的菜单列表
     */
    List<SysMenu> queryNotButtonList();

    /**
     * 获取用户菜单列表
     */
    List<SysMenu> getUserMenuList(Long userId);

}
