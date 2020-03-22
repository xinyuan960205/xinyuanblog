package cn.xinyuan.blog.portal.timeLine.service.impl;

import cn.xinyuan.blog.entity.timeline.Timeline;
import cn.xinyuan.blog.entity.timeline.TimelineMonth;
import cn.xinyuan.blog.entity.timeline.TimelinePost;
import cn.xinyuan.blog.mapper.timeline.TimelineMapper;
import cn.xinyuan.blog.portal.timeLine.service.ITimelineService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName: TimelineServiceImpl
 * @Description: java类作用描述
 * @Author: xinyuan
 * @CreateDate: 2020/3/18 13:04
 */
@Service
public class TimelineServiceImpl implements ITimelineService{

    @Autowired
    private TimelineMapper timelineMapper;

    @Override
    public List<Timeline> listTimeLine() {
        List<Timeline> timelineList = timelineMapper.listTimeLine();
        genTimelineMonth(timelineList);
        return timelineList;
    }

    private void genTimelineMonth(List<Timeline> timelineList) {
        for (Timeline timeline : timelineList) {
            List<TimelineMonth> timelineMonths = new ArrayList<>();
            for (int i = Calendar.DECEMBER+1; i >0 ; i--) {
                List<TimelinePost> timelinePosts = timelineMapper.listTimelinePost(timeline.getYear(),i);
                if(CollectionUtils.isEmpty(timelinePosts)) continue;
                TimelineMonth timelineMonth = new TimelineMonth();
                timelineMonth.setMonth(i);
                timelineMonth.setCount(timelinePosts.size());
                timelineMonth.setPosts(timelinePosts);
                timelineMonths.add(timelineMonth);
            }
            timeline.setMonths(timelineMonths);
        }
    }

}
