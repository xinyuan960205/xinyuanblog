package cn.xinyuan.blog.admin.sys.controller;

import cn.xinyuan.blog.admin.sys.service.ISysRoleMenuService;
import cn.xinyuan.blog.admin.sys.service.ISysRoleService;
import cn.xinyuan.blog.admin.sys.service.SysMenuService;
import cn.xinyuan.blog.common.api.Result;
import cn.xinyuan.blog.common.base.AbstractController;
import cn.xinyuan.blog.common.constants.SysConstants;
import cn.xinyuan.blog.common.util.PageUtils;
import cn.xinyuan.blog.common.validator.ValidatorUtils;
import cn.xinyuan.blog.entity.sys.DO.SysRole;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: SysRoleController
 * @Description: java类作用描述
 * @Author: xinyuan
 * @CreateDate: 2020/3/19 13:18
 */
@RestController
@RequestMapping("/admin/sys/role")
public class SysRoleController extends AbstractController {

    @Autowired
    private ISysRoleService iSysRoleService;

    @Autowired
    private ISysRoleMenuService iSysRoleMenuService;



    /**
     * 分页查询角色列表
     * @param params
     * @return
     */
    @GetMapping("/list")
    @RequiresPermissions("sys:role:list")
    public Result list(@RequestParam Map<String, Object> params){
        //如果不是超级管理员，则只查询自己创建的角色列表
        if(!SysConstants.SUPER_ADMIN.equals(getUserId())){
            params.put("createUserId", getUserId());
        }

        PageUtils page = iSysRoleService.queryPage(params);

        return Result.success(page);
    }

    /**
     * 角色列表
     */
    @GetMapping("/select")
    public Result select(){
        Map<String, Object> map = new HashMap<>();

        //如果不是超级管理员，则只查询自己所拥有的角色列表
        if(!SysConstants.SUPER_ADMIN.equals(getUserId())){
            map.put("createUserId", getUserId());
        }
        Collection<SysRole> list = iSysRoleService.listByMap(map);
        return Result.success(list);
    }

    /**
     * 保存角色信息
     * @param role
     * @return
     */
    @PostMapping("/save")
    @RequiresPermissions("sys:role:save")
    public Result save(@RequestBody SysRole role){
        ValidatorUtils.validateEntity(role);

        role.setCreateUserId(String.valueOf(getUserId()));
        iSysRoleService.saveRole(role);

        return Result.success();
    }

    /**
     * 更新角色信息
     * @param role
     * @return
     */
    @PutMapping("/update")
    @RequiresPermissions("sys:role:update")
    public Result update(@RequestBody SysRole role){
        ValidatorUtils.validateEntity(role);

        role.setCreateUserId(String.valueOf(getUserId()));
        iSysRoleService.updateRole(role);

        return Result.success();
    }

    /**
     * 获取角色信息
     * @param roleId
     * @return
     */
    @GetMapping("/info/{roleId}")
    @RequiresPermissions("sys:role:info")
    public Result info(@PathVariable Long roleId){
        SysRole role = iSysRoleService.getById(roleId);
        List<Integer> menuIdList=iSysRoleMenuService.queryMenuIdList(roleId);
        role.setMenuIdList(menuIdList);
        return Result.success(role);
    }

    /**
     * 删除角色信息
     * @param roleIds
     * @return
     */
    @DeleteMapping("/delete")
    @RequiresPermissions("sys:role:delete")
    public Result delete(@RequestBody Long[] roleIds){
        iSysRoleService.deleteBatch(roleIds);

        return Result.success();
    }


}
