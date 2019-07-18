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

    public String getItemId() {
        return itemId;
    }

    public YYSignRequest setItemId(String itemId) {
        this.itemId = itemId;
        return this;
    }

    public String getShoesSize() {
        return shoesSize;
    }

    public YYSignRequest setShoesSize(String shoesSize) {
        this.shoesSize = shoesSize;
        return this;
    }

    public String getShopId() {
        return shopId;
    }

    public YYSignRequest setShopId(String shopId) {
        this.shopId = shopId;
        return this;
    }

    public String getToken() {
        return token;
    }

    public YYSignRequest setToken(String token) {
        this.token = token;
        return this;
    }
}
