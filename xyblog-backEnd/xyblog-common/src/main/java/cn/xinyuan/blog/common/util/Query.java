package cn.xinyuan.blog.common.util;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName: Query
 * @Description: 查询
 * @Author: xinyuan
 * @CreateDate: 2020/2/10 10:32
 */
@Data
public class Query<T> extends LinkedHashMap<String, Object> {
    /**
     * mybatis-plus分页参数
     */
    private Page<T> page;
    /**
     * 每页条数
     */
    private int limit = 10;
    /**
     * 当前页码
     */
    private long currPage = 1;

    public Query(Map<String, Object> params){


        //分页参数
        if(params.get("page") != null){
            currPage = Integer.parseInt((String)params.get("page"));
        }
        if(params.get("limit") != null){
            limit = Integer.parseInt((String)params.get("limit"));
        }

        this.page = new Page<>(currPage, limit);
    }
}
