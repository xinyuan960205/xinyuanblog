package cn.xinyuan.blog.common.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: PageUtils
 * @Description: java类作用描述
 * @Author: xinyuan
 * @CreateDate: 2020/2/10 10:24
 */
@Data
public class PageUtils implements Serializable{
    private static final long serialVersionUID = 1L;
    //总记录数
    private long totalCount;
    //每页记录数
    private long pageSize;
    //总页数
    private long totalPage;
    //当前页数
    private long currPage;
    //内容列表
    private List<?> list;

    public PageUtils(IPage<?> page){
        this.list = page.getRecords();
        this.totalCount = (int)page.getTotal();
        this.pageSize = page.getSize();
        this.currPage = page.getCurrent();
        this.totalPage = (int)page.getPages();
    }
}
