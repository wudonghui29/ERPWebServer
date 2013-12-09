package com.modules.HttpWriter;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

public class ResponseWriter {
	
	public static HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	public static void write(byte[] data) throws IOException {
		ServletOutputStream output = ServletActionContext.getResponse().getOutputStream();
		output.write(data);
		output.flush();
//		output.close();		// cause for later WriteMessageInterceptor use
	}
	
	
	public static void close() throws IOException {
		ServletOutputStream output = ServletActionContext.getResponse().getOutputStream();
		output.close();
	}
	
}
