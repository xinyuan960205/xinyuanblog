package cn.xinyuan.blog.entity.sys.DO;

import cn.xinyuan.blog.common.base.baseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: SysParam
 * @Description: 系统参数表
 * @Author: xinyuan
 * @CreateDate: 2020/1/24 14:26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SysParam对象", description="系统参数")
public class SysParam extends baseEntity implements Serializable{
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "参数键")
    @NotNull(message = "参数键不能为空")
    private Integer parKey;

    @ApiModelProperty(value = "参数值")
    @NotBlank(message = "参数值不能为空")
    private String parValue;

    @ApiModelProperty(value = "参数url")
    private String menuUrl;

    @ApiModelProperty(value = "参数类型")
    @NotBlank(message = "参数类型不能为空")
    private String type;
}
