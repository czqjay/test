package com.sunit.global.util;

import java.io.IOException;

import org.apache.commons.httpclient.HttpException;

import com.sunit.global.util.alipay.util.httpClient.HttpResponse;

/**
 * 爬虫类
 * @author Administrator
 *
 */
public class WebCrawler {

	
	int pageNum;
	String urlTpl;
	HttpResponse httpResponse ;
	 
	String queryStringForGet;
	
	exceptionHandel eh;
	
	responsedHandel rh;
	
	/** 
	 * 异常处理接口, 返回true 则中断行为
	 * 
	 * @author Administrator
	 * 
	 */
	public interface exceptionHandel {
		public boolean doException(WebCrawler wc);
	}

	/**
	 * 数据返回后的处理行为
	 * 
	 * 类名称：responsedHandel
	 * 类描述：
	 * 创建人：Administrator
	 * 创建时间：2018年1月2日 下午4:44:18
	 * 修改人：joye
	 * 修改时间：2018年1月2日 下午4:44:18
	 * 修改备注：
	 * @version 
	 *
	 */
	public interface responsedHandel {
		public boolean doAction(WebCrawler wc,String responseText);
	}

	
	/**
	 *  期货
	* @Title: Futures 
	* @Description: 
	* @param      
	* @return void  
	* @throws 
	* @author joye 
	* 2018年1月2日 下午2:25:48
	 */
			
	public  void Futures () {
		


		// String url ="http://www.53zw.com/yuedu/25/25127/7997164.html";
		HttpUtil ht = new HttpUtil();
		String url=this.getUrlTpl();
		
		try {
				System.out.println(url);
				ht.getRequest().setMethod(ht.getRequest().METHOD_GET);
				ht.getRequest().setQueryString(this.getQueryStringForGet());
				HttpResponse re = ht.doAction(url);
				this.setHttpResponse(re);
				if (re.getStateCode().equals("200")) {
					String str = new String(re.getByteResult(), "utf-8"); 
					if (this.getRh()!=null && this.getRh().doAction(this,str)) { 
						
					}
				} else { // httpcode 
					System.out.println(url + "    httpcode:" + re.getStateCode());
					if (this.getEh().doException(this)) { 
						return;
					}

				}

		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); 
		} 
//		catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 

	
		
		
		
		
	}
	
	
	
	/**
	 *  
	 * crawler 网络小说
	 * 
	 * @param pageNum
	 *            页码 一般来说 , 网络小说都是用 页码累加来 编码url
	 * @param url
	 *            小说地址 eg: http://www.53zw.com/yuedu/25/25127/{pageNum}.html
	 *            #{pageNum}为累加变量
	 * @throws InterruptedException 
	 */
	public  void novel() {

		// String url ="http://www.53zw.com/yuedu/25/25127/7997164.html";
		HttpUtil ht = new HttpUtil();
		String url="";
		try {

			while (true) {   
				Thread.currentThread().sleep(10); 
				
				url = this.getUrlTpl().replace("{pageNum}", String.valueOf(pageNum));
				System.out.println(url);
				ht.getRequest().setMethod(ht.getRequest().METHOD_GET);
				HttpResponse re = ht.doAction(url);
				this.setHttpResponse(re);
				if (re.getStateCode().equals("200")) {
					String str = new String(re.getByteResult(), "gbk");
					// System.out.println(str);
					int start = str.indexOf("<div id=\"BookText\">");
					int end = str.indexOf("</div>", start);
					//System.out.println(str.substring(start, end));
					String text = str.substring(start, end).replaceAll(
							"&nbsp;", "");
					text = text.replaceAll("<br>", "");
					text = text.replaceAll("<br />", "");
					text = text
							.replaceAll(
									"<div id=\"BookText\">恋上你看630bookla，最快更新明骑最新章节",
									"");
					//System.out.println(text);
					pageNum++;
					
				} else { // httpcode 异常 可能超时,也可能所有章节都读完了
					System.out.println(url + " == " + re.getStateCode());
					if (this.getEh().doException(this)) { 
						return;
					}

				}
				
			}

		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); 
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args)  {
		WebCrawler wc = new WebCrawler();
		wc.setPageNum(7997172); 
		wc.setUrlTpl("http://www.53zw.com/yuedu/25/25127/{pageNum}.html");
		exceptionHandel eh =   new exceptionHandel(){
			public boolean doException(WebCrawler wc) {
				if(wc.getHttpResponse().getStateCode().equals("522")){
					wc.setPageNum(wc.getPageNum());
					wc.novel();
					return false;
				};
				return true;
			}};
		wc.setEh(eh);
		
		wc.novel();
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public String getUrlTpl() {
		return urlTpl;
	}

	public void setUrlTpl(String urlTpl) {
		this.urlTpl = urlTpl;
	}

	public HttpResponse getHttpResponse() {
		return httpResponse;
	}

	public void setHttpResponse(HttpResponse httpResponse) {
		this.httpResponse = httpResponse;
	}

	public exceptionHandel getEh() {
		return eh;
	}

	public void setEh(exceptionHandel eh) {
		this.eh = eh;
	}



	public String getQueryStringForGet() {
		return queryStringForGet;
	}



	public void setQueryStringForGet(String queryStringForGet) {
		this.queryStringForGet = queryStringForGet;
	}



	public responsedHandel getRh() {
		return rh;
	}



	public void setRh(responsedHandel rh) {
		this.rh = rh;
	}
}
