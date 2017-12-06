package com.hotel.cancellation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
public class HttpClient {
	public static final org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(HttpClient.class);
	public static Map<String,String> post(APIHotelCancelRequest APIHotelCancelRequest,String URL){
		 
		Map<String,String> statuMap=new HashMap<>();
		@SuppressWarnings("deprecation")
		DefaultHttpClient  httpClient = new DefaultHttpClient();
		try{
			HttpPost postRequest = new HttpPost(URL);
			postRequest.setHeader("Content-Type","application/json");
			postRequest.addHeader("Accept","application/json");
			logger.info("------- APIHotelCancelRequest.getUserId()--------"+APIHotelCancelRequest.getUserId());
			logger.info("------- APIHotelCancelRequest.getPassword()--------"+APIHotelCancelRequest.getPassword());
			logger.info("------- APIHotelCancelRequest.getAppKey()--------"+APIHotelCancelRequest.getAppKey());
			logger.info("------- APIHotelCancelRequest.getMethodType()--------"+APIHotelCancelRequest.getMethodType());
			logger.info("------- APIHotelCancelRequest.getRemarks()--------"+APIHotelCancelRequest.getRemarks());
			logger.info("------- APIHotelCancelRequest.getOrderId()--------"+APIHotelCancelRequest.getOrderId());
			 
			
			StringBuffer inputjson = new StringBuffer("{"
					+ "\"userId\": \""+APIHotelCancelRequest.getUserId()+"\","
					+ "\"password\": \""+APIHotelCancelRequest.getPassword()+"\","
					+ "\"appKey\": \""+APIHotelCancelRequest.getAppKey()+"\","
					+ "\"orderId\": \""+APIHotelCancelRequest.getOrderId()+"\","
					+ "\"methodType\": \""+APIHotelCancelRequest.getMethodType()+"\","
					+ "\"remarks\": \""+APIHotelCancelRequest.getRemarks()+"\""
					+ "}");
			logger.info("------- inputjson--------"+inputjson.toString());
			logger.info("------- inputjson--------"+inputjson.toString());
			StringEntity input = new StringEntity(inputjson.toString());
			logger.info("------- input--------"+input);
			logger.info("------- input--------"+input);
			postRequest.setEntity(input);

			HttpResponse httpResponse = httpClient.execute(postRequest);
			BufferedReader br = new BufferedReader(new InputStreamReader(
					(httpResponse.getEntity().getContent())));
			String response = null;
			while ((response = br.readLine()) != null) {
				JSONParser jsonParser = new JSONParser();
				logger.info("------- API Cancel response--------"+response);
				if(response!=null){
					JSONObject JSONObject = (JSONObject)jsonParser.parse(response);
					JSONObject  statusObject=(org.json.simple.JSONObject) JSONObject.get("status");
					String message=(String) statusObject.get("message");
					String code=(String) statusObject.get("code");
					statuMap.put(code, message);
				}

			}
			httpClient.getConnectionManager().shutdown();	

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} 
		catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		return statuMap;
	}
	
	
	// HTTP POST request
		public String sendPostParams(String dirName,String URL) throws Exception {
			String response = null;
			@SuppressWarnings("deprecation")
			DefaultHttpClient  httpClient = new DefaultHttpClient();
			try{
				HttpPost postRequest = new HttpPost(URL);
				postRequest.setHeader("Content-Type","application/json");
				postRequest.addHeader("Accept","application/json");
				StringBuffer inputjson = new StringBuffer("{"+"\"dirName\": \""+dirName+"\""+"}");
				logger.info("------- inputjson--------"+inputjson.toString());
				StringEntity input = new StringEntity(inputjson.toString());
				postRequest.setEntity(input);
				HttpResponse httpResponse = httpClient.execute(postRequest);
				BufferedReader br = new BufferedReader(new InputStreamReader(
						(httpResponse.getEntity().getContent())));
				
				while ((response = br.readLine()) != null) {
					 logger.info(response);
				}
				httpClient.getConnectionManager().shutdown();	

			} catch (MalformedURLException e) {
				e.printStackTrace();
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
			return response;
		}
		 
}
