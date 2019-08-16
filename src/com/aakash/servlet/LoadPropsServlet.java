package com.aakash.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aakash.servlet.Props;

public class LoadPropsServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		Properties props = new Properties();
		String propsFileLocation = "/home/aakashgautam/Desktop/Java Files/Config.properties";
		try {
			FileInputStream fis = new FileInputStream( propsFileLocation );
			props.load(fis); 
			Props p = new Props();
		    p.setProps(props);
		    fis.close();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		super.init(config);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doGet(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
	}
}