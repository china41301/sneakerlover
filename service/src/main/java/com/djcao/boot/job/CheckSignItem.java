package com.djcao.boot.job;

import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author djcao
 * @workcode wb-cdj390654
 * @date 2019-07-20
 */
public class CheckSignItem implements Delayed {

    private String itemId;

    private long delayTime;

    private TimeUnit unit;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }


    @Override
    public long getDelay(TimeUnit timeUnit) {
        return timeUnit.convert(delayTime,unit);
    }

    @Override
    public int compareTo(Delayed o) {
        return getDelay(TimeUnit.SECONDS) > o.getDelay(TimeUnit.SECONDS) ? 1 : -1;
    }
}
