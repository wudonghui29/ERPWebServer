package com.modules.HttpWriter;

import java.io.IOException;

import javax.servlet.ServletOutputStream;

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
