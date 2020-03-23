package cn.xinyuan.blog.admin.operation.service.impl;

import cn.xinyuan.blog.admin.article.service.IBlogArticleInfoService;
import cn.xinyuan.blog.admin.operation.service.IBlogRecommendService;
import cn.xinyuan.blog.common.util.PageUtils;
import cn.xinyuan.blog.common.util.Query;
import cn.xinyuan.blog.entity.article.DO.BlogArticleInfo;
import cn.xinyuan.blog.entity.operation.BlogRecommend;
import cn.xinyuan.blog.entity.operation.VO.BlogRecommendVO;
import cn.xinyuan.blog.mapper.operation.BlogRecommendMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 推荐表 服务实现类
 * </p>
 *
 * @author xinyuan
 * @since 2020-03-17
 */
@Service
public class BlogRecommendServiceImpl extends ServiceImpl<BlogRecommendMapper, BlogRecommend> implements IBlogRecommendService {

    @Autowired
    private IBlogArticleInfoService iBlogArticleInfoService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String title = (String) params.get("title");
        IPage<BlogRecommend> page = baseMapper.selectPage(new Query<BlogRecommend>(params).getPage(),
                new QueryWrapper<BlogRecommend>().lambda()
                        .like(StringUtils.isNotEmpty(title),BlogRecommend::getTitle,title));
        return new PageUtils(page);
    }

    @Override
    public List<BlogRecommendVO> listSelect() {
        return baseMapper.listSelect();
    }

    @Override
    public int updateTop(Long id) {
        BlogRecommend blogRecommend = new BlogRecommend();
        blogRecommend.setTop(true);
        blogRecommend.setId(id);
        baseMapper.updateById(blogRecommend);
        //将其余的置顶状态都置为false
        blogRecommend.setTop(false);
        blogRecommend.setId(null);
        this.baseMapper.update(blogRecommend,new QueryWrapper<BlogRecommend>().lambda()
                .ne(BlogRecommend::getId,id));
        return 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int saveRecommend(BlogRecommend recommend) {
        baseMapper.insert(recommend);
        //更新文章的推荐状态
        BlogArticleInfo blogArticleInfo=new BlogArticleInfo();
        blogArticleInfo.setId(recommend.getLinkId());
        blogArticleInfo.setIsRecommend(true);
        iBlogArticleInfoService.updateById(blogArticleInfo);
        return 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int removePatch(List<Long> list) {
        for(Long s:list){
            BlogRecommend blogRecommend = baseMapper.selectOne(new QueryWrapper<BlogRecommend>().lambda()
                    .eq(BlogRecommend::getId, s));
            //更新文章的推荐状态
            BlogArticleInfo blogArticleInfo=new BlogArticleInfo();
            blogArticleInfo.setId(blogRecommend.getLinkId());
            blogArticleInfo.setIsRecommend(false);
            iBlogArticleInfoService.updateById(blogArticleInfo);
        }
        baseMapper.deleteBatchIds(list);
        return 0;
    }

    @Override
    public int deleteByArticleId(List<Long> list) {
        for(Long s : list){
            //更新文章的推荐状态
            BlogArticleInfo blogArticleInfo=new BlogArticleInfo();
            blogArticleInfo.setId(s);
            blogArticleInfo.setIsRecommend(false);
            iBlogArticleInfoService.updateById(blogArticleInfo);
            baseMapper.delete(new QueryWrapper<BlogRecommend>().lambda()
                    .eq(BlogRecommend::getLinkId,s));
        }
        return 0;
    }
}
