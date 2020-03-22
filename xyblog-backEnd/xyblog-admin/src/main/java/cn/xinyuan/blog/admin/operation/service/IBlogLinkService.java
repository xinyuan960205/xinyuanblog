package cn.xinyuan.blog.admin.operation.service;


import cn.xinyuan.blog.common.util.PageUtils;
import cn.xinyuan.blog.entity.operation.BlogLink;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 友链表 服务类
 * </p>
 *
 * @author xinyuan
 * @since 2020-03-17
 */
public interface IBlogLinkService extends IService<BlogLink> {

    PageUtils queryPage(Map<String, Object> params);

}
