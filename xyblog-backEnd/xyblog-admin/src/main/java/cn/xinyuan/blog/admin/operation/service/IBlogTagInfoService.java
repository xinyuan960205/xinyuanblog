package cn.xinyuan.blog.admin.operation.service;

import cn.xinyuan.blog.common.util.PageUtils;
import cn.xinyuan.blog.entity.operation.BlogTagInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: ITagService
 * @Description: java类作用描述
 * @Author: xinyuan
 * @CreateDate: 2020/3/14 22:29
 */
public interface IBlogTagInfoService extends IService<BlogTagInfo>{
    List<BlogTagInfo> list(Integer type);

    int updateTagNum(Long id, int i);

    PageUtils queryPage(Map<String, Object> params);
}
