package demo.test.kybaby.notice;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import demo.util.HttpClientUtil;

public class PlatformNoticeTest {
	
	
	//private final String url = "http://localhost:8080/kybaby/notice/fdServiceOrganNoticeAction.action";
	private final String url = "http://dev.qiyico.com/notice/fdServiceOrganNoticeAction.action";
	@Test
	public void getFdServiceOrganNoticesByCondtionTest(){
		Map<String, String> params = new HashMap<String, String>();
		params.put("action", "getFdServiceOrganNoticesByCondtion");
		params.put("fdServiceOrganNotice.fdServicePackage.id", "1");//服务包id
		params.put("fdServiceOrganNotice.bizType", "儿保");//业务类型（儿科，儿保，计免，咨询，上门）
		String doPost = HttpClientUtil.doPost(url, params);
		System.out.println(doPost);
	}
	
}
