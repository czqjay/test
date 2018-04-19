package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

import org.apache.log4j.helpers.FileWatchdog;

public class IOtest {

	/**
	 * 递归获取文件下所有的文件信息(单设备)
	 * 
	 * @param lstFileNames
	 *            文件名集合
	 * @param path
	 *            文件路径
	 * @return 文件实体集合
	 * @throws IOException 
	 */
	public static void getListFile(String path) throws IOException {
		// 若是目录, 采用递归的方法遍历子目录
		File file = new File(path);
		if (file.isDirectory()) {// 如果是文件夹，则取出其中的文件
			File[] files = file.listFiles();
			for (File file2 : files) { 
				String pathDemo = file2.getPath();
				getListFile(pathDemo);
			}
		} else {// 如果是文件则读取文件
			String filePath = file.getParent() + "/";
			String fileName = file.getName(); 
			
			if (fileName.indexOf(".java") > -1) {
//				System.out.println(fileName);
//				byte[] b  = new byte[1024*1024];
//				File f = new File(filePath+fileName);
//				FileInputStream fi =new FileInputStream(f);
//				int count =0;
//				int  num =0;
////				int count = fi.read(b);
//				while ((num = fi.read(b))!=-1) {
//					System.out.println(num);
//					count = count+num;
//					
//				}
//				 
//				System.out.println(count);
//				fi.close();
//				String str = new String(b, "utf-8");
//				if (str.indexOf("creatorOrg as creatorOrg,") > -1) {
//					str = str.replaceFirst("creatorOrg as creatorOrg,",  "");
//					FileOutputStream fo =new FileOutputStream(f);
//					byte [] bs = str.getBytes("utf-8");
//					System.out.println(bs.length); 
//					fo.write(bs);
//					fo.flush();
//					fo.close();
//					System.out.println("当前文件的文件路径为：" + filePath + ",文件名为：" + fileName);
//				}
//			
				
				
				
				
				
//				 RandomAccessFile raf=new RandomAccessFile(filePath + fileName, "rw");  
//				 String line ="";
//				  long lastPoint = 0;
//				 while ( (line = raf.readLine()) !=null) {
//					 
//					   final long ponit = raf.getFilePointer();
//		               
//		          
//					 if (line.indexOf("creatorOrg as creatorOrg,") > -1) {
//						 line =  line.replaceFirst("creatorOrg as creatorOrg,",  "");
//						 raf.seek(lastPoint);
//						 raf.writeBytes(line);
//					 }
//					 
//				      lastPoint = ponit;  //读取一行，指针指到下一行开头。用作写下一行，偏移量的开始，。
//					 
//				 }
				 
				
				 RandomAccessFile raf=new RandomAccessFile(filePath + fileName, "rw");  
//				
//				FileReader fr = new FileReader(filePath + fileName); 
//				BufferedReader br = new BufferedReader(fr);
				StringBuffer sb =new StringBuffer();
				boolean isTarget = false;
				String str;
				while ((str = raf.readLine())!=null ) {
					
//					
					str =new String(str.getBytes("ISO-8859-1"),"utf-8");
					byte [] b = str.getBytes("utf-8");
//					if(b.length>5 && (b[0]==30 ||  b[0]==31 )&& b[1]==-17&&b[2]==-65&&b[3]==-67) {
//						if(b.length>5 &&b[2]==-65&&b[3]==-67) {
					
					while(sb.length()==0 && b.length>5 &&b[0]!=112) {
						isTarget=true;
						System.out.println(",文件名为：" + fileName);
						b = Arrays.copyOfRange(b,  1,b.length);	
						str = new String(b);
					}
					if (str.indexOf("creatorOrg as creatorOrg,") > -1) {
						isTarget=true;
						sb.append(str.replaceFirst("creatorOrg as creatorOrg,",  "")+"\n");
					}else {
						sb.append(str+"\n");
					}
				}
				if(isTarget) { 
					raf.getChannel().truncate(0);
//					raf.writeUTF(sb.toString());
					raf.write(sb.toString().getBytes("utf-8"));
					System.out.println("当前文件的文件路径为：" + filePath + ",文件名为：" + fileName);
					
				}
				raf.close();
				
			
	}
	}
	}

	public static void main(String[] args) throws IOException {

//		String s ="		public void setSvendorName(java.lang.String svendorName) {\r\n" + 
//				"			this.svendorName = svendorName;\r\n" + 
//				"		}\r\n" + 
//				"\r\n" + 
//				"		public java.lang.String getSvendorName() {\r\n" + 
//				"			return svendorName;\r\n" + 
//				"		}\r\n" + 
//				"\r\n" + 
//				"	}\r\n" + 
//				"\r\n" + 
//				"}\r\n" + 
//				"" ;
//		System.out.println(s);
//		System.out.println(new String(s.getBytes("utf-8"), "utf-8"));;
		
		String filestr = "H:\\longerInfoWorkspace\\tms";
		getListFile(filestr);

	}

}
