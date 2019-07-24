package com.djcao.boot.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.NonNull;

@Setter
@Getter
@RequiredArgsConstructor
/**
 * 登记结果查询响应体
 */
public class RegisterResultResponse {
    @NonNull
    private String itemId;

    @NonNull
    private String shoesSize;
//    private int activityShopId;

    @NonNull
    private String shopName;

    /**
     * 总登记数量
     */
    @NonNull
    private Long reservationNumber;
    /**
     * 登记成功的数量
     */
    private Long reservationSuccessNumber;
}
