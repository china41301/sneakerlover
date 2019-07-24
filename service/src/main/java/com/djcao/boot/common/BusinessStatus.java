package com.djcao.boot.common;

public interface BusinessStatus {

    /**
     * 预约登记流程状态
     */
    enum ReservationStatusEnum {
        RESERVATION_SUCCESS(0,"调用东哥登记成功"),
        RESERVATION_FAIL(1,"调用东哥登记失败"),
        GOT_THEM(2,"抽中鞋子"),
        LOSS_THEM(3,"没有抽中"),
        SHOES_OFF_LOAD(4,"鞋子下架")
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
        DURING_RESERVATION((byte) 0,"登记中"),
        OVER_RESERVATION((byte)1,"登记结束");

        byte status;
        String message;

        ShoesStatusEnum(byte status, String message) {
            this.status = status;
            this.message = message;
        }

        public byte getStatus() {
            return status;
        }

        public String getMessage() {
            return message;
        }

    }
}
