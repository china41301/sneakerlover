package com.djcao.boot.service;

import java.util.List;

/**
 * @author djcao
 * @workcode wb-cdj390654
 * @date 2019-07-11
 */
public interface PythonCallbackService {

    Boolean shoesOffLoadingV1(List<String> itemListId);

    Boolean shoesOffLoadingV2(List<String> itemListId);

    Boolean shoesOffLoadingV3(List<String> itemListId);
}
