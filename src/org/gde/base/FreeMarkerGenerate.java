package org.gde.base;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * FreemarkerWordDemo is used to Generate entity file by template.
 * @author JavaLuSir
 *
 */
public class FreeMarkerGenerate {
	//Configure of freemarker Configuration
	private Configuration conf = null;
	/**
	 * construct
	 */
	public FreeMarkerGenerate() {
		conf=new Configuration();
		conf.setDateFormat("utf-8");
	}
	
	/**
	 * write a File to FileSystem
	 * @throws TemplateException
	 * @throws IOException
	 */
	public void WriteDocFile() throws TemplateException, IOException{
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		 //load model file
		 conf.setClassForTemplateLoading(FreeMarkerGenerate.class, "/");
		 Template template = conf.getTemplate("model");
		 //set output file path must modifiedable
		 OutputStream os = new FileOutputStream("D:/Entity.java");
		 OutputStreamWriter ow = new OutputStreamWriter(os);
		 
		 List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		 Map<String,String> listmap = new LinkedHashMap<String,String>();
		 //here we should read from database  following 
		 //the map key is database field the value is decide to field type dynamic generate 
		 listmap.put("id", "String");
		 listmap.put("name", "String");
		 listmap.put("sex", "String");
		 
		 list.add(listmap);
		 //package is modifiedable depends FileOutput path
		 map.put("package", "org.entity.test");
		 map.put("paramNames", list);
		 //entity name is table name
		 map.put("entityName","Entity");
		 
		 template.process(map, ow);//generate java file
		 ow.close();
		 os.close();
	}
	
	//main invoke test
	public static void main(String[] args) throws TemplateException, IOException {
		FreeMarkerGenerate fwd = new FreeMarkerGenerate();
		fwd.WriteDocFile();
	}

}
