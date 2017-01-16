package demo.test.kybaby.sp;

import demo.util.HttpClientUtil;

public class DoPost {
	public static void main(String[] args) {
		String url = "http://171.221.218.21:5418/ToolInterface.asmx";
		String content = 
		"<?xml version=\"1.0\" encoding=\"utf-8\"?>"
		+"<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">"
			+"<soap12:Body>"
			+"<InteractionOperating xmlns=\"http://tempuri.org/\">"
			 +"   <request><Body><Request><BirthDay>2016-12-21</BirthDay><PaperNum></PaperNum><SelfPhone>18160031787</SelfPhone><ResidentName>康有</ResidentName><QueryString></QueryString><CardType></CardType><OrgCode>3d715fb3-5fd4-4f36-9be9-7cca29de01ca</OrgCode><OperType>003</OperType><ResidentID></ResidentID><RHCD>2</RHCD><BloodCD>5</BloodCD><SexCD>1</SexCD><Age></Age><CardNo></CardNo></Request></Body></request>"
		    +"</InteractionOperating>"
		  +"</soap12:Body>"
		+"</soap12:Envelope>";
		
		System.out.println(HttpClientUtil.doPostSOAP(url, content));
	}
}
