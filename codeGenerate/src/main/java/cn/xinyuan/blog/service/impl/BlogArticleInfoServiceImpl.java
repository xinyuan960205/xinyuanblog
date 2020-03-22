package cn.xinyuan.blog.service.impl;

import cn.xinyuan.blog.entity.BlogArticleInfo;
import cn.xinyuan.blog.mapper.BlogArticleInfoMapper;
import cn.xinyuan.blog.service.IBlogArticleInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章信息表 服务实现类
 * </p>
 *
 * @author xinyuan
 * @since 2020-02-09
 */
@Service
public class BlogArticleInfoServiceImpl extends ServiceImpl<BlogArticleInfoMapper, BlogArticleInfo> implements IBlogArticleInfoService {

}
