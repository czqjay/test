package com.sunit.global.util;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;


import sun.misc.BASE64Encoder; 

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
/**
 * 模板应用类,基于 freemark
 * @author Administrator
 *
 */
public class TempltUtil {  
    
    public static final String WORD_TEMPLATE = "/exportWordTemplate.ftl";   
    public static final String TEMPLATE_PATH = "/WEB-INF/classes/com/sunit/xwt/archives/templates";   
    public static final String PREVIEW_DOC = TEMPLATE_PATH+"/myDownload.doc";   
  
        public static Template configTemplate(HttpServletRequest request, String temp) throws IOException {   
        Configuration config = new Configuration();   
        ServletContext sc = request.getSession().getServletContext();   
        config.setDirectoryForTemplateLoading(new File(sc.getRealPath(TEMPLATE_PATH)));   
        config.setObjectWrapper(new DefaultObjectWrapper());   
        Template template = config.getTemplate(temp, "UTF-8");   
        return template;   
        }   
  
        public static void toPreview(HttpServletRequest request, String temp, Map<?, ?> root){   
            try {   
            String previewPath = request.getSession().getServletContext().getRealPath("")+PREVIEW_DOC;   
            Template template = configTemplate(request, temp);   
            FileOutputStream fos = new FileOutputStream(previewPath);   
            Writer out = new OutputStreamWriter(fos, "UTF-8");   
                template.process(root, out);   
                out.flush();   
                out.close();   
            } catch (Exception e) {   
            e.printStackTrace();   
            }   
        }   
        
}