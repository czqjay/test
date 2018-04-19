package test.fax;

import java.io.File;
import java.io.IOException;

import org.fax4j.FaxClient;
import org.fax4j.FaxClientFactory;
import org.fax4j.FaxJob;
import org.fax4j.FaxJob.FaxJobPriority;

public class Test {

	/**
	 * 
	* @Title: main 
	* @Description: 
	* @param @param args
	* @param @throws IOException     
	* @return void  
	* @throws 
	* @author joye 
	* 2018年2月24日 上午10:35:55
	 */
	public static void main(String[] args) throws IOException {
		
		
		//get new instance of a fax client (based on internal + external fax4j.properties file data)
		FaxClient faxClient=FaxClientFactory.createFaxClient();
		 
		//create a new fax job
		FaxJob faxJob=faxClient.createFaxJob();

		//set fax job values
		faxJob.setFile(new File("./my_document.txt")); 
		faxJob.setPriority(FaxJobPriority.HIGH_PRIORITY); 
		faxJob.setTargetAddress("076087967950"); 
		faxJob.setTargetName("YourName");
		faxJob.setSenderEmail("33262603@qq.com");
		faxJob.setSenderName("MyName");

		//submit fax job
		faxClient.submitFaxJob(faxJob);
 
		//print submitted fax job ID (may not be supported by all SPIs)
		System.out.println("Fax job submitted, ID: "+faxJob.getID());
		
	}
	
	
	
	
}
