package cn.xinyuan.blog.admin.sys.service.impl;

import cn.xinyuan.blog.admin.sys.service.SysMenuService;
import cn.xinyuan.blog.admin.sys.service.SysUserService;
import cn.xinyuan.blog.common.constants.SysConstants;
import cn.xinyuan.blog.common.enums.MenuTypeEnum;
import cn.xinyuan.blog.entity.sys.DO.SysMenu;
import cn.xinyuan.blog.entity.sys.DO.SysRole;
import cn.xinyuan.blog.mapper.sys.SysMenuMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @ClassName: SysMenuServiceImpl
 * @Description: java类作用描述
 * @Author: xinyuan
 * @CreateDate: 2020/1/24 16:48
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService{

    @Autowired
    private SysUserService sysUserService;

//    @Autowired
//    private SysRoleMenuService sysRoleMenuService;


    /**
     * 获取用户的所有菜单
     *
     * @param userId
     * @return
     */
    @Override
    public List<SysMenu> listUserMenu(Long userId) {
        //系统管理员，拥有最高权限
        if(userId.equals(SysConstants.SUPER_ADMIN)){
            return getAllMenuList(null);
        }
        //用户菜单列表
        List<Integer> menuIdList = sysUserService.queryAllMenuId(userId);
        return getAllMenuList(menuIdList);
    }

    private List<SysMenu> getAllMenuList(List<Integer> menuIdList) {
        //查询用户所属所有目录
        List<SysMenu> menuList = queryListParentId(0, menuIdList);
        //递归生成特定格式的菜单列表
        getMenuTreeList(menuList, menuIdList);

        return menuList;
    }

    /**
     * 递归
     */
    private List<SysMenu> getMenuTreeList(List<SysMenu> menuList, List<Integer> menuIdList){
        List<SysMenu> subMenuList = new ArrayList<>();

        for(SysMenu entity : menuList){
            //目录
            if(entity.getType() == MenuTypeEnum.CATALOG.getValue()){
                entity.setList(getMenuTreeList(queryListParentId(entity.getMenuId(), menuIdList), menuIdList));
            }
            subMenuList.add(entity);
        }

        return subMenuList;
    }

    /**
     * 根据父菜单，查询子菜单(用于鉴权)
     *
     * @param parentId   父菜单ID
     * @param menuIdList 用户菜单ID
     */
    @Override
    public List<SysMenu> queryListParentId(Integer parentId, List<Integer> menuIdList) {
        List<SysMenu> menuList = queryListParentId(parentId);
        if(menuIdList == null){
            return menuList;
        }

        List<SysMenu> userMenuList = new ArrayList<>();
        for(SysMenu menu : menuList){
            if(menuIdList.contains(menu.getMenuId())){
                userMenuList.add(menu);
            }
        }
        return userMenuList;
    }

    /**
     * 根据父菜单，查询子菜单
     *
     * @param parentId 父菜单ID
     */
    @Override
    public List<SysMenu> queryListParentId(Integer parentId) {
        return baseMapper.queryListParentId(parentId);
    }

    /**
     * 获取不包含按钮的菜单列表
     */
    @Override
    public List<SysMenu> queryNotButtonList() {
        return baseMapper.queryNotButtonList();
    }

    /**
     * 获取用户菜单列表
     *
     * @param userId
     */
    @Override
    public List<SysMenu> getUserMenuList(Long userId) {
        return null;
    }


}
