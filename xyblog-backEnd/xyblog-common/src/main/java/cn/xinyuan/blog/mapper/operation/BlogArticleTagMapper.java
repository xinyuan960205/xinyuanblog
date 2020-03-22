package cn.xinyuan.blog.mapper.operation;


import cn.xinyuan.blog.entity.operation.BlogArticleTag;
import cn.xinyuan.blog.entity.operation.BlogTagInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 文章标签表 Mapper 接口
 * </p>
 *
 * @author xinyuan
 * @since 2020-03-14
 */
public interface BlogArticleTagMapper extends BaseMapper<BlogArticleTag> {

    List<BlogTagInfo> getTagsByArticleId(@Param("articleId") Long id);
}
