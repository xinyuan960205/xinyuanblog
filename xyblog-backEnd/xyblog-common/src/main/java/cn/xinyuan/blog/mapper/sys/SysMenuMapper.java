package cn.xinyuan.blog.mapper.sys;

import cn.xinyuan.blog.entity.sys.DO.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName: SysMenuMapper
 * @Description: java类作用描述
 * @Author: xinyuan
 * @CreateDate: 2020/1/24 15:39
 */
public interface SysMenuMapper extends BaseMapper<SysMenu>{
    /**
     * 查询非按钮的菜单
     * @return
     */
    List<SysMenu> queryNotButtonList();

    /**
     * 根据parentId查询菜单
     * @param parentId
     * @return
     */
    List<SysMenu> queryListParentId(Integer parentId);
}
