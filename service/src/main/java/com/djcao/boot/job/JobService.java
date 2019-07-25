package com.djcao.boot.job;

import static com.djcao.boot.common.BusinessStatus.ShoesStatusEnum.OVER_RESERVATION;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSON;
import com.djcao.boot.repository.ShoesItem;
import com.djcao.boot.repository.ShoesItemRepository;
import com.djcao.boot.service.PythonCallbackServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * @author djcao
 * @workcode wb-cdj390654
 * @date 2019-07-20
 */
//@Component
public class JobService {

    @Autowired
    private ShoesItemRepository shoesItemRepository;

    @Autowired
    private PythonCallbackServiceImpl pythonCallbackService;

    public ScheduledExecutorService threadPoolExecutor = Executors.newScheduledThreadPool(5);

    Logger logger = LoggerFactory.getLogger(JobService.class);

    @PostConstruct
    public void init() {
        threadPoolExecutor.scheduleAtFixedRate(new QuerySignResultV2(), 1, 20, TimeUnit.MINUTES);
    }

    public class QuerySignResult implements Runnable {

        @Override
        public void run() {
            try {
                boolean hasNext = Boolean.TRUE;
                int step = 0;
                while (hasNext) {
                    PageRequest pageRequest = PageRequest.of(step++, 20);
                    Page<ShoesItem> byStatus = shoesItemRepository
                        .findByStatus((byte) OVER_RESERVATION.getStatus(),
                            pageRequest);
                    logger.info("item off loading item : {}",
                        JSON.toJSONString(byStatus.getContent()));
                    List<ShoesItem> content = byStatus.getContent();
                    hasNext = content.size() > 0;
                    if (content.size() > 0) {
                        try {
                            pythonCallbackService.shoesOffLoadJobProcess(
                                content.stream().map(ShoesItem::getItemId).collect(
                                    Collectors.toList()));
                        } catch (Exception ex) {
                            logger.error("job error", ex);
                        }
                    }
                }
            } catch (Exception ex) {
                logger.error("job error", ex);
                throw ex;
            }
        }
    }

    public class QuerySignResultV2 implements Runnable {

        @Override
        public void run() {
            try {
                pythonCallbackService.shoesOffLoadJobProcess(null);
            } catch (Exception ex) {
                logger.error("job error", ex);
            }
        }
    }
}
