package demo.test.kybabyDoctor.fdServiceManager;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import demo.util.HttpClientUtil;

public class FdServiceRecordTest {
	private final String url = "http://localhost:8080/kybabyDoctor/orgBusiness/fdPreServiceRecordAction.action";
	//private final String url = "http://dev.qiyico.com/kybaby/orgBusiness/fdPreServiceRecordAction.action";
	/**
	 * 获取操作人员服务的电话预约订单
	 */
	@Test
	public void getTelBookOrdersTest(){
		Map<String, String> params = new HashMap<String, String>();
		params.put("action", "getTelBookOrders");
		//params.put("userInfo.parentName", "尚瑞");//姓名
		//params.put("userInfo.phone", "15008491025");//电话
		String doPost = HttpClientUtil.doPost(url, params);
		System.out.println(doPost);
	}
	/**
	 * 编辑电话预约订单
	 */
	@Test
	public void updateTelBookOrderTest(){
		Map<String, String> params = new HashMap<String, String>();
		params.put("action", "updateTelBookOrder");
		params.put("fdPreServiceRecord.id", "1");//服务记录id
		String doPost = HttpClientUtil.doPost(url, params);
		System.out.println(doPost);
	}
}
