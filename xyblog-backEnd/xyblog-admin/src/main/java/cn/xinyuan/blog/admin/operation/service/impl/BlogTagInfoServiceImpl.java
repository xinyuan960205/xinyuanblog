package cn.xinyuan.blog.admin.operation.service.impl;

import cn.xinyuan.blog.admin.operation.service.IBlogTagInfoService;
import cn.xinyuan.blog.common.util.PageUtils;
import cn.xinyuan.blog.common.util.Query;
import cn.xinyuan.blog.entity.operation.BlogTagInfo;
import cn.xinyuan.blog.mapper.operation.BlogTagInfoMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: TagServiceImpl
 * @Description: java类作用描述
 * @Author: xinyuan
 * @CreateDate: 2020/3/14 22:29
 */
@Service
public class BlogTagInfoServiceImpl extends ServiceImpl<BlogTagInfoMapper, BlogTagInfo> implements IBlogTagInfoService{

    @Override
    public List<BlogTagInfo> list(Integer type) {
        return baseMapper.selectList(new QueryWrapper<BlogTagInfo>().lambda()
                .eq(type!=null,BlogTagInfo::getType,type));
    }

    @Override
    public int updateTagNum(Long id, int i) {
        BlogTagInfo tagInfo = baseMapper.selectById(id);
        tagInfo.setTagNumber(tagInfo.getTagNumber()+i);
        return baseMapper.updateById(tagInfo);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<BlogTagInfo> page = baseMapper.selectPage(new Query<BlogTagInfo>(params).getPage(),
                new QueryWrapper<BlogTagInfo>().lambda());
        return new PageUtils(page);
    }

}
