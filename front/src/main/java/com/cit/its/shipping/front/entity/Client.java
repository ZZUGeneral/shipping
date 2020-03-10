package com.cit.its.shipping.front.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cit.its.shipping.front.enums.ClientTypeEnum;
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
@Alias("client")
public class Client {

    /**
     * id  主键
     */
    @TableId
    private Integer id;

    /**
     * 终端ID
     */
    @TableField(value = "clientid")
    private String clientId;

    /**
     * 终端名称
     */
    private String name;

    /**
     * 在线状态
     * 0： 离线
     * 1： 在线
     */
    private Integer state;

    /**
     * 接入点名称
     */
    private String node;

    /**
     * 上线时间
     */
    private Date onlineAt;

    /**
     * 下线时间
     */
    private Date offlineAt;

    /**
     * 创建时间
     */
    private Date created;

    /**
     * 终端类型
     * @see ClientTypeEnum
     */
    private ClientTypeEnum type;

    /**
     * 是否可用
     */
    private Boolean valid;
}
