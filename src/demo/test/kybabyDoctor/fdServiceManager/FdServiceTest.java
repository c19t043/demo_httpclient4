package demo.test.kybabyDoctor.fdServiceManager;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import demo.util.HttpClientUtil;

public class FdServiceTest {
	
	private final String url = "http://localhost:8080/kybabyDoctor/orgBusiness/childCareManagerAction.action";
	//private final String url = "http://dev.qiyico.com/kybaby/DoctorDataGather/doctorDataGather.action";
	/**
	 * 获取用户购买服务包的具体信息
	 */
	@Test
	public void getUserServicePackageDetailTest(){
		Map<String, String> params = new HashMap<String, String>();
		params.put("action", "getUserServicePackageDetail");
		params.put("userChildcareAppointmentInfo.id", "489");//儿保订单id
		String doPost = HttpClientUtil.doPost(url, params);
		System.out.println(doPost);
	}
	/**
	 * 保存or更新检测记录
	 */
	@Test
	public void saveOrUpdatefdChildcareCheckRecodeTest(){
		Map<String, String> params = new HashMap<String, String>();
		params.put("action", "saveOrUpdatefdChildcareCheckRecode");
		params.put("fdChildcareCheckRecode.id", "1");//检测记录Id
		params.put("userChildcareAppointmentInfo.id", "489");//儿保订单id
		params.put("fdChildcareCheckRecode.fdChildcareProjMain.id", "1");//服务项目头信息ID
		params.put("fdChildcareCheckRecode.childcareItemIds", "1,2,3");//具体服务项目ID
		params.put("fdChildcareCheckRecode.allBasicItem", "1,2,3");//所有基础服务项目id串
		String doPost = HttpClientUtil.doPost(url, params);
		System.out.println(doPost);
	}
}
