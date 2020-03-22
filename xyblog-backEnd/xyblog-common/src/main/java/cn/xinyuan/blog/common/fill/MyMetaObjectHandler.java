package cn.xinyuan.blog.common.fill;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @ClassName: MyMetaObjectHandler
 * @Description: java类作用描述
 * @Author: xinyuan
 * @CreateDate: 2020/3/21 17:15
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        // 填充创建时间 更新时间字段
        Date now = new Date();
        this.setFieldValByName("createBy", now, metaObject);
        this.setFieldValByName("modifiedBy", now, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // 填充给更新时间字段
        this.setFieldValByName("modifiedBy", new Date(), metaObject);
    }
}
