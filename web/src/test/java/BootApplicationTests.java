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
import com.djcao.boot.so.RegisterUserSo;
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

	@Test
	public void contextLoads() throws Exception{
        List<RegisterShoesRequest> list = new ArrayList<>();
        RegisterShoesRequest shoesRequest1 = new RegisterShoesRequest();
        shoesRequest1.setShoesItemId("1222");
        shoesRequest1.setActivityShopId(2222);
        shoesRequest1.setShoesSize("8.5");
        shoesRequest1.setReservationNumber(2);

        RegisterShoesRequest shoesRequest2 = new RegisterShoesRequest();
        shoesRequest2.setShoesItemId("1222");
        shoesRequest2.setActivityShopId(3333);
        shoesRequest2.setShoesSize("8");
        shoesRequest2.setReservationNumber(2);
        list.add(shoesRequest1);
        list.add(shoesRequest2);
	    User user = userRepository.getOne(1L);
        PackageResult<String> result = reservationService.registerShoes(list, user);
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
