package cn.xinyuan.blog.entity.timeline;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName: TimelinePost
 * @Description: 时间轴具体数据
 * @Author: xinyuan
 * @CreateDate: 2020/3/18 12:59
 */
@Data
public class TimelinePost {
    private Integer id;

    private String title;

    private String description;

    private String postType;

    private Date createTime;
}
