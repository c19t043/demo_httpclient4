package demo.test.familydoctor;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import demo.util.HttpClientUtil;

public class WxTest {
	//
	private final String domainName = "http://localhost:8080/familyDoctor";
	//private final String domainName = "http://dev.qiyico.com/kybaby";
	/**
	 * 接口地址
	 */
	private final String url = domainName+"/wx/indexUserAuth.action";
	@Test
	public void loginTest(){
		Map<String,String> params = new HashMap<String,String>();
		params.put("code", "39");
		params.put("hospitalId", "39");
		HttpClientUtil.doPost(url, params);
	}
}
