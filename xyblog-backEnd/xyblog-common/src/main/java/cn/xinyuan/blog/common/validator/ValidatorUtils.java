package cn.xinyuan.blog.common.validator;

import cn.xinyuan.blog.common.exception.MyException;
import org.apache.commons.lang.StringUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @ClassName: ValidatorUtils
 * @Description: java类作用描述
 * @Author: xinyuan
 * @CreateDate: 2020/3/15 11:49
 */

public class ValidatorUtils {
    private static Validator validator;

    static {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    /**
     * 校验对象
     * @param object        待校验对象
     * @param groups        待校验的组
     * @throws MyException  校验不通过，则报MyException异常
     */
    public static void validateEntity(Object object, Class<?>... groups)
            throws MyException {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
        if (!constraintViolations.isEmpty()) {
            List<String> collect = constraintViolations.stream().map(constant -> constant.getMessage()).collect(Collectors.toList());
            String msg = StringUtils.join(collect, ",");
            throw new MyException(msg);
        }
    }
}
