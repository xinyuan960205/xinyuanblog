package cn.xinyuan.blog.portal.operation.service;

import cn.xinyuan.blog.entity.operation.BlogRecommend;
import cn.xinyuan.blog.entity.operation.VO.BlogRecommendVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @ClassName: IRecommandService
 * @Description: java类作用描述
 * @Author: xinyuan
 * @CreateDate: 2020/3/17 16:32
 */
public interface IRecommandService extends IService<BlogRecommend> {
    List<BlogRecommendVO> listRecommendVo();
}
