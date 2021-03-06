package com.djcao.boot.service;

import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import com.djcao.boot.common.PackageResult;
import com.djcao.boot.common.PythonResult;
import com.djcao.boot.repository.RegisterUser;
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
import org.springframework.util.CollectionUtils;

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
    private YYService yyService;
    @Override
    public PackageResult<RegisterUser> save(RegisterUser registerUser) {
        if (Objects.isNull(registerUser.getUserId()) || StringUtils.isBlank(registerUser
            .getUserName()) || StringUtils.isBlank(registerUser.getPassword())){
            return PackageResult.error("userId或者userName或者password为空");
        }

        RegisterUser byUserName = registerUserRepository.findByUserName(registerUser.getUserName());
        if (byUserName != null && byUserName.getId() != null){
            return PackageResult.error("该账户已经添加,无法再次添加");
        }

        PythonResult<List<Map<String,String>>> login;
        try {
            login = yyService.login(Lists.newArrayList(registerUser));
        }catch (Exception ex){
            return PackageResult.error("快联系东哥，登录获取token挂了。异常信息:"+ex.getMessage());
        }
        if (!login.getCode().equals("0") || CollectionUtils.isEmpty(login.getData())
            || "failed".equalsIgnoreCase(login.getData().get(0).get("token"))){
            return PackageResult.error("东哥返回失败");
        }
        registerUser.setToken(login.getData().get(0).get("token"));
        registerUser.setCreateTime(new Date());
        registerUser.setUpdateTime(new Date());
        registerUser.setStatus(1);
        registerUser = registerUserRepository.save(registerUser);
        return PackageResult.success(registerUser);
    }

    @Override
    public PackageResult<RegisterUser> update(RegisterUser registerUser) {
        if (Objects.isNull(registerUser.getId())){
            return PackageResult.error("id不能为空");
        }
        Optional<RegisterUser> byId = registerUserRepository.findById(registerUser.getId());
        if (!byId.isPresent()){
            return PackageResult.error("用户不存在，刷新后重试");
        }
        RegisterUser dbUser= byId.get();
        PythonResult<List<Map<String,String>>> login;
        try {
            login = yyService.login(Lists.newArrayList(registerUser));
        }catch (Exception ex){
            return PackageResult.error("快联系东哥，登录获取token挂了。异常信息:"+ex.getMessage());
        }
        if (!login.getCode().equals("0") || CollectionUtils.isEmpty(login.getData()) ||
            "failed".equalsIgnoreCase(login.getData().get(0).get("token"))){
            return PackageResult.error("东哥返回失败");
        }
        registerUser.setToken(login.getData().get(0).get("token"));
        registerUser.setUpdateTime(new Date());
        registerUser = registerUserRepository.save(registerUser);
        return PackageResult.success(registerUser);
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

    @Override
    @SuppressWarnings("unchecked")
    public PackageResult<List<RegisterUser>> findByUserIds(List<Long> ids) {
        return PackageResult.success().setResult(registerUserRepository.findRegisterUsersByIds(ids));
    }
}
