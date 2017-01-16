package demo.util;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.ArrayUtils;
import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HttpClientUtil {
	//===========================log
	private static Logger log = LoggerFactory.getLogger(HttpClientUtil.class);
	//===========================log
    //=======================GET
    public static String doPut(String url,Map<String,String> params){
    	if(params.isEmpty()) return "请求参数不能为空";
    	List<NameValuePair> paramList = parseMap2NameValuePair(params);
    	UrlEncodedFormEntity req_Entity = new UrlEncodedFormEntity(paramList,Consts.UTF_8);
    	return doPut(url, req_Entity);
    }
    private static String doPut(String url,HttpEntity req_Entity){
    	CloseableHttpClient httpClient = getHttpClient();
    	HttpUriRequest request = RequestBuilder.get()
    			.setUri(url)
    			.setConfig(getConfig())
    			.setEntity(req_Entity)
    			.build();
    	return ClientExecute(httpClient, request);
    }
    //=======================GET
    //==========================POST
    public static String doPost(String url,Map<String,String> params){
    	if(params.isEmpty()) return "请求参数不能为空";
    	List<NameValuePair> paramList = parseMap2NameValuePair(params);
    	UrlEncodedFormEntity req_Entity = new UrlEncodedFormEntity(paramList,Consts.UTF_8);
    	return doPost(url, req_Entity);
    }
    public static String doPostXML(String url,String content){
    	StringEntity req_Entity = new StringEntity(content,Consts.UTF_8);
    	req_Entity.setContentType(ContentType.TEXT_XML.toString());
    	return doPost(url, req_Entity);
    }
    public static String doPostSOAP(String url,String content){
    	StringEntity req_Entity = new StringEntity(content,Consts.UTF_8);
    	CloseableHttpClient httpClient = getHttpClient();
    	HttpUriRequest request = RequestBuilder.post()
    			.setUri(url)
    			.setConfig(getConfig())
    			.setEntity(req_Entity).build();
    	List<org.apache.http.Header>  headers = new ArrayList<org.apache.http.Header>();
    	headers.add(new BasicHeader("Content-Type", "application/soap+xml; charset=utf-8"));
    	//headers.add(new BasicHeader("SOAPAction", "http://tempuri.org/InteractionOperating"));
    	org.apache.http.Header[] header = new Header[1];
    	request.setHeaders(headers.toArray(header));
    	return ClientExecute(httpClient, request);
    }
    public static String doPostText(String url,String content){
    	StringEntity req_Entity = new StringEntity(content,Consts.UTF_8);
    	return doPost(url, req_Entity);
    }
    public static String doPostJson(String url,String jsonContent){
    	StringEntity req_Entity = new StringEntity(jsonContent,Consts.UTF_8);
    	req_Entity.setContentType(ContentType.APPLICATION_JSON.toString());
    	return doPost(url, req_Entity);
    }
    private static String doPost(String url,HttpEntity req_Entity){
    	CloseableHttpClient httpClient = getHttpClient();
    	HttpUriRequest request = RequestBuilder.post()
    			.setUri(url)
    			.setConfig(getConfig())
    			.setEntity(req_Entity).build();
    	return ClientExecute(httpClient, request);
    }
  //==========================POST
    //======================Delete
    public static String doDelete(String url,HttpEntity req_Entity){
    	CloseableHttpClient httpClient = getHttpClient();
    	HttpUriRequest request = RequestBuilder.delete()
    			.setUri(url)
    			.setConfig(getConfig())
    			.setEntity(req_Entity)
    			.build();
    	return ClientExecute(httpClient, request);
    }
    //======================Delete
    //===================HttpClient
    private static CloseableHttpClient getHttpClient(){
    	HttpClientConnectionManager connManager = getHttpClientConnectionManager();
		RequestConfig globalConfig = getConfig();
		CookieStore cookieStore = getCookieStore();
		CloseableHttpClient httpClient = HttpClients.custom()
				.setDefaultCookieStore(cookieStore)
				.setDefaultRequestConfig(globalConfig)// 设置全局请求配置
				//.setConnectionManager(connManager)// 设置连接管理器
				.build();
		return httpClient;
	}
    //===================HttpClient
    //================================CookieStore
    private static CookieStore cookieStore;
    public static void setCookieStore(CookieStore inCookieStore){
    	cookieStore = inCookieStore;
    }
    private static CookieStore getCookieStore(){
    	if(cookieStore==null){
    		cookieStore = new BasicCookieStore();
    	}
    	return cookieStore;
    } 
   //================================CookieStore
    //=========================HttpClientConnectionManager
    private static HttpClientConnectionManager getHttpClientConnectionManager(){
    	PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager();
		connManager.setMaxTotal(100);
		connManager.setDefaultMaxPerRoute(20);
		return connManager;
    }
    //=========================HttpClientConnectionManager
    //========================Config
	private static RequestConfig getConfig(){
		int timeOut = 120000;//2分钟
		RequestConfig config = RequestConfig.custom()
				.setConnectionRequestTimeout(timeOut) // 设置从connectManager获取Connection,超时时间，单位毫秒
				.setConnectTimeout(timeOut) // 设置连接超时时间，单位毫秒
				.setSocketTimeout(timeOut) // 请求获取数据的超时时间，单位毫秒
				.build();
		return config;
	}
	//========================Config
	//=========================execute
    private static String ClientExecute(CloseableHttpClient httpClient,HttpUriRequest request){
    	String result = "";
    	try {
    		StringResponseHandler responseHandler = new StringResponseHandler();
    		log.info("Executing request " + request.getRequestLine());
    		result = httpClient.execute(request,responseHandler);
		} catch (Exception e) {
			log.error("", e);
		}finally{
			//释放资源
			releaseConnection(httpClient, request);
		}
    	return result;
    }
  //=========================execute
    private static List<NameValuePair> parseMap2NameValuePair(Map<String,String> params){
    	List<NameValuePair> paramList = new ArrayList<NameValuePair>();
    	for(Entry<String, String> entry : params.entrySet()){
    		paramList.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
    	}
    	return paramList;
    }
    public static void releaseConnection(CloseableHttpClient httpClient,HttpUriRequest request){
		if(httpClient!=null){
			try {
				httpClient.close();
			} catch (IOException e) {
				log.error("", e);
			}
		}
	}
}
/**
 * 字符串响应处理类
 */
class StringResponseHandler implements ResponseHandler<String>{
	@Override
	public String handleResponse(HttpResponse response)
			throws ClientProtocolException, IOException {
		System.out.println("----------------------------------------");
        System.out.println(response.getStatusLine());
		int status = response.getStatusLine().getStatusCode();
		if (status >= 200 && status < 300) {
			StringBuilder sb = new StringBuilder();
			BufferedInputStream bufferIn = null ;
			HttpEntity res_Entity = response.getEntity();
			if(res_Entity!=null){
				res_Entity = new BufferedHttpEntity(response.getEntity());
				InputStream in = res_Entity.getContent();
				bufferIn = new BufferedInputStream(in);
				byte[] b = new byte[1024];
				int len;
				while((len=bufferIn.read(b))!=-1){
					sb.append(new String(b,0,len,Consts.UTF_8));
				}
				releaseResource(in, res_Entity);
			}
			return sb.toString();
		} else {
            throw new ClientProtocolException("Unexpected response status: " + status);
        }
	}
	//释放资源
	private void releaseResource(InputStream in,HttpEntity res_Entity) throws IOException{
		if(in!=null){
			in.close();
		}
		if(res_Entity!=null){
			EntityUtils.consume(res_Entity);
		}
	}
}
