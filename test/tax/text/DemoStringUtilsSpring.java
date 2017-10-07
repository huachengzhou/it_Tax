package tax.text;

import org.springframework.util.StringUtils;

public class DemoStringUtilsSpring {
	public static void main(String[] args) {
		String str1 = "";
		Boolean flag = StringUtils.isEmpty(str1);
		System.out.println(flag);
	}
}
