package cn.xinyuan.blog.mapper.timeline;

import cn.xinyuan.blog.entity.timeline.Timeline;
import cn.xinyuan.blog.entity.timeline.TimelinePost;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName: TimelineMapper
 * @Description: java类作用描述
 * @Author: xinyuan
 * @CreateDate: 2020/3/18 13:01
 */
public interface TimelineMapper {

    List<Timeline> listTimeLine();

    List<TimelinePost> listTimelinePost(@Param("year") Integer year, @Param("month") Integer month);
}
