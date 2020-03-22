package cn.xinyuan.blog.service.impl;

import cn.xinyuan.blog.entity.BlogArticleContent;
import cn.xinyuan.blog.mapper.BlogArticleContentMapper;
import cn.xinyuan.blog.service.IBlogArticleContentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章内容表 服务实现类
 * </p>
 *
 * @author xinyuan
 * @since 2020-02-10
 */
@Service
public class BlogArticleContentServiceImpl extends ServiceImpl<BlogArticleContentMapper, BlogArticleContent> implements IBlogArticleContentService {

}
