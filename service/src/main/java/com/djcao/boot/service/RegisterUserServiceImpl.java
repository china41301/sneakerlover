package com.djcao.boot.service;

import java.util.List;

import com.djcao.boot.common.PackageResult;
import com.djcao.boot.common.PythonResult;
import com.djcao.boot.repository.RegisterUser;
import com.djcao.boot.repository.RegisterUserGroupRepository;
import com.djcao.boot.repository.RegisterUserRepository;
import com.djcao.boot.so.RegisterUserSo;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

/**
 * @author djcao
 * @workcode wb-cdj390654
 * @date 2019-07-06
 */
@Service
public class RegisterUserServiceImpl implements RegisterUserService {

    @Autowired
    private RegisterUserRepository registerUserRepository;

    @Autowired
    private RegisterUserGroupRepository registerUserGroupRepository;

    @Autowired
    private YYService yyService;
    @Override
    public PackageResult<RegisterUser> save(RegisterUser registerUser) {
        PythonResult<List<RegisterUser>> login = yyService.login(Lists.newArrayList(registerUser));
        if (!login.getCode().equals("1")){
            return PackageResult.error("东哥返回失败");
        }
        RegisterUser save = registerUserRepository.save(login.getData().get(0));
        return PackageResult.success(save);
    }

    @Override
    public PackageResult<RegisterUser> update(RegisterUser registerUser) {
        if (registerUser.getGroupId() != null ){
            if (!registerUserGroupRepository.existsById(registerUser.getGroupId())){
                return PackageResult.error("用户组不存在");
            }
        }
        RegisterUser save = registerUserRepository.save(registerUser);
        return PackageResult.success(save);
    }

    @Override
    public PackageResult<Boolean> delete(Long id) {
        registerUserRepository.deleteById(id);
        return PackageResult.success(Boolean.TRUE);
    }

    @Override
    public PackageResult<List<RegisterUser>> importFromExcel(Object obj) {
        return null;
    }

    @Override
    public PackageResult<RegisterUser> findById(Long id) throws Exception {
        return PackageResult.success(registerUserRepository.findById(id).orElseThrow( () ->  new Exception("未找到登记信息")));
    }

    // FIXME 没有分页返回pageSize pageNum
    @Override
    public PackageResult<List<RegisterUser>> findByUserId(RegisterUserSo registerUserSo) {
        Pageable pageable = PageRequest.of(registerUserSo.getPageNum(), registerUserSo.getPageSize(),
            Direction.DESC, "createTime");
        Page<RegisterUser> registerUserByUserId = registerUserRepository.findRegisterUserByUserId(
            registerUserSo.getUserId(), pageable);
        return new PackageResult<List<RegisterUser>>().setPage(registerUserByUserId);
    }
}
