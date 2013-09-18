package com.modules.httpWriter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

public class ResponseWriter {

	public static void write(byte[] data) throws IOException {
		ServletOutputStream output = ServletActionContext.getResponse().getOutputStream();
		output.write(data);
		output.flush();
//		output.close();
	}
	
	
	public static void close() throws IOException {
		ServletOutputStream output = ServletActionContext.getResponse().getOutputStream();
		output.close();
	}
	
}
