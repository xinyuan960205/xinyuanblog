package cn.xinyuan.blog.common.api;

import cn.xinyuan.blog.common.exception.ErrorEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: Result
 * @Description: 通用返回对象
 * @Author: xinyuan
 * @CreateDate: 2020/1/22 15:35
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    private int code;
    private String message;
    private T data;

    /**
     * 成功返回结果
     *
     */
    public static <T> Result<T> success() {
        return new Result<T>(ErrorEnum.SUCCESS.getCode(), ErrorEnum.SUCCESS.getMsg(), null);
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     */
    public static <T> Result<T> success(T data) {
        return new Result<T>(ErrorEnum.SUCCESS.getCode(), ErrorEnum.SUCCESS.getMsg(), data);
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     * @param  message 提示信息
     */
    public static <T> Result<T> success(T data, String message) {
        return new Result<T>(ErrorEnum.SUCCESS.getCode(), message, data);
    }

    /**
     * 失败返回结果
     * @param errorCode 错误码
     */
    public static <T> Result<T> failed(ErrorEnum errorCode) {
        return new Result<T>(errorCode.getCode(), errorCode.getMsg(), null);
    }

    /**
     * 失败返回结果
     * @param message 提示信息
     */
    public static <T> Result<T> failed(String message) {
        return new Result<T>(ErrorEnum.FAILED.getCode(), message, null);
    }

    /**
     * 失败返回结果
     */
    public static <T> Result<T> failed() {
        return failed(ErrorEnum.FAILED.getMsg());
    }

    /**
     * 失败返回结果
     * 不使用状态码，自己定义一个错误码和错误信息
     */
    public static <T> Result<T> failed(Integer code , String msg) {
        return new Result<T>(code,msg,null);
    }

}
