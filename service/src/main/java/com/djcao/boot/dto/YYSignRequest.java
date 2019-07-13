package com.djcao.boot.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
/**
 * 预约登记接口的Request请求体
 */
public class YYSignRequest {
    /**
     * 登记鞋子的item_id
     */
    private String itemId;
    /**
     * 登记选择的鞋码
     */
    private String shoesSize;
    /**
     * 登记选择的商店
     */
    private String shopId;
    /**
     * 登记鞋子的会员账号token
     */
    private String token;
}
