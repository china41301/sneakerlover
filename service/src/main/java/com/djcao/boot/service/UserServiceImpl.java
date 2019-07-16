package com.djcao.boot.service;

import java.util.Date;

import com.djcao.boot.common.PackageResult;
import com.djcao.boot.repository.User;
import com.djcao.boot.repository.UserRepository;
import com.djcao.boot.util.SwitchHelper;
import com.djcao.boot.util.UserSo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author djcao
 * @workcode wb-cdj390654
 * @date 2019-07-03
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public PackageResult<User> findById(Long id) {
        PackageResult<User> packageResult = PackageResult.success();
        packageResult.setResult(userRepository.findById(id).orElse(new User()));
        return packageResult;
    }

    @Override
    public PackageResult<User> login(UserSo so) {
        if (StringUtils.isBlank(so.getAccount()) || StringUtils.isBlank(so.getPassword())){
            return PackageResult.error("用户名或密码为空");
        }
        String account = so.getAccount();
        String password = so.getPassword();
        User dbUser = userRepository.findByPhoneNumOrEmail(account);
        //to register
        if (dbUser == null){
            if (StringUtils.isBlank(account) || StringUtils.isBlank(password)){
                return PackageResult.error("请输入用户名或者密码");
            }
            if (SwitchHelper.isSimple()){
                return PackageResult.error("用户不存在");
            }
            dbUser = new User();
            dbUser.setCreateTime(new Date());
            dbUser.setUpdateTime(new Date());
            dbUser.setIsVip((byte)0);
            dbUser.setPasswd(password.replace(" ",""));
            dbUser.setPhoneNum(Integer.valueOf(account));
            dbUser = userRepository.save(dbUser);
            return PackageResult.success(dbUser);
        }else {
            if (dbUser.getPasswd().equals(password)){
                return PackageResult.success(dbUser);
            }else {
                return PackageResult.error("密码错误");
            }
        }
    }

    @Override
    public PackageResult<User> save(User user) {
        user.setId(null);
        user.setUpdateTime(new Date());
        user.setCreateTime(new Date());
        user.setIsVip(Byte.valueOf("1"));
        user = userRepository.save(user);
        return PackageResult.success(user);
    }
}
