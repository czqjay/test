package test.jco;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoParameterList;
import com.sap.conn.jco.JCoTable;
import com.sap.conn.jco.ext.Environment;

public final class RfcManager {
    private static Logger logger = Logger.getLogger(RfcManager.class);

    private static String ABAP_AS_POOLED = "XXX";

    private static JCOProvider provider = null;

    private static JCoDestination destination = null;

    static { 
        Properties properties = loadProperties();

        provider = new JCOProvider();

        // catch IllegalStateException if an instance is already registered
        try {
            Environment.registerDestinationDataProvider(provider);
        } catch (IllegalStateException e) {
            logger.debug(e);
        }

        provider.changePropertiesForABAP_AS(ABAP_AS_POOLED, properties);
    }

    public static Properties loadProperties() {
        RfcManager manager = new RfcManager();
        Properties prop = new Properties();
        try {
            prop.load(manager.getClass().getResourceAsStream(
                    "sap_conf.properties"));
        } catch (IOException e) {
            logger.debug(e);
        }
        return prop;
    }

    public static JCoDestination getDestination() throws JCoException {
        if (destination == null) {
            destination = JCoDestinationManager.getDestination(ABAP_AS_POOLED);
        }
        return destination;
    }

    public static JCoFunction getFunction(String functionName) {
        JCoFunction function = null;
        try {
            function = getDestination().getRepository()
                    .getFunctionTemplate(functionName).getFunction();
        } catch (JCoException e) {
            logger.error(e);
        } catch (NullPointerException e) {
            logger.error(e);
        }
        return function;
    }

    public static void execute(JCoFunction function) {
        logger.debug("SAP Function Name : " + function.getName());
        JCoParameterList paramList = function.getImportParameterList();

        if (paramList != null) {
            logger.debug("Function Import Structure : " + paramList.toString());
        }
 
        try {
            function.execute(getDestination());
        } catch (JCoException e) {
            logger.error(e);
        }
        paramList = function.getExportParameterList();

        if (paramList != null) {
            logger.debug("Function Export Structure : " + paramList.toString());
        }
    }

    /*
     * SAP 연결 Ping 테스트
     */
    public static String ping() { 
        String msg = null;
        try {
            getDestination().ping();
            msg = "Destination " + ABAP_AS_POOLED + " is ok";
        } catch (JCoException ex) {
            msg=ex.getMessage();
        }
        logger.debug(msg);
        return msg;
    } 

 
    
    public void callRfcExample() {
    	System.out.println("RfcManager.callRfcExample()");
        // 获取RFC 对象
        JCoFunction function = RfcManager.getFunction("Z_MM_PO_CLOSE");
        // 设置import 参数
        JCoParameterList importParam = function.getImportParameterList();
//        PO_NO
//        MATERIAL_NO
//        ITEM_NO
//        SEND_SIGN
        
//        4500022392
//        737
//        00020
//        关闭
        
        importParam.setValue("PO_NO", "4500022392");
        importParam.setValue("MATERIAL_NO", "737");
        importParam.setValue("ITEM_NO", "00030"); 
        importParam.setValue("SEND_SIGN", "关闭");
        
        
        // 执行RFC
        RfcManager.execute(function);

        // 获取RFC返回的字段值
        JCoParameterList exportParam = function.getExportParameterList();
        String exParamA = exportParam.getString("CANCEL_MESSAGE");
        System.out.println(exParamA);
//        String exParamB = exportParam.getString("field_B");
        // 遍历RFC返回的表对象
//        JCoTable tb = function.getTableParameterList().getTable("table_name");
//        for (int i = 0; i < tb.getNumRows(); i++) {
//            tb.setRow(i);
//            System.out.println(tb.getString("field01"));
//            System.out.println(tb.getString("field02"));
//        } 
    } 
    public static void main(String[] args) {
//    	RfcManager.ping();  
    	RfcManager rfcManager = new RfcManager();
    	rfcManager.callRfcExample();  
    	
	}
    
    
    
}