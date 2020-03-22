package cn.xinyuan.blog.service.impl;

import cn.xinyuan.blog.entity.BlogArticleTag;
import cn.xinyuan.blog.mapper.BlogArticleTagMapper;
import cn.xinyuan.blog.service.IBlogArticleTagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章标签表 服务实现类
 * </p>
 *
 * @author xinyuan
 * @since 2020-03-14
 */
@Service
public class BlogArticleTagServiceImpl extends ServiceImpl<BlogArticleTagMapper, BlogArticleTag> implements IBlogArticleTagService {

}
