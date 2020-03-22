package cn.xinyuan.blog.portal.operation.service.impl;

import cn.xinyuan.blog.entity.operation.BlogCategoryInfo;
import cn.xinyuan.blog.mapper.operation.BlogCategoryInfoMapper;
import cn.xinyuan.blog.portal.operation.service.ICategoryService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 分类信息表 服务实现类
 * </p>
 *
 * @author xinyuan
 * @since 2020-03-14
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<BlogCategoryInfoMapper, BlogCategoryInfo> implements ICategoryService {

    @Override
    public List<BlogCategoryInfo> listCategory(Map<String, Object> params) {
        String type = (String) params.get("type");
        String rank = (String) params.get("rank");
        return baseMapper.selectList(new QueryWrapper<BlogCategoryInfo>().lambda()
                .eq(StringUtils.isNotEmpty(type), BlogCategoryInfo::getType, type)
                .eq(StringUtils.isNotEmpty(rank), BlogCategoryInfo::getCategoryRank, rank));
    }
}
