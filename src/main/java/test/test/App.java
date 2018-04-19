package test.test;

import java.io.IOException;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
    	
    	

    		String itemNo="1";
    
    		
    		while ( 5-itemNo.length()>0) {
    			itemNo="0"+itemNo;
    		} 
    		 
    		System.out.println(itemNo);
    	
    	
        System.out.println( "Hello World!" );
        
        
        String jarName = "D:\\桌面\\sunitDesktop\\scm.jar";   
        JarFile jarFile = new JarFile(jarName);  
        Enumeration<JarEntry> entrys = jarFile.entries();  
        while (entrys.hasMoreElements()) {  
            JarEntry jarEntry = entrys.nextElement();  
            System.out.println(jarEntry.getName());   
        
        
    }
}
}
