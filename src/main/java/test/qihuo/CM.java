package test.qihuo;

import com.sunit.global.util.WebCrawler;
import com.sunit.global.util.WebCrawler.responsedHandel;
import com.sunit.global.util.mq.Send;

/**
 * 玉米期货的价格
 * 
 * 类名称：CM
 * 类描述：
 * 创建人：Administrator
 * 创建时间：2018年1月2日 下午2:20:30
 * 修改人：joye
 * 修改时间：2018年1月2日 下午2:20:30
 * 修改备注：
 * @version 
 *
 */
public class CM {
	
	public static void getPrire() {
		

		WebCrawler wc = new WebCrawler();
		
//		wc.setUrlTpl("http://nufm.dfcfw.com/EM_Finance2014NumericApplication/JS.aspx?type=CT&cmd=CM3&sty=FDPBPFBTA&st=z&sr=&p=&ps=&cb=jQuery17209968121927779905_1514866553625&js=([[(x)]])&token=7bc05d0d4c3c22ef9fca8c2a912d779c&_=1514872912691");
	  wc.setUrlTpl("http://nufm.dfcfw.com/EM_Finance2014NumericApplication/JS.aspx");
//		wc.setUrlTpl("http://quote.eastmoney.com/qihuo/CM.html");  
	  wc.setQueryStringForGet("type=CT&cmd=C.F_DCE_C&sty=MPNSBAS&st=c&sr=-1&p=1&ps=5&token=7bc05d0d4c3c22ef9fca8c2a912d779c&_=1514876961176");
	  
	  wc.setRh(new responsedHandel() { 
		
		public boolean doAction(WebCrawler wc, String responseText) { 

			System.out.println(responseText);
			Send.sendMsg(responseText); 
			return true;
		} 
	});
	  
		wc.Futures();
		
		
		 
	}
	
	
	public static void main(String[] args) {
		
		getPrire();
		
	}

}
