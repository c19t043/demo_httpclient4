package demo.test.kybaby.paycallback;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import demo.util.HttpClientUtil;

public class PlatformNoticeTest {
	private final String baseUrl = "http://localhost:8080/kybaby";
//	private final String baseUrl = "http://dev.qiyico.com";
	private final String url = baseUrl+"/wx/wxpay.action";
	@Test
	public void WxPayCallBackProxyTest(){
		String doPost = HttpClientUtil.doPostText(url, "");
		System.out.println(doPost);
	}
	
}
