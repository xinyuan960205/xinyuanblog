package cn.xinyuan.blog.mapper.operation;


import cn.xinyuan.blog.entity.operation.BlogRecommend;
import cn.xinyuan.blog.entity.operation.VO.BlogRecommendVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 推荐表 Mapper 接口
 * </p>
 *
 * @author xinyuan
 * @since 2020-03-17
 */
public interface BlogRecommendMapper extends BaseMapper<BlogRecommend> {

    List<BlogRecommendVO> listSelect();

    List<BlogRecommendVO> listRecommendVo();
}
