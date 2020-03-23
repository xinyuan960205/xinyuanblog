package cn.xinyuan.blog.admin.sys.service.impl;

import cn.xinyuan.blog.admin.sys.service.ISysUserRoleService;
import cn.xinyuan.blog.entity.sys.DO.SysUser;
import cn.xinyuan.blog.entity.sys.DO.SysUserRole;
import cn.xinyuan.blog.mapper.sys.SysUserRoleMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 用户与角色对应关系 服务实现类
 * </p>
 *
 * @author xinyuan
 * @since 2020-03-19
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements ISysUserRoleService {

    /**
     * 根据用户id批量删除用户角色关系
     * @param userIds
     * @return
     */
    @Override
    public int deleteBatch(Long[] userIds) {
        for(Long userid : userIds){
            baseMapper.delete(new QueryWrapper<SysUserRole>().lambda()
                    .eq(SysUserRole::getUserId,userid));
        }
        return 1;
    }

    @Override
    public List<Integer> queryRoleIdList(Integer userId) {
        List<SysUserRole> sysUserRoles = baseMapper.selectList(new QueryWrapper<SysUserRole>().lambda()
                .eq(SysUserRole::getUserId, userId));
        List<Integer> list = new ArrayList<>();
        for(SysUserRole sysUserRole : sysUserRoles){
            list.add(Math.toIntExact(sysUserRole.getRoleId()));
        }
        return list;
    }

    @Override
    public int insertByUser(SysUser user) {
        List<Integer> roleIdList=user.getRoleIdList();
        if(!CollectionUtils.isEmpty(roleIdList)){
            for(Integer id : roleIdList){
                SysUserRole sysUserRole=new SysUserRole();
                sysUserRole.setRoleId(Long.valueOf(id));
                sysUserRole.setUserId(Long.valueOf(user.getId()));
                baseMapper.insert(sysUserRole);
            }
        }
        return 1;
    }
}
