package cn.xinyuan.blog.portal.operation.service.impl;

import cn.xinyuan.blog.entity.operation.BlogLink;
import cn.xinyuan.blog.mapper.operation.BlogLinkMapper;
import cn.xinyuan.blog.portal.operation.service.ILinkService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: LinkServiceImpl
 * @Description: java类作用描述
 * @Author: xinyuan
 * @CreateDate: 2020/3/17 14:27
 */
@Service
public class LinkServiceImpl extends ServiceImpl<BlogLinkMapper, BlogLink> implements ILinkService{
    @Override
    public List<BlogLink> listLink() {
        return baseMapper.selectList(null);
    }
}
