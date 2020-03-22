package cn.xinyuan.blog.mapper.article;

import cn.xinyuan.blog.entity.article.DO.BlogArticleInfo;
import cn.xinyuan.blog.entity.article.DTO.ArticleDTO;
import cn.xinyuan.blog.entity.article.VO.ArticleVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 文章信息表 Mapper 接口
 * </p>
 *
 * @author xinyuan
 * @since 2020-02-09
 */
public interface BlogArticleInfoMapper extends BaseMapper<BlogArticleInfo> {

    List<ArticleDTO> listArticleDTO(Page<ArticleDTO> page, @Param("params") Map<String, Object> params);

    List<ArticleDTO> queryArticlesCondition(Page<ArticleDTO> page, @Param("params") Map<String, Object> params);

    ArticleVO queryArticleById(@Param("ArticleId") Long ArticleId);
}
