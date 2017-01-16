package demo.test.familydoctor;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import demo.util.HttpClientUtil;

public class Test1 {
	@Test
	public void loginTest(){
		Map<String, String> params = new HashMap<String, String>();
		params.put("action", "login");//就诊卡ID
		params.put("userPassword", "123");//就诊卡
		params.put("userPhone", "18160031787");//姓名
		String doPost = HttpClientUtil.doPost("http://127.0.0.1:8080/fileserver/fileupload", params);
		System.out.println(doPost);
	}
}
