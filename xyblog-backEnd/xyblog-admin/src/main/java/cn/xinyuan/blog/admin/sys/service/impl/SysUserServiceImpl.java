package cn.xinyuan.blog.admin.sys.service.impl;

import cn.xinyuan.blog.admin.sys.service.ISysUserRoleService;
import cn.xinyuan.blog.admin.sys.service.SysUserService;
import cn.xinyuan.blog.common.util.PageUtils;
import cn.xinyuan.blog.common.util.Query;
import cn.xinyuan.blog.entity.sys.DO.SysUser;
import cn.xinyuan.blog.entity.sys.DO.SysUserRole;
import cn.xinyuan.blog.mapper.sys.SysUserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.models.auth.In;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: SysUserServiceImpl
 * @Description: java类作用描述
 * @Author: xinyuan
 * @CreateDate: 2020/2/9 11:38
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private ISysUserRoleService iSysUserRoleService;

    /**
     * 查询用户菜单
     *
     * @param userId
     * @return
     */
    @Override
    public List<Integer> queryAllMenuId(Integer userId) {
        return baseMapper.queryAllMenuId(userId);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String username = (String) params.get("username");
        Integer createUserId = (Integer) params.get("createUserId");
        IPage<SysUser> iPage = baseMapper.selectPage(
                new Query<SysUser>(params).getPage(),
                new QueryWrapper<SysUser>().lambda()
                        .like(StringUtils.isNotEmpty(username), SysUser::getUsername, username)
                        .eq(createUserId != null, SysUser::getCreateUserId, createUserId));
        return new PageUtils(iPage);
    }

    /**
     * 批量删除用户，并删除用户角色关系
     * @param userIds
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteBatch(Integer[] userIds) {
        baseMapper.deleteBatchIds(Arrays.asList(userIds));
        iSysUserRoleService.deleteBatch(userIds);
        return 1;
    }

    /**
     * 保存用户信息，同时保存用户角色关系
     * @param user
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int saveUser(SysUser user) {
        baseMapper.insert(user);
        iSysUserRoleService.insertByUser(user);
        return 1;
    }

    /**
     * 修改用户信息，同时修改用户角色关系
     * @param user
     * @return
     */
    @Override
    public int updateUser(SysUser user) {
        baseMapper.updateById(user);
        //先删除，再添加
        iSysUserRoleService.deleteBatch(new Integer[]{user.getId()});
        iSysUserRoleService.insertByUser(user);
        return 1;
    }
}
