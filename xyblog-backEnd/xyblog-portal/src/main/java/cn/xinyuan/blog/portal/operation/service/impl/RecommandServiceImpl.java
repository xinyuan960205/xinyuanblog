package cn.xinyuan.blog.portal.operation.service.impl;

import cn.xinyuan.blog.common.enums.ModuleEnum;
import cn.xinyuan.blog.entity.article.VO.ArticleVO;
import cn.xinyuan.blog.entity.operation.BlogRecommend;
import cn.xinyuan.blog.entity.operation.VO.BlogRecommendVO;
import cn.xinyuan.blog.mapper.operation.BlogRecommendMapper;
import cn.xinyuan.blog.portal.operation.service.IRecommandService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: RecommandServiceImpl
 * @Description: java类作用描述
 * @Author: xinyuan
 * @CreateDate: 2020/3/17 16:33
 */
@Service
public class RecommandServiceImpl extends ServiceImpl<BlogRecommendMapper, BlogRecommend> implements IRecommandService{

    @Override
    public List<BlogRecommendVO> listRecommendVo() {
        return baseMapper.listRecommendVo();
    }

//    private List<BlogRecommendVO> genRecommendList(List<BlogRecommendVO> recommendList) {
//        recommendList.forEach(recommendVo -> {
//            if(ModuleEnum.ARTICLE.getValue() == recommendVo.getType()){
//                ArticleVO simpleArticleVo = articleService.getSimpleArticleVo(recommendVo.getLinkId());
//                BeanUtils.copyProperties(simpleArticleVo,recommendVo);
//                recommendVo.setUrlType("article");
//            }else if(ModuleEnum.BOOK_NOTE.getValue() == recommendVo.getType()) {
//                BookNoteVO simpleBookNoteVo = bookNoteService.getSimpleBookNoteVo(recommendVo.getLinkId());
//                recommendVo.setUrlType("bookNote");
//                BeanUtils.copyProperties(simpleBookNoteVo,recommendVo);
//            }
//        });
//        return recommendList;
//    }
}
