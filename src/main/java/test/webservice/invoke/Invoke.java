package test.webservice.invoke;

import java.net.MalformedURLException;
import java.net.URL;

import test.webservice.client.WebService;
import test.webservice.client.WebServiceService;

public class Invoke {

	public  void  invokeWS() { 
		WebService ws = new WebServiceService().getWebServicePort();
		System.out.println(ws.test());
		
	}
	
	public static void main(String[] args) throws MalformedURLException {
		
		new Invoke().invokeWS(); 
		

		
		
	}
}
