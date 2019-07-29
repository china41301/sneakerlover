import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

import com.djcao.boot.common.PackageResult;
import com.djcao.boot.common.PythonResult;
import com.djcao.boot.dto.RegisterResultResponse;
import com.djcao.boot.dto.RegisterShoesRequest;
import com.djcao.boot.dto.YYCheckRequest;
import com.djcao.boot.repository.*;
import com.djcao.boot.service.RegisterUserService;
import com.djcao.boot.service.ReservationService;
import com.djcao.boot.service.UserService;
import com.djcao.boot.so.RegisterUserSo;
import com.djcao.boot.util.UserSo;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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
    RegisterUserRepository registerUserRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ReservationRegistrationRepository reservationRegistrationRepository;

    @Autowired
    private ShoesItemRepository shoesItemRepository;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    UserService userService;

	@Test
	public void contextLoads() throws Exception{
        UserSo so = new UserSo();
        so.setAccount("1");
        so.setPassword("1");
        userService.login(so);
    }

    @Test
    public void test_FindByUserId(){
        RegisterUserSo registerUserSo =new RegisterUserSo();
        registerUserSo.setUserId(2L);
        PackageResult<List<RegisterUser>> byUserId = registerUserService.findByUserId(registerUserSo);
        assert byUserId.getPageSize() > 0;
	}

	@Test
    public void test_getShoes() throws Exception{
        List<Long> ids = new ArrayList<>();
        ids.add(3L);
        ids.add(4L);
        ids.add(5L);
        List<RegisterUser> registerUsers = registerUserRepository.findRegisterUsersByIds(ids);
        System.out.println(registerUsers);
    }

	@Test
    public void test_FetchURL() throws Exception {
        try {
            JSONObject json = new JSONObject();
            YYCheckRequest pythonYYCheckReq = new YYCheckRequest();
//            pythonYYCheckReq.setItemId("123");
//            pythonYYCheckReq.setToken("beasdsd2313");
            json.put("data", Lists.newArrayList(pythonYYCheckReq));
            json.put("code",10086);
            PythonResult jsonObject = restTemplate.postForObject("http://47.111.163.9:5000/yy/check", json,
                    PythonResult.class);
            System.out.println("################");
            System.out.println(jsonObject);
        }catch (Exception ignore){
            System.out.println(ignore.getMessage());
        }
    }
}
