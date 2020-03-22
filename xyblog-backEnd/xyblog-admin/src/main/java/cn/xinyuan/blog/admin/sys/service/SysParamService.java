package cn.xinyuan.blog.admin.sys.service;

import cn.xinyuan.blog.common.util.PageUtils;
import cn.xinyuan.blog.entity.sys.DO.SysParam;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @ClassName: SysParamService
 * @Description: java类作用描述
 * @Author: xinyuan
 * @CreateDate: 2020/1/24 14:50
 */
public interface SysParamService extends IService<SysParam>{
    PageUtils queryPage(Map<String, Object> params);
}
