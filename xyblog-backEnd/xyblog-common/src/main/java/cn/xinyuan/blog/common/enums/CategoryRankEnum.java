package cn.xinyuan.blog.common.enums;

import lombok.Getter;

/**
 * @ClassName: CategoryRankEnum
 * @Description: java类作用描述
 * @Author: xinyuan
 * @CreateDate: 2020/3/16 17:51
 */
@Getter
public enum CategoryRankEnum {
    /**
     * 一级
     */
    ROOT(-1),
    /**
     * 一级
     */
    FIRST(1),
    /**
     * 二级
     */
    SECOND(2),
    /**
     * 三级
     */
    THIRD(3);

    private int value;

    CategoryRankEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
