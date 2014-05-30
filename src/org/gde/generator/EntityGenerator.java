package org.gde.generator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.gde.interfaces.IGenerate;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * EntityGenerator is used to Generate entity file by template.
 * @author JavaLuSir
 *
 */
public class EntityGenerator implements IGenerate{
	//Configure of freemarker Configuration
	private Configuration conf = null;
	/**
	 * constructor
	 */
	public EntityGenerator() {
		conf=new Configuration();
		conf.setDateFormat("utf-8");
	}
	
	/**
	 * write a File to FileSystem
	 * @throws TemplateException
	 * @throws IOException  TemplateException, IOException
	 */
	public void generate(String path) {
		OutputStream os = null;
		OutputStreamWriter ow = null;
		try{
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		 //load model file
		 conf.setClassForTemplateLoading(EntityGenerator.class, "/");
		 Template template = conf.getTemplate("model");
		 //set output file path must modifiedable
		 os = new FileOutputStream(path);
		 ow = new OutputStreamWriter(os);
		 
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
		}catch(TemplateException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			try {
				ow.close();
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	//main invoke test
	public static void main(String[] args) throws TemplateException, IOException {
		EntityGenerator fwd = new EntityGenerator();
		fwd.generate("D:/entity.java");
	}

}
