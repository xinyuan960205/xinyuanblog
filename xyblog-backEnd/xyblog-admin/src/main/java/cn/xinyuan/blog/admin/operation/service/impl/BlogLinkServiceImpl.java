package cn.xinyuan.blog.admin.operation.service.impl;

import cn.xinyuan.blog.admin.operation.service.IBlogLinkService;
import cn.xinyuan.blog.common.util.PageUtils;
import cn.xinyuan.blog.common.util.Query;
import cn.xinyuan.blog.entity.operation.BlogLink;
import cn.xinyuan.blog.mapper.operation.BlogLinkMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 友链表 服务实现类
 * </p>
 *
 * @author xinyuan
 * @since 2020-03-17
 */
@Service
public class BlogLinkServiceImpl extends ServiceImpl<BlogLinkMapper, BlogLink> implements IBlogLinkService {

    /**
     * 分页查询
     * @param params
     * @return
     */
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String title = (String) params.get("title");
        IPage<BlogLink> page=baseMapper.selectPage(new Query<BlogLink>(params).getPage(),
                new QueryWrapper<BlogLink>().lambda().like(StringUtils.isNotEmpty(title),BlogLink::getTitle,title));
        return new PageUtils(page);
    }
}
