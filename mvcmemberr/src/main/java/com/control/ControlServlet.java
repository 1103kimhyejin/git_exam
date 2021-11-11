package com.control;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map<String, Object> map = new HashMap<String, Object>();
	   
	   public void init(ServletConfig config) {
	      String propertyConfig = config.getInitParameter("propertyConfig");
	      System.out.println("propertyConfig = "+propertyConfig+"\n");
	      
	      FileInputStream fin = null;
	      Properties properties = new Properties();
	      
	      try {
	         fin = new FileInputStream(propertyConfig);
	         properties.load(fin);
	         System.out.println("properties = "+properties);
	         
	      } catch (IOException e) {
	         e.printStackTrace();
	      }finally{
	         try {
	            fin.close();
	         } catch (IOException e) {
	            e.printStackTrace();
	         }
	      }
	      System.out.println();
	      
	      Iterator it = properties.keySet().iterator();
	      while(it.hasNext()) {
	         String key = (String)it.next();
	         System.out.println("key = "+key);
	         
	         String className = properties.getProperty(key);
	         System.out.println("className = "+className);
	         
	         try {
	            Class<?> classType = Class.forName(className);
	            Object ob = classType.newInstance();
	            
	            System.out.println("ob = "+ob);
	            
	            map.put(key, ob);
	            
	         } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	         } catch (InstantiationException e) {
	            e.printStackTrace();
	         } catch (IllegalAccessException e) {
	            e.printStackTrace();
	         } catch (IllegalArgumentException e) {
	            e.printStackTrace();
	         } 
	         
	         System.out.println();
	      }//while
	      
	   }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		execute(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//get이랑 한글처리만 다름
		execute(request, response);
	}
	
	protected void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println();
		
		//post일때한글처리, get은 안해도 되니까 안하고
		if(request.getMethod().equals("POST")) {
			request.setCharacterEncoding("UTF-8");
		}
		
		String category = request.getServletPath(); //주소에 http안써도 되게 하는 것!!
	      System.out.println("category = "+category);
	      
	      CommandProcess commandProcess = (CommandProcess)map.get(category);
	      System.out.println("commandProcess = "+commandProcess);
	      
	      String view = null;
	      try {
	         view = commandProcess.requestPro(request, response);
	      } catch (Throwable e) {
	         e.printStackTrace();
	      }
	      
	      //forward   
	      RequestDispatcher dispatcher = request.getRequestDispatcher(view);//상대번지로 써야함 꼭
	      dispatcher.forward(request, response);//제어권 넘기기
		
	}
	

}
