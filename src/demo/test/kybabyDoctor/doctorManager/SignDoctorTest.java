package demo.test.kybabyDoctor.doctorManager;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import demo.util.HttpClientUtil;

public class SignDoctorTest {
	
	private final String url = "http://localhost:8080/kybabyDoctor/DoctorDataGather/doctorDataGather.action";
	//private final String url = "http://dev.qiyico.com/kybaby/DoctorDataGather/doctorDataGather.action";
	/**
	 * 维护医生信息查询
	 */
	@Test
	public void maintenanceDoctorTest(){
		Map<String, String> params = new HashMap<String, String>();
		params.put("action", "maintenanceDoctor");
		String doPost = HttpClientUtil.doPost(url, params);
		System.out.println(doPost);
	}
	/**
	 * 检查医生电话是否重复
	 */
	@Test
	public void checkDoctorPhoneTest(){
		Map<String, String> params = new HashMap<String, String>();
		params.put("action", "checkDoctorPhone");
		params.put("doctorInfo.doctorPhone", "23699080687");//电话
		String doPost = HttpClientUtil.doPost(url, params);
		System.out.println(doPost);
	}
	/**
	 * 开单查询
	 */
	@Test
	public void queryDoctorOrderSummaryTest(){
		Map<String, String> params = new HashMap<String, String>();
		params.put("action", "queryDoctorOrderSummary");
		String doPost = HttpClientUtil.doPost(url, params);
		System.out.println(doPost);
	}
	
	/**
	 * 要签约的医生信息查询
	 */
	@Test
	public void querySignDoctorTest(){
		Map<String, String> params = new HashMap<String, String>();
		params.put("action", "querySignDoctor");
		params.put("professionFlag", "医生");//医生;护士;技师
		String doPost = HttpClientUtil.doPost(url, params);
		System.out.println(doPost);
	}
	/**
	 * 提交审批
	 */
	@Test
	public void commitApproveTest(){
		Map<String, String> params = new HashMap<String, String>();
		params.put("action", "commitApprove");
		params.put("doctorInfo.id", "302");//医生ID
		String doPost = HttpClientUtil.doPost(url, params);
		System.out.println(doPost);
	}
	@Test
	public void initDoctorInfoPageTest(){
		Map<String, String> params = new HashMap<String, String>();
		params.put("action", "initDoctorInfoPage");
		params.put("professionFlag", "医生");//医生;护士;技师
		params.put("doctorInfo.id", "322");//医生ID
		String doPost = HttpClientUtil.doPost(url, params);
		System.out.println(doPost);
	}
	/**
	 * 保存医生基本信息
	 */
	@Test
	public void saveOrUpdateDoctorBasicInfoTest(){
		Map<String, String> params = new HashMap<String, String>();
		params.put("action", "saveOrUpdateDoctorBasicInfo");
		//params.put("doctorInfo.id", "339");//医生姓名
		params.put("doctorInfo.doctorName", "测试2016118");//医生姓名
		params.put("doctorInfo.doctorSex", "男");//医生姓名
		params.put("doctorInfo.doctorPhone", "23699080687");//电话
		params.put("doctorInfo.doctorType", "23699080687");//医生类型
		params.put("doctorInfo.doctorTitle", "主任医师");//职称
		params.put("doctorInfo.doctorEmployer", "成都市第一人民医院");//工作单位
		params.put("doctorInfo.hospitalId", "1");//工作单位id
		params.put("doctorInfo.doctorType", "康优儿保医生");//工作单位id
		params.put("doctorInfo.department", "儿科");//科室
		params.put("doctorInfo.clinicalExperience", "20");//临床经验
		params.put("doctorInfo.major.id", "3");//主专业
		params.put("doctorInfo.secondMajorIds", "16::19");//亚专业
		params.put("doctorInfo.thirdMajorIds", "17::20");//病种
		params.put("doctorInfo.serviceTypeIds", "2::3::5::8::1");//开通服务
		params.put("doctorInfo.imgBase64", "1234");//图片base64编码
		params.put("doctorInfo.bankAccountName", "工商银行");//开户行
		params.put("doctorInfo.bankCard", "6212264000036285290");//银行卡
		params.put("doctorInfo.doctorComment", "4396");//个人简介
		params.put("doctorInfo.recommendPhone", "4396");//推荐人电话
		params.put("doctorInfo.doctorImpression", "好影响");//医生评价
		params.put("doctorInfo.idCardNum", "123456");//身份证
		params.put("doctorInfo.idCard", "123456");//职业资格证号
		//params.put("doctorLifeInfo.id", "1");//医生生活ID
		params.put("doctorLifeInfo.graduateSchool", "华西");//毕业学校
		params.put("doctorLifeInfo.degree", "研究生");//学位
		params.put("doctorLifeInfo.hospitalMonthlIncome", "10K");//院内月收入
		params.put("doctorLifeInfo.isDivorce", "N");//是否离异（Y/N）
		params.put("doctorLifeInfo.specialInterests", "123");//特殊爱好
		params.put("doctorLifeInfo.remark", "执业证书");//备注
		String doPost = HttpClientUtil.doPost(url, params);
		System.out.println(doPost);
	}
	/**
	 * 上传医生资格证书图片
	 */
	@Test
	public void uploadDoctorImageInfoTest(){
		Map<String, String> params = new HashMap<String, String>();
		params.put("action", "uploadDoctorImageInfo");
		params.put("doctorCardInfo.doctorInfo.id", "302");//医生ID
		params.put("doctorCardInfo.imgBase64", "");//执业证书图片base64
		params.put("doctorCardInfo.imgType", "执业证书");//图片类型
		String doPost = HttpClientUtil.doPost(url, params);
		System.out.println(doPost);
	}
	/**
	 * 删除医生资格证书图片
	 */
	@Test
	public void removeDoctorImageInfoTest(){
		Map<String, String> params = new HashMap<String, String>();
		params.put("action", "removeDoctorImageInfo");
		params.put("doctorCardInfo.id", "7");//医生ID
		String doPost = HttpClientUtil.doPost(url, params);
		System.out.println(doPost);
	}
}
