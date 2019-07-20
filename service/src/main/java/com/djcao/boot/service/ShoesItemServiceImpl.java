package com.djcao.boot.service;

import com.djcao.boot.common.PackageResult;
import com.djcao.boot.repository.RegisterUser;
import com.djcao.boot.repository.RegisterUserRepository;
import com.djcao.boot.repository.ShoesItem;
import com.djcao.boot.repository.ShoesItemRepository;
import com.djcao.boot.repository.User;
import com.djcao.boot.vo.ShoesItemVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.jws.soap.SOAPBinding.Use;

@Service
public class ShoesItemServiceImpl implements ShoesItemService{
    @Autowired
    private ShoesItemRepository shoesItemRepository;

    @Autowired
    private RegisterUserRepository registerUserRepository;

    @Override
    @SuppressWarnings("unchecked")
    public PackageResult<ShoesItemVO> getShoesItemById(Long id, User user) throws Exception {
        ShoesItem shoesItem = shoesItemRepository.findById(id).orElseThrow(() -> new Exception("未找到球鞋信息"));
        int notReservationByUserIdCount = registerUserRepository.findNotReservationByUserIdCount(
            user.getId(), id);
        ShoesItemVO shoesItemVO = new ShoesItemVO();
        BeanUtils.copyProperties(shoesItem,shoesItemVO);
        shoesItemVO.setMaxRegisterNumber(notReservationByUserIdCount);
        return PackageResult.success(shoesItemVO);
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
    public PackageResult<List<ShoesItem>> addNewShoesItem(List<ShoesItem> ShoesItems) throws Exception {
        return PackageResult.success().setResult(shoesItemRepository.saveAll(ShoesItems));
    }
}
