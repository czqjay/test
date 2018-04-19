package com.sunit.global.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;

import com.sunit.global.util.alipay.util.httpClient.HttpProtocolHandler;
import com.sunit.global.util.alipay.util.httpClient.HttpRequest;
import com.sunit.global.util.alipay.util.httpClient.HttpResponse;
import com.sunit.global.util.alipay.util.httpClient.HttpResultType;



public class HttpUtil {
	
	private HttpRequest request = new HttpRequest(HttpResultType.BYTES); 
	private List<NameValuePair> parameters=new ArrayList(); 
	 
	private void httu() { 
		request.setCharset("UTF-8");  
	} 

	public HttpRequest getRequest() { 
		return request;
	}

 
	public void setRequest(HttpRequest request) {
		this.request = request;
	}

	
	public  void putParameter(String key, String value){
		parameters.add(new NameValuePair(key,value));
	}  

	public  String  doAction(String url,String charset ) throws HttpException, IOException{ 
		if(SunitStringUtil.isBlankOrNull(charset))
			charset="utf-8";
		
		request.setUrl(url);
		request.setParameters(parameters.toArray(new NameValuePair[parameters.size()]));
  		HttpProtocolHandler httpHandle = HttpProtocolHandler.getInstance();
  		
		HttpResponse response= httpHandle.execute(request,"", "");
		response.getStateCode();
		String  str = new String (response.getByteResult(),charset); 
		return str;
	} 

	public  HttpResponse  doAction(String url ) throws HttpException, IOException{ 
		request.setUrl(url);  
		request.setParameters(parameters.toArray(new NameValuePair[parameters.size()]));
  		HttpProtocolHandler httpHandle = HttpProtocolHandler.getInstance();
		HttpResponse response= httpHandle.execute(request,"", "");
		return response;
	}
	
}
