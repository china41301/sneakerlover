package com.djcao.boot.job;

import java.awt.print.Pageable;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import com.djcao.boot.repository.ShoesItem;
import com.djcao.boot.repository.ShoesItemRepository;
import com.djcao.boot.service.PythonCallbackServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import static com.djcao.boot.common.BusinessStatus.ShoesStatusEnum.OVER_RESERVATION;

/**
 * @author djcao
 * @workcode wb-cdj390654
 * @date 2019-07-20
 */
@Component
public class JobService {

    @Autowired
    private ShoesItemRepository shoesItemRepository;

    @Autowired
    private PythonCallbackServiceImpl pythonCallbackService;

    public ScheduledExecutorService threadPoolExecutor = Executors.newScheduledThreadPool(1);

    Logger logger = LoggerFactory.getLogger(JobService.class);

    @PostConstruct
    public void init(){
        threadPoolExecutor.scheduleAtFixedRate(new QuerySignResult(),0,10, TimeUnit.MINUTES);
    }

    public class QuerySignResult implements Runnable{

        @Override
        public void run() {
            boolean hasNext = Boolean.TRUE;
            PageRequest pageRequest = PageRequest.of(0, 20);
            while (hasNext){
                Page<ShoesItem> byStatus = shoesItemRepository.findByStatus(OVER_RESERVATION.getStatus(), pageRequest);
                List<ShoesItem> content = byStatus.getContent();
                hasNext = content.size() > 0;
                if (content.size() > 0){
                    try {
                        pythonCallbackService.shoesOffLoadingV1(content.stream().map(ShoesItem::getItemId).collect(
                            Collectors.toList()));
                    }catch (Exception ex){
                        logger.error("job error",ex);
                    }
                }
            }
        }
    }
}
