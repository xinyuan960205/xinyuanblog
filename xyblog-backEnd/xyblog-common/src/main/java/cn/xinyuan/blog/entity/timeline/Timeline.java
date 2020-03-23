package cn.xinyuan.blog.entity.timeline;

import lombok.Data;

import java.util.List;

/**
 * @ClassName: Timeline
 * @Description: 时间轴（年）
 * @Author: xinyuan
 * @CreateDate: 2020/3/18 12:58
 */
@Data
public class Timeline {
    private Integer year;

    private Integer count;

    private List<TimelineMonth> months;
}
