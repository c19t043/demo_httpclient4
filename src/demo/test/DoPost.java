package demo.test;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import demo.util.HttpClientUtils;

public class DoPost {
	public static void main(String[] args) {
		//Map<String, String> params = new HashMap<String,String>();
		String url = "http://localhost:8080/fileserver/fileupload";
		Map<String,String> params = new HashMap<String,String>();
		params.put("fileBase64", "中文");
		params.put("filePath", "中文");
		/*if(StringUtils.isNotBlank(specifyHandleMethod)){
			params.put("specifyHandleMethod", specifyHandleMethod);
		}*/
		String doPost = HttpClientUtils.doPost(url, params);
		System.out.println(doPost);
	}
}
