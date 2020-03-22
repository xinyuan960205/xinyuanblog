package cn.xinyuan.blog.admin.sys.service.impl;

import cn.xinyuan.blog.admin.sys.service.ISysRoleMenuService;
import cn.xinyuan.blog.admin.sys.service.ISysRoleService;
import cn.xinyuan.blog.admin.sys.service.SysMenuService;
import cn.xinyuan.blog.common.util.PageUtils;
import cn.xinyuan.blog.common.util.Query;
import cn.xinyuan.blog.entity.sys.DO.SysRole;
import cn.xinyuan.blog.mapper.sys.SysRoleMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Map;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author xinyuan
 * @since 2020-03-19
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Autowired
    private ISysRoleMenuService iSysRoleMenuService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String roleName = (String) params.get("roleName");
        Long createUserId = (Long) params.get("createUserId");
        IPage<SysRole> iPage = baseMapper.selectPage(new Query<SysRole>(params).getPage()
                , new QueryWrapper<SysRole>().lambda()
                        .like(StringUtils.isNotEmpty(roleName), SysRole::getRoleName, roleName)
                        .eq(createUserId!=null,SysRole::getCreateUserId,createUserId));
        return new PageUtils(iPage);
    }

    /**
     * 保存角色，并保存角色菜单关系数据
     * @param role
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int saveRole(SysRole role) {
        baseMapper.insert(role);
        //保存角色菜单关系数据
        iSysRoleMenuService.insertByRole(role);
        return 1;
    }

    /**
     * 修改角色，并修改角色菜单关系数据
     * @param role
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateRole(SysRole role) {
        baseMapper.updateById(role);
        //关系数据先删除，再添加
        iSysRoleMenuService.deleteBatch(new Integer[]{Math.toIntExact(role.getId())});
        return 1;
    }

    /**
     * 根据角色id删除角色，并删除角色菜单关系
     * @param roleIds
     * @return
     */
    @Override
    public int deleteBatch(Integer[] roleIds) {
        baseMapper.deleteBatchIds(Arrays.asList(roleIds));
        iSysRoleMenuService.deleteBatch(roleIds);
        return 1;
    }
}
