package cn.xinyuan.blog.portal.operation.service.impl;

import cn.xinyuan.blog.entity.operation.BlogTagInfo;
import cn.xinyuan.blog.mapper.operation.BlogTagInfoMapper;
import cn.xinyuan.blog.portal.operation.service.ITagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: TagServiceImpl
 * @Description: java类作用描述
 * @Author: xinyuan
 * @CreateDate: 2020/3/15 10:30
 */
@Service
public class TagServiceImpl extends ServiceImpl<BlogTagInfoMapper,BlogTagInfo> implements ITagService{

    @Override
    public List<BlogTagInfo> listTag() {
        return baseMapper.selectList(null);
    }
}
