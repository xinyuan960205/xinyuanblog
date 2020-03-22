package cn.xinyuan.blog.admin.sys.controller;

import cn.xinyuan.blog.admin.sys.service.SysMenuService;
import cn.xinyuan.blog.common.api.Result;
import cn.xinyuan.blog.common.enums.MenuTypeEnum;
import cn.xinyuan.blog.common.exception.MyException;
import cn.xinyuan.blog.entity.sys.DO.SysMenu;
import cn.xinyuan.blog.security.service.ShiroService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cn.xinyuan.blog.common.base.AbstractController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName: SysMenuController
 * @Description: 后台菜单管理接口
 * @Author: xinyuan
 * @CreateDate: 2020/1/22 23:13
 */
@RestController
@RequestMapping("admin/sys/menu")
public class SysMenuController extends AbstractController{

    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private ShiroService shiroService;

    @GetMapping("/nav")
    public Result nav(){
        List<SysMenu> menuList=sysMenuService.listUserMenu(getUserId());
        Set<String> permissions=shiroService.getUserPermissions(getUserId());
        Map<String,Object> map = new HashMap<>();
        map.put("menuList",menuList);
        map.put("permissions",permissions);
        return Result.success(map);
    }

    /**
     * 所有菜单列表
     */
    @GetMapping("/list")
    @RequiresPermissions("sys:menu:list")
    public List<SysMenu> list(){
        List<SysMenu> menuList = sysMenuService.list(null);
        menuList.forEach(sysMenu -> {
            SysMenu parentMenu = sysMenuService.getById(sysMenu.getParentId());
            if(parentMenu != null){
                sysMenu.setParentName(parentMenu.getName());
            }
        });
        return menuList;
    }

    /**
     * 选择菜单(添加、修改菜单)
     */
    @GetMapping("/select")
    @RequiresPermissions("sys:menu:select")
    public Result select(){
        //查询列表数据
        List<SysMenu> menuList = sysMenuService.queryNotButtonList();

        //添加顶级菜单
        SysMenu root = new SysMenu();
        root.setMenuId(0);
        root.setName("一级菜单");
        root.setParentId(-1);
        root.setOpen(true);
        menuList.add(root);

        return Result.success(menuList);
    }

    /**
     * 获取单个菜单信息
     * @param menuId
     * @return
     */
    @GetMapping("/info/{menuId}")
    @RequiresPermissions("sys:menu:info")
    public Result update(@PathVariable Integer menuId){
        SysMenu menu = sysMenuService.getById(menuId);
        return Result.success(menu);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @RequiresPermissions("sys:menu:save")
    public Result save(@RequestBody SysMenu menu){
        //数据校验
        verifyForm(menu);

        sysMenuService.save(menu);

        return Result.success();
    }

    /**
     * 更新
     * @param menu
     * @return
     */
    @PutMapping("/update")
    @RequiresPermissions("sys:menu:update")
    public Result update(@RequestBody SysMenu menu){
        //数据校验
        verifyForm(menu);

        sysMenuService.updateById(menu);

        return Result.success();
    }

    /**
     * 删除
     * @param menuId
     * @return
     */
    @DeleteMapping("/delete/{menuId}")
    @RequiresPermissions("sys:menu:delete")
    public Result delete(@PathVariable Integer menuId){
        if(menuId <= 29){
            return Result.failed("系统菜单，不能删除");
        }

        //判断是否有子菜单或按钮
        List<SysMenu> menuList = sysMenuService.queryListParentId(menuId);
        if(menuList.size() > 0){
            return Result.failed("请先删除子菜单或按钮");
        }
        sysMenuService.removeById(menuId);
        return Result.success();
    }
    /**
     * 验证参数是否正确
     */
    private void verifyForm(SysMenu menu) {
        if (StringUtils.isBlank(menu.getName())) {
            throw new MyException("菜单名称不能为空");
        }

        if (menu.getParentId() == null) {
            throw new MyException("上级菜单不能为空");
        }

        //菜单
        if (menu.getType() == MenuTypeEnum.MENU.getValue()) {
            if (StringUtils.isBlank(menu.getUrl())) {
                throw new MyException("菜单URL不能为空");
            }
        }

        //上级菜单类型
        int parentType = MenuTypeEnum.CATALOG.getValue();
        if (menu.getParentId() != 0) {
            SysMenu parentMenu = sysMenuService.getById(menu.getParentId());
            parentType = parentMenu.getType();
        }

        //目录、菜单
        if (menu.getType() == MenuTypeEnum.CATALOG.getValue() ||
                menu.getType() == MenuTypeEnum.MENU.getValue()) {
            if (parentType != MenuTypeEnum.CATALOG.getValue()) {
                throw new MyException("上级菜单只能为目录类型");
            }
        }

        //按钮
        if (menu.getType() == MenuTypeEnum.BUTTON.getValue()) {
            if (parentType != MenuTypeEnum.MENU.getValue()) {
                throw new MyException("上级菜单只能为菜单类型");
            }
        }
    }

}
