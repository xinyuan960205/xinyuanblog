package cn.xinyuan.blog.admin.operation.service;


import cn.xinyuan.blog.common.util.PageUtils;
import cn.xinyuan.blog.entity.operation.BlogRecommend;
import cn.xinyuan.blog.entity.operation.VO.BlogRecommendVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 推荐表 服务类
 * </p>
 *
 * @author xinyuan
 * @since 2020-03-17
 */
public interface IBlogRecommendService extends IService<BlogRecommend> {

    PageUtils queryPage(Map<String, Object> params);

    List<BlogRecommendVO> listSelect();

    int updateTop(Integer id);

    int saveRecommend(BlogRecommend recommend);

    int removePatch(List<String> list);

    int deleteByArticleId(List<String> list);
}
