package com.cit.its.shipping.simulator.message;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;
import java.util.concurrent.ThreadLocalRandom;

@Data
public abstract class AbstractMessage implements Serializable {

    private static final long serialVersionUID = -6295920445732826165L;

    /**
     * 时间戳，单位秒
     */
    private Long time;

    @JSONField(serialize = false)
    protected ThreadLocalRandom random;

    public AbstractMessage() {
        random = ThreadLocalRandom.current();
    }

    public byte[] msgContent() {
        this.time = Instant.now().getEpochSecond();
        randomValue();
        return JSON.toJSONBytes(this, SerializerFeature.WriteNullNumberAsZero);
    }

    protected abstract void randomValue();
}
