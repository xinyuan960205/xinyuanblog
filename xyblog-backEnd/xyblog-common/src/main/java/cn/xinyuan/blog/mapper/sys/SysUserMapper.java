package cn.xinyuan.blog.mapper.sys;

import cn.xinyuan.blog.entity.sys.DO.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @ClassName: SysUserMapper
 * @Description: java类作用描述
 * @Author: xinyuan
 * @CreateDate: 2020/1/22 17:01
 */

public interface SysUserMapper extends BaseMapper<SysUser>{
    /**
     * 查询用户所有权限
     * @param userId
     * @return
     */
    List<String> queryAllPerms(Integer userId);

    /**
     * 查询用户的menuId
     * @param userId
     * @return
     */
    List<Integer> queryAllMenuId(Integer userId);
}
