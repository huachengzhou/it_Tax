package tax.service.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;

import cn.tax.nswf.entity.Info;
import cn.tax.nswf.entity.User;
import tax.util.ApplicationUtils;

public class BaseTest {
	ApplicationContext ctx = ApplicationUtils.getContext();
	InfoService_ infoService_ = ctx.getBean(InfoService_.class);
	
	@Test
	public void isit(){
		Info info = infoService_.findObjectById(Info.class,"4028deed5e6b6028015e6b69abdd0000");
		System.out.println(info);
		List<Info> infos = infoService_.findObjects(Info.class);
		System.out.println(infos);
	}
}
