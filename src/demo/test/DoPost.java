package demo.test;

import java.util.HashMap;
import java.util.Map;

import demo.util.HttpClientUtils;

public class DoPost {
	public static void main(String[] args) {
		String url = "http://192.168.31.254/kybaby/hydNetWorkClinic/hydNetWorkClinicAction.action";
		Map<String,String> params = new HashMap<String,String>();
		params.put("action", "getNetworkClinicOrderList");//执行方法
		System.out.println(HttpClientUtils.doPost(url, params));
	}
}
