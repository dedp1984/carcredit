//package com.pujjr;
//
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.io.OutputStreamWriter;
//import java.io.Writer;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.w3c.dom.Document;
//import org.xhtmlrenderer.pdf.ITextFontResolver;
//import org.xhtmlrenderer.pdf.ITextOutputDevice;
//import org.xhtmlrenderer.pdf.ITextRenderer;
//import org.xhtmlrenderer.pdf.ITextUserAgent;
//import org.xhtmlrenderer.resource.XMLResource;
//import org.xml.sax.InputSource;
//
//import com.alibaba.fastjson.JSON;
//import com.lowagie.text.DocumentException;
//import com.lowagie.text.pdf.BaseFont;
//import com.pujjr.business.service.SysMenuService;
//import com.pujjr.business.vo.MenuTree;
//
//import freemarker.core.ParseException;
//import freemarker.template.Configuration;
//import freemarker.template.MalformedTemplateNameException;
//import freemarker.template.Template;
//import freemarker.template.TemplateException;
//import freemarker.template.TemplateNotFoundException;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration({"classpath:conf/spring*.xml"})
//public class Html2PdfTest 
//{
//	
//	
//	@Autowired
//	private SysMenuService sysMenuService;
//	@Test
//	public void test()
//	{
//		System.out.println("test");
//	}
//	
//	@Test
//	public void getUserMenuTree()
//	{
//		List<MenuTree> list=sysMenuService.generateSysMenuTreeByAccountId("dengpan", "-1");
//		
//		String jsonStr=JSON.toJSONString(list, true);
//		System.out.println(jsonStr);
//	}
//	@Test
//	public void generateHtmlFromTemplate() throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException
//	{
//		Configuration cfg=new Configuration();
//		cfg.setDirectoryForTemplateLoading(new File("d:\\"));
//		Template template=cfg.getTemplate("test.ftl");
//			
//		Map map=new HashMap();
//		
//		map.put("username", "dengpan");
//		map.put("password", "1111");
//		
//		//Writer out = new OutputStreamWriter(System.out);
//		
//		File afile = new File("d:\\2.html");  
//        Writer out = new BufferedWriter(new OutputStreamWriter(  
//                new FileOutputStream(afile), "utf-8"));  
//		template.process(map, out);
//		
//		out.flush();
//		
//	}
//	
//	
//	@Test
//	public void createPdf() throws DocumentException, IOException
//	{
//		String pdf="d:\\2.pdf";
//		String url="http://127.0.0.1:8080/carcredit/template/1.html";
//		OutputStream os = null;
//		try {
//            os = new FileOutputStream(pdf);
//
//            /* standard approach
//            ITextRenderer renderer = new ITextRenderer();
//
//            renderer.setDocument(url);
//            renderer.layout();
//            renderer.createPDF(os);
//            */
//
//            ITextRenderer renderer = new ITextRenderer();
//            ResourceLoaderUserAgent callback = new ResourceLoaderUserAgent(renderer.getOutputDevice());
//            callback.setSharedContext(renderer.getSharedContext());
//            renderer.getSharedContext ().setUserAgentCallback(callback);
//
//            ITextFontResolver fontResolver = renderer.getFontResolver();  
//            fontResolver.addFont("C:/Windows/Fonts/msyh.ttc", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
//            
//            Document doc = XMLResource.load(new InputSource(url)).getDocument();
//            //renderer.setDocument(url);
//            renderer.setDocument(doc, url);
//            
//            renderer.layout();
//            renderer.createPDF(os);
//
//            os.close();
//            os = null;
//        } finally {
//            if (os != null) {
//                try {
//                    os.close();
//                } catch (IOException e) {
//                    // ignore
//                }
//            }
//        }
//	}
//	
//	private static class ResourceLoaderUserAgent extends ITextUserAgent
//    {
//        public ResourceLoaderUserAgent(ITextOutputDevice outputDevice) {
//            super(outputDevice);
//        }
//
//        protected InputStream resolveAndOpenStream(String uri) {
//            InputStream is = super.resolveAndOpenStream(uri);
//            System.out.println("IN resolveAndOpenStream() " + uri);
//            return is;
//        }
//    }
//	
//}
