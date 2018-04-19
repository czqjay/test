package test.webservice.invoke;

import java.net.MalformedURLException;
import java.net.URL;

import javax.jws.HandlerChain;

import test.webservice.client.WebService;
import test.webservice.client.WebServiceService;

public class Invoke {

	/**
	 * 调用 ws , soapHeader 的验证在 client.webServeiceService的  annotation 中  eg: @HandlerChain(file="client-handler-chain.xml")
	* @Title: invokeWS 
	* @Description: 
	* @param      
	* @return void  
	* @throws 
	* @author joye 
	* 2017年11月18日 下午3:25:11
	 */
	public  void  invokeWS() {  
		System.out.println("Invoke.invokeWS()");
		WebService ws = new WebServiceService().getWebServicePort();
		System.out.println(ws.test("joye"));
		
	}
	
	public static void main(String[] args) throws MalformedURLException {
		new Invoke().invokeWS(); 
	}
}
