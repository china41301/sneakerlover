package com.djcao.boot.service;

import java.util.Date;

import com.alibaba.fastjson.JSONObject;
import com.djcao.boot.common.PackageResult;
import com.djcao.boot.repository.User;
import com.djcao.boot.repository.UserRepository;
import com.djcao.boot.util.SwitchHelper;
import com.djcao.boot.util.UserSo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author djcao
 * @workcode wb-cdj390654
 * @date 2019-07-03
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    RestTemplate restTemplate;

    @Value(value = "${wechat.login.api}")
    private String wxLoginApi;

    @Value(value = "${wechat.appId}")
    private String appId;

    @Value(value = "${wechat.appSecret}")
    private String appSecret;

    @Override
    public PackageResult<User> findById(Long id) {
        PackageResult<User> packageResult = PackageResult.success();
        packageResult.setResult(userRepository.findById(id).orElse(new User()));
        return packageResult;
    }

    @Override
    public PackageResult<User> login(UserSo so) {
        if (StringUtils.isNotBlank(so.getCode())){
            String code = so.getCode();
            JSONObject object = JSONObject.parseObject(restTemplate.getForObject(wxLoginApi + "?appid=" + appId + "&secret=" + appSecret + "&js_code=" + code + "&grant_type=authorization_code", String.class));
            String session_key = object.getString("session_key");
            String open_id = object.getString("openid");
            //TODO 使用session_key和open_id自定义3rd_session的逻辑
            if (object.getIntValue("errcode") != 0){
                return PackageResult.error("登录失败,获取open_id失败");
            }
            User dbUser = userRepository.findByAccount(open_id);
            if (dbUser == null){
                dbUser = new User();
                dbUser.setCreateTime(new Date());
                dbUser.setUpdateTime(new Date());
                dbUser.setIsVip((byte)0);
                dbUser.setAccount(open_id);
                dbUser.setIsWeChat((byte)1);
                dbUser.setSessionKey(session_key);
                dbUser = userRepository.save(dbUser);
            }
            return PackageResult.success(dbUser);

        }else {
            if (StringUtils.isBlank(so.getAccount()) || StringUtils.isBlank(so.getPassword())){
                return PackageResult.error("用户名或密码为空");
            }
            String account = so.getAccount();
            String password = so.getPassword();
            User dbUser = userRepository.findByAccount(account);
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
                dbUser.setPasswd(password);
                dbUser.setPhoneNum(account);
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
