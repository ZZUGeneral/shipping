package com.cit.its.shipping.front.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cit.its.shipping.front.enums.ClientTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * MQTT 连接终端，包含传感器与代码连接终端
 * 类型通过type字段标记
 * 字段名称对应eqmx持久化插件配置，谨慎修改
 */
@Data
@TableName(value = "mqtt_client")
//@Alias("client")
@ApiModel("MQTT 连接终端")
public class Client {

    /**
     * id  主键
     */
    @ApiModelProperty("ID,唯一主键")
    @TableId
    private Integer id;

    /**
     * 终端ID
     */
    @ApiModelProperty(" 终端ID")
    @TableField("client_id")
    private String clientId;

    /**
     * 终端名称
     */
    @ApiModelProperty("终端名称")
    @TableField("name")
    private String name;

    /**
     * 在线状态
     * 0： 离线
     * 1： 在线
     */
    @ApiModelProperty("在线状态")
    @TableField("state")
    private Integer state;

    /**
     * 接入点名称
     */
    @ApiModelProperty("接入点名称")
    @TableField("node")
    private String node;

    /**
     * 上线时间
     */
    @ApiModelProperty("上线时间")
    @TableField("online_at")
    private Date onlineAt;

    /**
     * 下线时间
     */
    @ApiModelProperty("下线时间")
    @TableField("offline_at")
    private Date offlineAt;

    /**
     * 创建时间
     */
    @TableField("created")
    @ApiModelProperty("创建时间")
    private Date created;

    /**
     * 终端类型
     *
     * @see ClientTypeEnum
     */
    @TableField("type")
    @ApiModelProperty("终端类型")
    private ClientTypeEnum type;

    /**
     * 是否可用
     */
    @TableField("valid")
    @ApiModelProperty("是否可用")
    private Boolean valid;
}
