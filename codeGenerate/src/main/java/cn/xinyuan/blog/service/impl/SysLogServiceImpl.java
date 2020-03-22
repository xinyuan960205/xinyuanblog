package cn.xinyuan.blog.service.impl;

import cn.xinyuan.blog.entity.SysLog;
import cn.xinyuan.blog.mapper.SysLogMapper;
import cn.xinyuan.blog.service.ISysLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 日志表 服务实现类
 * </p>
 *
 * @author xinyuan
 * @since 2020-03-19
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements ISysLogService {

}
