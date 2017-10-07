package tax.text;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.context.ApplicationContext;

import tax.util.ApplicationUtils;
import cn.tax.nswf.entity.User;
import cn.tax.nswf.service.UserService;

public class UserServiceTest {
	private static ApplicationContext ctx = ApplicationUtils.getContext();
	private static UserService userService = ctx.getBean(UserService.class);
	public static void save(){
		User user = new User();
		user.setAccount("aomin");
		user.setDept("B部门");
		user.setGender(true);
		user.setEmail("aomin"+"@163.com");
		user.setState(User.USER_STATE_VALID);
		user.setBirthday(new Date());
		user.setName("管理员");
		user.setMemo("无");
		user.setMobile(""+new Random().nextLong());
		user.setId(UUID.randomUUID().toString());
		user.setPassword("123456");
		userService.save(user);
	}
	public static void isGet(){
		User user = userService.findObjectById("ff8080815e54f681015e54f683720000");
		System.out.println(user);
	}
	public static void findObjects(){
		List<User> users = userService.findObjects();
		for (User user : users) {
			System.out.println(user);
		}
	}
	public static void login(){
		List<User> users = userService.findUserByAccountAndPass("admin","123456");
		System.out.println(users.get(0));
	}
	public static void main(String[] args) {
		login();
	}
}
