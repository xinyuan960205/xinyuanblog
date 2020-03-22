package cn.xinyuan.blog.portal.timeLine.controller;

import cn.xinyuan.blog.common.api.Result;
import cn.xinyuan.blog.entity.timeline.Timeline;
import cn.xinyuan.blog.portal.timeLine.service.ITimelineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName: TimeLineController
 * @Description: 时间轴接口
 * @Author: xinyuan
 * @CreateDate: 2020/3/18 12:50
 */
@RestController
@RequestMapping("/timeline")
public class TimeLineController {

    @Autowired
    private ITimelineService iTimelineService;

    @GetMapping("")
    public Result listTimeline() {
        List<Timeline> timelineList = iTimelineService.listTimeLine();

        return Result.success(timelineList);
    }
}
