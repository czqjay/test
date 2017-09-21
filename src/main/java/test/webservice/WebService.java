package test.webservice;

import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.xml.ws.Endpoint;

@javax.jws.WebService
@HandlerChain(file="handler-chain.xml")
public class WebService {    

	
	@WebMethod 
	public String  test() {
		System.out.println("WebService.test()");
		 System.out.println(System.currentTimeMillis()+"B");
		return "WebService.test";
	}  
	 
	public static void main(String[] args) {
//		http://localhost:8080/
		Endpoint.publish("http://localhost:8081/ws/test", new WebService());
		System.out.println("sucess"); 
		
	}

}
