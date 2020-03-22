package cn.xinyuan.blog.admin.operation.service.impl;

import cn.xinyuan.blog.admin.operation.service.IBlogCategoryInfoService;
import cn.xinyuan.blog.entity.operation.BlogCategoryInfo;
import cn.xinyuan.blog.mapper.operation.BlogCategoryInfoMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
public class BlogCategoryInfoServiceImpl extends ServiceImpl<BlogCategoryInfoMapper, BlogCategoryInfo> implements IBlogCategoryInfoService {

    @Autowired
    private BlogCategoryInfoMapper blogCategoryInfoMapper;

    @Override
    public List<BlogCategoryInfo> queryWithParentName(Map<String, Object> params) {
        List<BlogCategoryInfo> categoryInfos = blogCategoryInfoMapper.queryWithParentName(params);
        return categoryInfos;
    }

    @Override
    public String getCategorysByArticleId(String id, int value) {
        if (StringUtils.isEmpty(id)) return "";
        String[] ids = id.split(",");
        ArrayList<String> categoryStrList = new ArrayList<>();
        for (String s : ids) {
            BlogCategoryInfo blogCategoryInfo = baseMapper.selectById(Integer.parseInt(s));
            categoryStrList.add(blogCategoryInfo.getCategoryName());
        }
        return String.join("/", categoryStrList);
    }

    @Override
    public List<BlogCategoryInfo> queryListParentId(Integer id) {
        List<BlogCategoryInfo> categoryInfos = baseMapper.selectList(new QueryWrapper<BlogCategoryInfo>().lambda()
                .eq(BlogCategoryInfo::getParentId, id));
        return categoryInfos;
    }
}
