package joye.test;

import java.io.IOException;

import org.apache.commons.httpclient.HttpException;

import com.sunit.global.util.HttpUtil;
import com.sunit.global.util.alipay.util.httpClient.HttpResponse;

public class WebCrawler {

	
	int pageNum;
	String urlTpl;
	HttpResponse httpResponse ;
	
	exceptionHandel eh;
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
			@Override
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
}
