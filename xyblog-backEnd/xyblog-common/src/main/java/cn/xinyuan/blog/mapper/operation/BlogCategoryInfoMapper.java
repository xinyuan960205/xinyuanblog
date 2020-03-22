package cn.xinyuan.blog.mapper.operation;

import cn.xinyuan.blog.entity.operation.BlogCategoryInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 分类信息表 Mapper 接口
 * </p>
 *
 * @author xinyuan
 * @since 2020-03-14
 */
public interface BlogCategoryInfoMapper extends BaseMapper<BlogCategoryInfo> {

    List<BlogCategoryInfo> queryWithParentName(Map<String, Object> params);
}
