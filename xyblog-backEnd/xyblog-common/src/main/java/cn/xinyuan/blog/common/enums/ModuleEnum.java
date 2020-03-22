package cn.xinyuan.blog.common.enums;

import lombok.Data;
import lombok.Getter;

/**
 * @ClassName: ModuleEnum
 * @Description: java类作用描述
 * @Author: xinyuan
 * @CreateDate: 2020/3/15 0:02
 */
@Getter
public enum ModuleEnum {
    /**
     * 文章模块
     */
    ARTICLE(0),
    /**
     * 图书模块
     */
    BOOK(1),
    /**
     * 图书笔记模块
     */
    BOOK_NOTE(2);

    private int value;

    ModuleEnum(int value) {
        this.value = value;
    }
}
