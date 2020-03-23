package cn.xinyuan.blog.common.base;

import cn.xinyuan.blog.entity.sys.DO.SysUser;
import org.apache.shiro.SecurityUtils;

/**
 * @ClassName: AbstractController
 * @Description: java类作用描述
 * @Author: xinyuan
 * @CreateDate: 2020/1/23 21:36
 */
public class AbstractController {
    protected SysUser getUser(){
        return (SysUser) SecurityUtils.getSubject().getPrincipal();
    }

    protected Long getUserId(){
        return getUser().getId();
    }
}
