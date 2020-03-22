package cn.xinyuan.blog.portal.operation.service;

import cn.xinyuan.blog.entity.operation.BlogLink;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @ClassName: ILinkService
 * @Description: java类作用描述
 * @Author: xinyuan
 * @CreateDate: 2020/3/17 14:27
 */
public interface ILinkService extends IService<BlogLink> {
    List<BlogLink> listLink();
}
