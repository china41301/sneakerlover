package com.djcao.boot.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
/**
 * 预约登记返回的Response响应体
 */
public class YYSignResponse {
    /**
     * 登记鞋子的会员账号token
     */
    private String token;
    /**
     * 登记鞋子的item_id
     */
    private String itemId;
    /**
     * 预约登记返回的抽签码
     */
    private String sign_id;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getSign_id() {
        return sign_id;
    }

    public void setSign_id(String sign_id) {
        this.sign_id = sign_id;
    }
}
