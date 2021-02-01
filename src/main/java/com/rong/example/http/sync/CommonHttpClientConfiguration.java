package com.rong.example.http.sync;

import org.apache.http.Consts;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.nio.charset.CodingErrorAction;
import java.security.GeneralSecurityException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

/**
 * 同步httpclient 配置类
 * 
 * date: 2020年4月26日 下午1:24:15 
 * @author rongyi 
 */
@Configuration
public class CommonHttpClientConfiguration {

	private static final  int CONNECT_TIME_OUT=6000;
	
	private static final  int SOCKET_TIME_OUT=6000;
	
    private static final  int MAX_ROUTES=50;
	
	private static final  int MAX_PER_ROUTE=50;
	
	
	@Bean(name= {"commonHttpclient"})
	public CloseableHttpClient getHttpclient() throws GeneralSecurityException{
		SSLContext sslContext=createIgnoreVerifySSL();
		
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
		Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create()
				.register("http", PlainConnectionSocketFactory.INSTANCE)
				.register("https", sslsf).build();
		
		PoolingHttpClientConnectionManager pool = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
		ConnectionConfig connectionConfig = ConnectionConfig.custom()
				.setMalformedInputAction(CodingErrorAction.IGNORE)
				.setUnmappableInputAction(CodingErrorAction.IGNORE)
				.setCharset(Consts.UTF_8)
				.build();
		
		pool.setDefaultConnectionConfig(connectionConfig);
		pool.setMaxTotal(MAX_ROUTES);
		pool.setDefaultMaxPerRoute(MAX_PER_ROUTE);
		
		RequestConfig requestConfig=RequestConfig.custom()
				.setSocketTimeout(SOCKET_TIME_OUT)
				.setConnectTimeout(CONNECT_TIME_OUT).build();
		
		
		CloseableHttpClient httpclient= HttpClients.custom()
				.setConnectionManager(pool)
				.setDefaultRequestConfig(requestConfig)
				.evictExpiredConnections()
				.build();
		return httpclient;
		
	}
	
	/**
	 * 绕过验证
	 * 	
	 * @return
	 * @throws NoSuchAlgorithmException 
	 * @throws KeyManagementException 
	 */
	public static SSLContext createIgnoreVerifySSL() throws NoSuchAlgorithmException, KeyManagementException {
		SSLContext sc = SSLContext.getInstance("TLSv1.2");
 
		// 实现一个X509TrustManager接口，用于绕过验证，不用修改里面的方法
		X509TrustManager trustManager = new X509TrustManager() {
			@Override
			public void checkClientTrusted(
					java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
					String paramString) {
			}
 
			@Override
			public void checkServerTrusted(
					java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
					String paramString) {
			}
 
			@Override
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return null;
			}
		};
 
		sc.init(null, new TrustManager[] { trustManager }, null);
		return sc;
	}
}