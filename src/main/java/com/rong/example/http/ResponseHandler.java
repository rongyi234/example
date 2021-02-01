package com.rong.example.http;


import com.alibaba.fastjson.JSON;
import org.apache.commons.io.IOUtils;
import org.apache.http.*;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;


public class ResponseHandler {
	
	private static final Logger log = LoggerFactory.getLogger(ResponseHandler.class);

	public static <T> T handle(final CloseableHttpResponse response, Class<T> clz) throws Exception {
		final StatusLine statusLine = response.getStatusLine();
		if (statusLine.getStatusCode() != HttpStatus.SC_OK) {

			closeQuietly(response);
			throw new HttpException("http invoke fail, code ="+ statusLine.getStatusCode());
		} else {
			return responseHandle(response, clz);
		}
	}
	
	private static void closeQuietly(Closeable closeable) {
		try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (final IOException ioe) {
            // ignore
        }
		
	}

	private static <T> T responseHandle(final CloseableHttpResponse response, Class<T> clz) throws Exception{
		HttpEntity entity = response.getEntity();
		String type =entity.getContentType().getValue().toLowerCase();

		InputStream in = null;
		try {
			in = entity.getContent();
			String result = IOUtils.toString(in, "UTF-8");
			log.info("http 返回 ："+result);

			if(clz.equals(String.class)){
				return (T) result;
			}
			if (type.startsWith("application/json")) {
				return JSON.parseObject(result, clz);
			}else{
				throw new HttpException("http return type not support");
			}
		}finally{
			closeQuietly(in);
		}
	}
	

}
