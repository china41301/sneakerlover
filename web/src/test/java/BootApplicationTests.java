import java.util.Optional;

import com.djcao.boot.repository.User;
import com.djcao.boot.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootApplication.class)
@WebAppConfiguration
@ContextConfiguration
public class BootApplicationTests {

    @Autowired
    UserRepository userRepository;
	@Test
	public void contextLoads() {
        Optional<User> byId = userRepository.findById(1L);
        User user = byId.get();
        user.setPhoneNum(12312312);
        userRepository.save(user);
    }

}
