import java.net.URI;
import java.util.List;
import java.util.Optional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.djcao.boot.common.PackageResult;
import com.djcao.boot.repository.RegisterUser;
import com.djcao.boot.repository.User;
import com.djcao.boot.repository.UserRepository;
import com.djcao.boot.service.RegisterUserService;
import com.djcao.boot.so.RegisterUserSo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootApplication.class)
@WebAppConfiguration
@ContextConfiguration
public class BootApplicationTests {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RegisterUserService registerUserService;

    @Autowired
    RestTemplate restTemplate;
	@Test
	public void contextLoads() {
        Optional<User> byId = userRepository.findById(1L);
        User user = byId.get();
        user.setPhoneNum(12312312);
        userRepository.save(user);
    }

    @Test
    public void test_FindByUserId(){
        RegisterUserSo registerUserSo =new RegisterUserSo();
        registerUserSo.setUserId(2L);
        PackageResult<List<RegisterUser>> byUserId = registerUserService.findByUserId(registerUserSo);
        assert byUserId.getPageSize() > 0;
	}

	@Test
    public void test_FetchURL() throws Exception {
        try {
            JSONObject json = new JSONObject();
            PackageResult<RegisterUser> byId = registerUserService.findById(1L);
            RegisterUser result = byId.getResult();
            result.setUserName("17826805734");
            result.setPassword("EASTlove7_yy");
            json.put("data",result);
            json.put("code","10086");
            //URI uri = restTemplate.postForLocation("http://192.168.0.108:5000/yy/login", json);
            //Object execute = restTemplate.execute(uri, HttpMethod.POST, null, null);
            Object jsonObject = restTemplate.postForObject("http://192.168.0.108:5000/yy/login", json,
                Object.class);
            System.out.println(jsonObject);
        }catch (Exception ignore){

        }
    }
}
