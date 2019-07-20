package com.djcao.boot.common;

public interface BusinessStatus {

    /**
     * 预约登记流程状态
     */
    enum ReservationStatusEnum {
        RESERVATION_SUCCESS(1,"调用东哥登记成功"),
        RESERVATION_FAIL(2,"调用东哥登记失败"),
        GOT_THEM(3,"抽中鞋子"),
        LOSS_THEM(4,"没有抽中"),


        ;

        int status;
        String message;

        ReservationStatusEnum(int status, String message) {
            this.status = status;
            this.message = message;
        }

        public int getStatus() {
            return status;
        }

        public String getMessage() {
            return message;
        }
    }

    /**
     * 鞋子状态
     */
    enum ShoesStatusEnum {
        DURING_RESERVATION(1,"登记中"),
        OVER_RESERVATION(2,"登记结束");

        int status;
        String message;

        ShoesStatusEnum(int status, String message) {
            this.status = status;
            this.message = message;
        }

        public int getStatus() {
            return status;
        }

        public String getMessage() {
            return message;
        }

    }
}
