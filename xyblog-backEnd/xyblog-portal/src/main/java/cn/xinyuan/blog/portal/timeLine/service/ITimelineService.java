package cn.xinyuan.blog.portal.timeLine.service;

import java.util.List;

/**
 * @ClassName: ITimelineService
 * @Description: java类作用描述
 * @Author: xinyuan
 * @CreateDate: 2020/3/18 13:04
 */
public interface ITimelineService {
    List<cn.xinyuan.blog.entity.timeline.Timeline> listTimeLine();
}
