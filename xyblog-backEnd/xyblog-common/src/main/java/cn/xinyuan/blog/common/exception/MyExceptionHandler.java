package cn.xinyuan.blog.common.exception;

import cn.xinyuan.blog.common.api.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * MyExceptionHandler
 *
 * @author xinyuan
 * @description 统一异常处理器
 */
@RestControllerAdvice
@Slf4j
public class MyExceptionHandler {

    /**
     * 处理自定义异常
     * @param e
     * @return
     */
    @ExceptionHandler(MyException.class)
    public Result handleMyException(MyException e){
        Result result=new Result();
        result.setCode(e.getCode());
        result.setMessage(e.getMsg());
        return result;
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public Result handlerNoFoundException(Exception e){
        log.error(e.getMessage(),e);
        return Result.failed(ErrorEnum.PATH_NOT_FOUND);
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public Result handleDuplicateKeyException(DuplicateKeyException e){
        log.error(e.getMessage(),e);
        return Result.failed(ErrorEnum.DUPLICATE_KEY);
    }

    @ExceptionHandler(AuthorizationException.class)
    public Result handleAuthorizationException(AuthorizationException e){
        log.error(e.getMessage(),e);
        return Result.failed(ErrorEnum.NO_AUTH);
    }

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e){
        log.error(e.getMessage(),e);
        return Result.failed();
    }
}
