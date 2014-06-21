package com.modules.HttpWriter;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

public class ResponseWriter {
	
    
    public static void write(byte[] data) throws IOException {
        ServletOutputStream output = getResponse().getOutputStream();
        output.write(data);
        output.flush();
    }
    
    
    public static void close() throws IOException {
        ServletOutputStream output = getResponse().getOutputStream();
        output.close();
    }
    
    
    
	public static HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	
}
