package cn.xinyuan.blog.admin.sys.service.impl;

import cn.xinyuan.blog.admin.sys.service.ISysRoleMenuService;
import cn.xinyuan.blog.entity.sys.DO.SysMenu;
import cn.xinyuan.blog.entity.sys.DO.SysRole;
import cn.xinyuan.blog.entity.sys.DO.SysRoleMenu;
import cn.xinyuan.blog.mapper.sys.SysRoleMenuMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 角色菜单对应关系表 服务实现类
 * </p>
 *
 * @author xinyuan
 * @since 2020-03-19
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements ISysRoleMenuService {

    /**
     * 根据角色添加对应关系
     * @param role
     * @return
     */
    @Override
    public int insertByRole(SysRole role) {
        List<Integer> menuIdList = role.getMenuIdList();
        if(!CollectionUtils.isEmpty(menuIdList)){
            for(Integer id : menuIdList){
                if(id==-666666) continue;
                SysRoleMenu sysRoleMenu = new SysRoleMenu();
                sysRoleMenu.setMenuId(Long.valueOf(id));
                sysRoleMenu.setRoleId(role.getId());
                baseMapper.insert(sysRoleMenu);
            }
        }
        return 1;
    }

    @Override
    public int deleteBatch(Integer[] roleIdList) {
        for(Integer id : roleIdList){
            baseMapper.delete(new QueryWrapper<SysRoleMenu>().lambda()
                    .eq(SysRoleMenu::getRoleId, id));
        }
        return 1;
    }

    @Override
    public List<Integer> queryMenuIdList(Integer roleId) {
        List<SysRoleMenu> sysRoleMenus = baseMapper.selectList(new QueryWrapper<SysRoleMenu>().lambda()
                .eq(SysRoleMenu::getRoleId, roleId));
        List<Integer> menuList=new ArrayList<>();
        for(SysRoleMenu sysRoleMenu : sysRoleMenus){
            menuList.add(Math.toIntExact(sysRoleMenu.getMenuId()));
        }
        return menuList;
    }
}
