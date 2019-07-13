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
}
