
package com.zuoyy.component.pay.common.bean.outbuilder;

import com.alibaba.fastjson.JSONObject;
import com.zuoyy.component.pay.common.bean.PayOutMessage;

/**
 */
public class JsonBuilder  extends BaseBuilder<JsonBuilder, PayOutMessage>{
    JSONObject json = null;

    public JsonBuilder(JSONObject json) {
        this.json = json;
    }

    public JsonBuilder content(String key, Object content) {
        this.json.put(key, content);
        return this;
    }

    public JSONObject getJson() {
        return json;
    }

    @Override
    public PayOutMessage build() {
        PayJsonOutMessage message = new PayJsonOutMessage();
        setCommon(message);
        message.setContent(json.toJSONString());
        return message;
    }
}
