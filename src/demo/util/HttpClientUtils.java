package demo.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;


public class HttpClientUtils {
	
	private final static String CHARSET_UTF_8 = "UTF-8";
	
    public static String doPost(String url,Map<String,String> params){
    	
    	if(params.isEmpty()) return "请求参数不能为空";
    	
    	List<NameValuePair> paramList = parseMap2NameValuePair(params);
    	
    	UrlEncodedFormEntity req_Entity = null;
		try {
			req_Entity = new UrlEncodedFormEntity(paramList,CHARSET_UTF_8);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
    	
    	return doPost(url, req_Entity);
    }
    
    public static String doPut(String url,Map<String,String> params){
    	
    	if(params.isEmpty()) return "请求参数不能为空";
    	
    	List<NameValuePair> paramList = parseMap2NameValuePair(params);
    	
    	UrlEncodedFormEntity req_Entity = null;
		try {
			req_Entity = new UrlEncodedFormEntity(paramList,CHARSET_UTF_8);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
    	
    	return doPut(url, req_Entity);
    }
    
    public static List<NameValuePair> parseMap2NameValuePair(Map<String,String> params){
    	List<NameValuePair> paramList = new ArrayList<NameValuePair>();
    	for(Entry<String, String> entry : params.entrySet()){
    		paramList.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
    	}
    	return paramList;
    }
    
    public static String doPost(String url,String content){
    	
    	StringEntity req_Entity = new StringEntity(content,CHARSET_UTF_8);

    	return doPost(url, req_Entity);
    }
    
    public static String doDelete(String url){
    	CloseableHttpClient httpClient = getHttpClient();
    	HttpDelete request = getHttpDelete(url);
    	
    	return ClientExecute(httpClient, request);
    }
    
    private static String doPut(String url,HttpEntity req_Entity){
    	CloseableHttpClient httpClient = getHttpClient();
    	HttpPut request = getHttpPut(url);
    	request.setEntity(req_Entity);
    	
    	return ClientExecute(httpClient, request);
    }
    
    private static String ClientExecute(CloseableHttpClient httpClient,HttpUriRequest request){
    	CloseableHttpResponse response = null;
    	String result = "";
    	try {
    		response = httpClient.execute(request);
    		result = getResponseContent(response);
    		
		} catch (Exception e) {
			System.out.println(e);
		}finally{
			releaseConnection(httpClient, request, response);
		}
    	return result;
    }
    
    private static String doPost(String url,HttpEntity req_Entity){
    	
    	CloseableHttpClient httpClient = getHttpClient();
    	HttpPost request = getHttpPost(url);
    	request.setEntity(req_Entity);
    	
    	return ClientExecute(httpClient, request);
    }
    
    
    private static CloseableHttpClient getHttpClient(){
		PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager();
		connManager.setMaxTotal(100);
		connManager.setDefaultMaxPerRoute(20);

		RequestConfig globalConfig = getConfig();

		CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig)// 设置全局请求配置
				.setConnectionManager(connManager)// 设置连接管理器
				.build();
		return httpClient;
	}
	
    private static HttpPut getHttpPut(String url){
    	HttpPut httpPut = new HttpPut(url);
		RequestConfig config = getConfig();
		httpPut.setConfig(config);
		return httpPut;
	}
    
    private static HttpDelete getHttpDelete(String url){
    	HttpDelete httpDelete = new HttpDelete(url);
		RequestConfig config = getConfig();
		httpDelete.setConfig(config);
		return httpDelete;
	}
    
	private static HttpPost getHttpPost(String url){
		HttpPost httppost = new HttpPost(url);
		RequestConfig config = getConfig();
		httppost.setConfig(config);
		return httppost;
	}
	
	private static RequestConfig getConfig(){
		RequestConfig config = RequestConfig.custom().setConnectionRequestTimeout(5000) // 设置从connectManager获取Connection,超时时间，单位毫秒
				.setConnectTimeout(5000) // 设置连接超时时间，单位毫秒
				.setSocketTimeout(5000) // 请求获取数据的超时时间，单位毫秒
				.build();
		return config;
	}
	
    public static void releaseConnection(CloseableHttpClient httpClient,HttpUriRequest request,CloseableHttpResponse response){
		if(httpClient!=null){
			try {
				httpClient.close();
				httpClient.getConnectionManager().shutdown();
			} catch (IOException e) {
				System.out.println(e);
			}
		}
		if(request!=null){
			((HttpRequestBase) request).releaseConnection();
		}
		if(response!=null){
			try {
				response.close();
			} catch (IOException e) {
				System.out.println(e);
			}
		}
	}
    private static String getResponseContent(CloseableHttpResponse response) {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = null;
		BufferedInputStream bufferIn = null ;
		try {
			HttpEntity res_Entity = response.getEntity();
			res_Entity = new BufferedHttpEntity(res_Entity);
			if(res_Entity!=null){
				InputStream in = res_Entity.getContent();
				bufferIn = new BufferedInputStream(in);
				byte[] b = new byte[1024];
				int len;
				while((len=bufferIn.read(b))!=-1){
					sb.append(new String(b,0,len));
				}
				//br = new BufferedReader(new InputStreamReader(in));
	
				/*if ((str = br.readLine()) != null) {
					sb.append(str);
				}*/
			}
		} catch (IOException e) {
			System.out.println(e);
		}finally{
			if(br!=null){
				try {
					br.close();
				} catch (IOException e) {
					System.out.println(e);
				}
			}
		}
		return sb.toString();
	}
}
