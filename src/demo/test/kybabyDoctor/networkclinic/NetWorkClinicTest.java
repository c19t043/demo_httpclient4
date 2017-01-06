package demo.test.kybabyDoctor.networkclinic;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import demo.util.HttpClientUtil;

public class NetWorkClinicTest {
	//
	private final String domainName = "http://localhost:8080/kybabyDoctor";
	//private final String domainName = "http://dev.qiyico.com/kybaby";
	/**
	 * 接口地址
	 */
	private final String url = domainName+"/hydNetWorkClinic/hydNetWorkClinicAction.action";
	
	/**
	 * 获取门诊检化验项目信息
	 */
	@Test
	public void getNetworkClinicCheckItemTest(){
		Map<String,String> params = new HashMap<String,String>();
		params.put("action", "getNetworkClinicCheckItem");//执行方法
		params.put("hydReqBasicInfo.id", "3");//门诊挂号基础记录id
		params.put("hydReqChecktest.id", "1");//检化验记录id
		System.out.println(HttpClientUtil.doPost(url, params));
	}
	
	/**
	 * 取消订单
	 */
	@Test
	public void cancelOrderTest(){
		Map<String,String> params = new HashMap<String,String>();
		params.put("action", "cancelOrder");//执行方法
		params.put("hydReqBasicInfo.id", "1");//门诊挂号基础记录id
		System.out.println(HttpClientUtil.doPost(url, params));
	}
	
	/**
	 * 标记完成
	 */
	@Test
	public void markCompleteTest(){
		Map<String,String> params = new HashMap<String,String>();
		params.put("action", "markComplete");//执行方法
		params.put("hydReqBasicInfo.id", "1");//门诊挂号基础记录id
		params.put("hydReqBasicInfo.appCheckTestTime", "2016-01-05 12:12:12");//门诊挂号基础记录id
		params.put("hydReqBasicInfo.appCheckTestDayTime", "下午");//门诊挂号基础记录id
		System.out.println(HttpClientUtil.doPost(url, params));
	}
	
	/**
	 * 生成挂号订单
	 */
	@Test
	public void generateNetWorkClinicOrderTest(){
		Map<String,String> params = new HashMap<String,String>();
		params.put("action", "generateNetWorkOrder");//执行方法
		params.put("hydReqBasicInfo.user_id", "");//用户id--没有就不传
		params.put("hydReqBasicInfo.user_name", "张三");//用户姓名
		params.put("hydReqBasicInfo.user_sex", "女");//用户性别
		params.put("hydReqBasicInfo.phone", "123456789");//电话
		params.put("hydReqBasicInfo.diagnosis", "test");//诊断内容
		params.put("hydReqBasicInfo.clinicalhistory", "test");//病史及体征
		params.put("hydReqBasicInfo.doctor_id", "1");//医生ID
		params.put("hydReqBasicInfo.doctor_name", "李四");//医生姓名
		
		params.put("hydReqBasicInfo.userAgeYear", "1");//用户年龄-岁
		params.put("hydReqBasicInfo.userAgeMonth", "5");//用户年龄-月
		params.put("hydReqBasicInfo.userProvince", "四川省");//省
		params.put("hydReqBasicInfo.userCity", "成都市");//市
		params.put("hydReqBasicInfo.userArea", "武侯区");//区县
		params.put("hydReqBasicInfo.userStreet", "天府二街");//街道
		params.put("hydReqBasicInfo.userAddress", "天府二街XXX");//详细地址
		params.put("hydReqBasicInfo.userNowCase", "看病");//用户当前情况
		//params.put("hydReqBasicInfo.appCheckTestTime", "2016-01-05 12:12:12");//预约检查检验时间（通知用户去检查的时间）
		params.put("hydReqBasicInfo.totalPrice", "0.01");//用户当前情况
		
		params.put("hydReqChecktestList[0].id", "1");//检化验记录id
		System.out.println(HttpClientUtil.doPost(url, params));
	}
	
	/**
	 * 挂号
	 */
	@Test
	public void networkClinicRegisterTest(){
		Map<String,String> params = new HashMap<String,String>();
		params.put("action", "networkClinicRegister");//执行方法
		params.put("hydReqBasicInfo.orderNum", "1483587782282");//门诊挂号基础记录订单编号
		//Or
		params.put("hydReqBasicInfo.id", "4");//门诊挂号基础记录id
		System.out.println(HttpClientUtil.doPost(url, params));
	}
	
	/**
	 * 门诊订单列表
	 */
	@Test
	public void qryNetworkClinicOrderHistoryTest(){
		Map<String,String> params = new HashMap<String,String>();
		params.put("action", "getNetworkClinicOrderList");//执行方法
		System.out.println(HttpClientUtil.doPost(url, params));
	}
	
	/**
	 * 门诊订单明细信息
	 */
	@Test
	public void qryNetworkClinicOrderDetailTest(){
		Map<String,String> params = new HashMap<String,String>();
		params.put("action", "getNetworkClinicOrderDetail");//执行方法
		params.put("hydReqBasicInfo.id", "4");//门诊挂号基础记录id
		System.out.println(HttpClientUtil.doPost(url, params));
	}
	
	/**
	 * 得到当天预约用户信息列表
	 */
	@Test
	public void getNowDayUserInfoListTest(){
		Map<String,String> params = new HashMap<String,String>();
		params.put("action", "getNowDayUserInfoList");//执行方法
		System.out.println(HttpClientUtil.doPost(url, params));
	}
	
	/**
	 * 门诊订单明细信息
	 */
	@Test
	public void getDoctorInfoListTest(){
		Map<String,String> params = new HashMap<String,String>();
		params.put("action", "getDoctorInfoList");//执行方法
		System.out.println(HttpClientUtil.doPost(url, params));
	}
}
