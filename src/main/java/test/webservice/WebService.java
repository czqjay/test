package test.webservice;

import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.xml.ws.Endpoint;

@javax.jws.WebService
@HandlerChain(file="handler-chain.xml")
public class WebService {    

	
	@WebMethod 
	public String  test(String s) {
		System.out.println("WebService.test()");
		System.out.println(s);
		 System.out.println(System.currentTimeMillis()+"B");
		return "WebService.test_"+s;
	}  
	   
	public static void main(String[] args) { 
//		http://localhost:8080/ 
		Endpoint.publish("http://192.168.1.101:8081/ws/test", new WebService());  
		System.out.println("sucess"); 
		
	}

}
