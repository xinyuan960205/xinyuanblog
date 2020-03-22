package cn.xinyuan.blog.service.impl;

import cn.xinyuan.blog.entity.BlogCategoryInfo;
import cn.xinyuan.blog.mapper.BlogCategoryInfoMapper;
import cn.xinyuan.blog.service.IBlogCategoryInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 分类信息表 服务实现类
 * </p>
 *
 * @author xinyuan
 * @since 2020-03-14
 */
@Service
public class BlogCategoryInfoServiceImpl extends ServiceImpl<BlogCategoryInfoMapper, BlogCategoryInfo> implements IBlogCategoryInfoService {

}
