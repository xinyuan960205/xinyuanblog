package cn.xinyuan.blog.admin.sys.service.impl;

import cn.xinyuan.blog.admin.sys.service.SysParamService;
import cn.xinyuan.blog.common.util.PageUtils;
import cn.xinyuan.blog.common.util.Query;
import cn.xinyuan.blog.entity.sys.DO.SysParam;
import cn.xinyuan.blog.mapper.sys.SysParamMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @ClassName: SysParamServiceImpl
 * @Description: java类作用描述
 * @Author: xinyuan
 * @CreateDate: 2020/1/24 14:51
 */
@Service
public class SysParamServiceImpl extends ServiceImpl<SysParamMapper, SysParam> implements SysParamService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String menuUrl = (String) params.get("menuUrl");
        String type = (String) params.get("type");

        IPage<SysParam> iPage = baseMapper.selectPage(new Query<SysParam>(params).getPage()
                , new QueryWrapper<SysParam>().lambda()
                        .like(StringUtils.isNotEmpty(menuUrl), SysParam::getMenuUrl, menuUrl)
                        .like(StringUtils.isNotEmpty(type), SysParam::getType, type));
        return new PageUtils(iPage);
    }
}
