package demo.test.familydoctor.userConsultDoctor;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import demo.util.HttpClientUtil;

public class UserConsultDoctorTest {
	String url = "http://localhost:8080/familyDoctor/fd/userConsultDoctor.action";
	/**
	 * 获取咨询记录列表
	 */
	@Test
	public void qryConsultRecordsTest(){
		/*
		 * 图片路径
		 * String uploadDir = "../../../tomcat/kybaby/kybabyBG/admin/images/consultPicture";
		 * 在/kybabyBG/admin/images/consultPicture/下
		 */
		Map<String, String> params = new HashMap<String, String>();
		params.put("action", "getConsultRecords");
		params.put("doctorInfo.id", "39");//医生ID
		params.put("fdServicePackage.id", "1");//服务包ID
		String doPost = HttpClientUtil.doPost(url, params);
		System.out.println(doPost);
	}
	/**
	 * 保存咨询记录
	 */
	@Test
	public void saveConsultRecordTest(){
		Map<String, String> params = new HashMap<String, String>();
		params.put("action", "saveConsultRecord");
		params.put("doctorInfo.id", "39");//医生ID
		params.put("fdServicePackage.id", "1");//服务包ID
		params.put("userConsultDoctor.symptomTagIds", "1::2::3");//症状标签Id集合
		//params.put("userConsultDoctor.symptomImage", "3Uimg20160823114356.jpg");//症状图片
		params.put("userConsultDoctor.imgBase64", "123456");//传入图片base64
		params.put("userConsultDoctor.symptomDescribe", "123456");//症状描述(用户输入的内容)
		params.put("userConsultDoctor.msgType", "1");//发送内容的类型（0代表语音，1代表文字）
		params.put("userConsultDoctor.userType", "1");//0:普通用户咨询；1：家庭医生签约用户咨询
		String doPost = HttpClientUtil.doPost(url, params);
		System.out.println(doPost);
	}
}
