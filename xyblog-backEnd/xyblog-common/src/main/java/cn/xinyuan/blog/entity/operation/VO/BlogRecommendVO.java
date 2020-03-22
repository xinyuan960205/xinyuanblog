package cn.xinyuan.blog.entity.operation.VO;

import cn.xinyuan.blog.entity.operation.BlogRecommend;
import cn.xinyuan.blog.entity.operation.BlogTagInfo;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: BlogRecommendVO
 * @Description: java类作用描述
 * @Author: xinyuan
 * @CreateDate: 2020/3/17 15:00
 */
@Data
public class BlogRecommendVO extends BlogRecommend {
    private String description;

    private Long readNum;

    private Long commentNum;

    private Long likeNum;

    private String urlType;

    private String cover;

    private Date createTime;

    private List<BlogTagInfo> tagList;
}
