package cn.xinyuan.blog.service.impl;

import cn.xinyuan.blog.entity.BlogTagInfo;
import cn.xinyuan.blog.mapper.BlogTagInfoMapper;
import cn.xinyuan.blog.service.IBlogTagInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 标签信息表 服务实现类
 * </p>
 *
 * @author xinyuan
 * @since 2020-03-14
 */
@Service
public class BlogTagInfoServiceImpl extends ServiceImpl<BlogTagInfoMapper, BlogTagInfo> implements IBlogTagInfoService {

}
