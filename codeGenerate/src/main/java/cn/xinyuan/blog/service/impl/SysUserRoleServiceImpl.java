package cn.xinyuan.blog.service.impl;

import cn.xinyuan.blog.entity.SysUserRole;
import cn.xinyuan.blog.mapper.SysUserRoleMapper;
import cn.xinyuan.blog.service.ISysUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
