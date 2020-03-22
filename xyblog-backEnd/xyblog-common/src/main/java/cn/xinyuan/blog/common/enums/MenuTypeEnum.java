package cn.xinyuan.blog.common.enums;

import lombok.Getter;

/**
 * @ClassName: MenuTypeEnum
 * @Description: 菜单类型
 * @Author: xinyuan
 * @CreateDate: 2020/2/9 12:47
 */
@Getter
public enum MenuTypeEnum {
    /**
     * 目录
     */
    CATALOG(0),
    /**
     * 菜单
     */
    MENU(1),
    /**
     * 按钮
     */
    BUTTON(2);

    private int value;

    MenuTypeEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
