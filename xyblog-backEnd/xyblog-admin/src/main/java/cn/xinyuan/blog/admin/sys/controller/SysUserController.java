package cn.xinyuan.blog.admin.sys.controller;

import cn.xinyuan.blog.admin.sys.service.ISysUserRoleService;
import cn.xinyuan.blog.admin.sys.service.SysUserService;
import cn.xinyuan.blog.common.api.Result;
import cn.xinyuan.blog.common.base.AbstractController;
import cn.xinyuan.blog.common.constants.SysConstants;
import cn.xinyuan.blog.common.util.PageUtils;
import cn.xinyuan.blog.common.validator.ValidatorUtils;
import cn.xinyuan.blog.common.validator.group.AddGroup;
import cn.xinyuan.blog.common.validator.group.UpdateGroup;
import cn.xinyuan.blog.entity.sys.DO.SysRole;
import cn.xinyuan.blog.entity.sys.DO.SysUser;
import cn.xinyuan.blog.entity.sys.DTO.PasswordForm;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: SysUserController
 * @Description: java类作用描述
 * @Author: xinyuan
 * @CreateDate: 2020/1/24 14:04
 */
@RestController
@RequestMapping("/admin/sys/user")
public class SysUserController extends AbstractController{

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private ISysUserRoleService iSysUserRoleService;

    /**
     * 获取登录的用户信息
     */
    @GetMapping("/info")
    public Result info(){
        return Result.success(getUser());
    }

    /**
     * 所有用户列表
     */
    @GetMapping("/list")
    public Result list(@RequestParam Map<String, Object> params){
        //只有超级管理员，才能查看所有管理员列表
        if(SysConstants.SUPER_ADMIN.equals(getUserId())){
            params.put("createUserId", getUserId());
        }
        PageUtils page = sysUserService.queryPage(params);

        return Result.success(page);
    }

    /**
     * 修改密码
     * @param passwordForm
     * @return
     */
    @PutMapping("/password")
    public Result password(@RequestBody PasswordForm passwordForm){
        if(StringUtils.isEmpty(passwordForm.getNewPassword())) {
            return Result.failed("新密码不能为空");
        }
        //sha256加密
        String password = new Sha256Hash(passwordForm.getPassword(), getUser().getSalt()).toHex();
        //sha256加密
        String newPassword = new Sha256Hash(passwordForm.getNewPassword(), getUser().getSalt()).toHex();

        boolean flag = sysUserService.updatePassword(getUserId(), password, newPassword);
        if(!flag){
            return Result.failed("原密码不正确");
        }

        return Result.success();
    }

    /**
     * 保存用户
     */
    @PostMapping("/save")
    @RequiresPermissions("sys:user:save")
    public Result save(@RequestBody SysUser user){
        ValidatorUtils.validateEntity(user, AddGroup.class);

        //设置该用户的创建人
        user.setCreateUserId(getUserId());
        //将密码使用Sha256加密
        String password = new Sha256Hash(user.getPassword(), getUser().getSalt()).toHex();
        user.setPassword(password);
        sysUserService.saveUser(user);

        return Result.success();
    }

    /**
     * 修改用户
     */
    @PostMapping("/update")
    @RequiresPermissions("sys:user:update")
    public Result update(@RequestBody SysUser user){
        ValidatorUtils.validateEntity(user, UpdateGroup.class);

        user.setCreateUserId(getUserId());
        sysUserService.updateUser(user);

        return Result.success();
    }

    /**
     * 删除用户
     */
    @PostMapping("/delete")
    @RequiresPermissions("sys:user:delete")
    public Result delete(@RequestBody Long[] userIds){
        if(ArrayUtils.contains(userIds, SysConstants.SUPER_ADMIN)){
            return Result.failed("系统管理员不能删除");
        }

        if(ArrayUtils.contains(userIds, getUserId())){
            return Result.failed("当前用户不能删除");
        }

        sysUserService.deleteBatch(userIds);

        return Result.success();
    }

    /**
     * 用户信息
     */
    @GetMapping("/info/{userId}")
    @RequiresPermissions("sys:user:info")
    public Result info(@PathVariable("userId") Long userId){
        SysUser user = sysUserService.getById(userId);

        //获取用户所属的角色列表
        List<Long> roleIdList = iSysUserRoleService.queryRoleIdList(userId);
        user.setRoleIdList(roleIdList);

        return Result.success(user);
    }

}
