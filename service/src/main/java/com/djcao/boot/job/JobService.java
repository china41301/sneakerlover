package com.djcao.boot.job;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import javax.annotation.PostConstruct;

import com.djcao.boot.repository.ShoesItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author djcao
 * @workcode wb-cdj390654
 * @date 2019-07-20
 */
@Service
public class JobService {

    //public static final DelayQueue<CheckSignItem> queue = new DelayQueue<>();

    @Autowired
    private ShoesItemRepository shoesItemRepository;

    public ScheduledExecutorService threadPoolExecutor = Executors.newScheduledThreadPool(5);

    @PostConstruct
    public void init(){

    }

    public class QuerySignResult implements Runnable{

        @Override
        public void run() {
            //shoesItemRepository.findByStatus()
        }
    }
}
