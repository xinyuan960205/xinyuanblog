package cn.xinyuan.blog.entity.timeline;

import lombok.Data;

import java.util.List;

/**
 * @ClassName: TimelineMonth
 * @Description: 时间轴（月）
 * @Author: xinyuan
 * @CreateDate: 2020/3/18 12:57
 */
@Data
public class TimelineMonth {
    private Integer month;

    private Integer count;

    private List<TimelinePost> posts;
}
