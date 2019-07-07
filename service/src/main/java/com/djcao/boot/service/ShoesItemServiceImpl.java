package com.djcao.boot.service;

import com.djcao.boot.common.PackageResult;
import com.djcao.boot.repository.ShoesItem;
import com.djcao.boot.repository.ShoesItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoesItemServiceImpl implements ShoesItemService{
    @Autowired
    private ShoesItemRepository shoesItemRepository;

    @Override
    @SuppressWarnings("unchecked")
    public PackageResult<ShoesItem> getShoesItemById(Long id) {
        return PackageResult.success().setResult(shoesItemRepository.getOne(id));
    }

    @Override
    @SuppressWarnings("unchecked")
    public PackageResult<List<ShoesItem>> getShoesItem(int pageNo, int pageSize) {
        Sort sort = new Sort(Sort.Direction.DESC, "create_time");
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<ShoesItem> page = shoesItemRepository.getShoesItems(pageable);
        return PackageResult.success().setPage(page);
    }

    @Override
    @SuppressWarnings("unchecked")
    public PackageResult<String> addNewShoesItem(List<ShoesItem> ShoesItems) throws Exception {
        shoesItemRepository.saveAll(ShoesItems);
        return PackageResult.success();
    }
}
