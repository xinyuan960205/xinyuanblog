package cn.xinyuan.blog.portal.operation.service;

import cn.xinyuan.blog.entity.operation.BlogTagInfo;

import java.util.List;

/**
 * @ClassName: ITagService
 * @Description: java类作用描述
 * @Author: xinyuan
 * @CreateDate: 2020/3/15 10:30
 */
public interface ITagService {
    List<BlogTagInfo> listTag();
}
