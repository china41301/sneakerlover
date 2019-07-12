package com.djcao.boot.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.djcao.boot.common.PackageResult;
import com.djcao.boot.common.PythonResult;
import com.djcao.boot.repository.RegisterUser;
import com.djcao.boot.repository.RegisterUserGroupRepository;
import com.djcao.boot.repository.RegisterUserRepository;
import com.djcao.boot.so.RegisterUserSo;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
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
        if (Objects.isNull(registerUser.getUserId()) || StringUtils.isBlank(registerUser
            .getUserName()) || StringUtils.isBlank(registerUser.getPassword())){
            return PackageResult.error("userId或者userName或者password为空");
        }
        PythonResult<List<RegisterUser>> login = yyService.login(Lists.newArrayList(registerUser));
        if (!login.getCode().equals("1")){
            return PackageResult.error("东哥返回失败");
        }
        RegisterUser save = registerUserRepository.save(login.getData().get(0));
        return PackageResult.success(save);
    }

    @Override
    public PackageResult<RegisterUser> update(RegisterUser registerUser) {
        if (Objects.isNull(registerUser.getId())){
            return PackageResult.error("id不能为空");
        }
        if (registerUser.getGroupId() != null ){
            if (!registerUserGroupRepository.existsById(registerUser.getGroupId())){
                return PackageResult.error("用户组不存在");
            }
        }
        Optional<RegisterUser> byId = registerUserRepository.findById(registerUser.getId());
        if (!byId.isPresent()){
            return PackageResult.error("抢鞋用户不存在");
        }
        RegisterUser dbUser= byId.get();
        dbUser.setName(registerUser.getName());
        dbUser = registerUserRepository.save(dbUser);
        return PackageResult.success(dbUser);
    }

    @Override
    public PackageResult<Boolean> delete(Long id) {
        if (Objects.isNull(id))
            return PackageResult.error("id不能为空");
        registerUserRepository.deleteById(id);
        return PackageResult.success(Boolean.TRUE);
    }

    @Override
    public PackageResult<List<RegisterUser>> importFromExcel(Object obj) {
        return null;
    }

    @Override
    public PackageResult<RegisterUser> findById(Long id) throws Exception {
        if (Objects.isNull(id))
            return PackageResult.error("id不能为空");
        return PackageResult.success(registerUserRepository.findById(id).orElseThrow( () ->  new Exception("未找到登记信息")));
    }

    // FIXME 没有分页返回pageSize pageNum
    @Override
    public PackageResult<List<RegisterUser>> findByUserId(RegisterUserSo registerUserSo) {
        if (Objects.isNull(registerUserSo.getUserId()))
            return PackageResult.error("userId不能为空");
        Pageable pageable = PageRequest.of(registerUserSo.getPageNum(), registerUserSo.getPageSize(),
            Direction.DESC, "createTime");
        Page<RegisterUser> registerUserByUserId = registerUserRepository.findRegisterUserByUserId(
            registerUserSo.getUserId(), pageable);
        return new PackageResult<List<RegisterUser>>().setPage(registerUserByUserId);
    }
}
