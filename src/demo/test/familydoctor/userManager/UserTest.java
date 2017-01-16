package demo.test.familydoctor.userManager;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import demo.util.HttpClientUtil;

public class UserTest {
	String url = "http://localhost:8080/familyDoctor/fd/userManage.action";
	/**
	 * 登陆
	 */
	@Test
	public void loginTest(){
		Map<String, String> params = new HashMap<String, String>();
		params.put("action", "login");//就诊卡ID
		params.put("userPassword", "123");//就诊卡
		params.put("userPhone", "18160031787");//姓名
		String doPost = HttpClientUtil.doPost(url, params);
		System.out.println(doPost);
	}
	@Test
	public void logoutTest(){
		Map<String, String> params = new HashMap<String, String>();
		params.put("action", "logout");//就诊卡ID
		String doPost = HttpClientUtil.doPost(url, params);
		System.out.println(doPost);
	}
}
