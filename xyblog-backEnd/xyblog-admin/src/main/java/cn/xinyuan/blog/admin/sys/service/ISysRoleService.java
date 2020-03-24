package cn.xinyuan.blog.admin.sys.service;

import cn.xinyuan.blog.common.util.PageUtils;
import cn.xinyuan.blog.entity.sys.DO.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author xinyuan
 * @since 2020-03-19
 */
public interface ISysRoleService extends IService<SysRole> {

    PageUtils queryPage(Map<String, Object> params);

    int saveRole(SysRole role);

    int updateRole(SysRole role);

    int deleteBatch(Long[] roleIds);
}
