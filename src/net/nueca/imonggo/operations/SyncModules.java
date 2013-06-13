package net.nueca.imonggo.operations;

import org.json.JSONObject;

import net.nueca.http.HttpRequestProperties;
import net.nueca.http.HttpsRequestProperties;
import net.nueca.http.JSONRequestor;
import net.nueca.http.RequestMethod;
import net.nueca.imonggo.enums.Modules;
import net.nueca.imonggo.enums.Server;
import net.nueca.imonggo.interfaces.OnHttpRequestor;
import net.nueca.imonggo.objects.Session;
import net.nueca.imonggo.tools.ImonggoTools;
import android.content.Context;

public class SyncModules {

	public static void sendInventory(Context context, JSONObject jsonObjectValue, Session session, OnHttpRequestor onHttpReq, HttpsRequestProperties httpsReqProp, HttpRequestProperties httpReqProp, Server server) {
		switch(server) {
			case IMONGGO: {
				JSONRequestor jsonReq = new JSONRequestor(context, ImonggoTools.buildAPIModuleURL(context, session.getToken(), session.getAcctUrlNoProtocol(), Modules.INVOICES, "", true), true, RequestMethod.POST);
				jsonReq.setOnHttpRequestor(onHttpReq);
				jsonReq.setHttpsRequestProperties(httpsReqProp);
				jsonReq.setModule(Modules.SETTINGS);
				jsonReq.setJsonObj(jsonObjectValue);
				jsonReq.execute();
			} break;
			case IRETAILCLOUD: {
				JSONRequestor jsonReq = new JSONRequestor(context, ImonggoTools.buildAPIModuleURL(context, session.getToken(), session.getAcctUrlNoProtocol(), Modules.INVOICES, "", false), RequestMethod.POST);
				jsonReq.setOnHttpRequestor(onHttpReq);
				jsonReq.setModule(Modules.SETTINGS);
				jsonReq.setHttpRequestProperties(httpReqProp);
				jsonReq.setJsonObj(jsonObjectValue);
				jsonReq.execute();
			} break;
		}
	}
	
	public static void getSettings(Context context, Session session, OnHttpRequestor onHttpReq, HttpsRequestProperties httpsReqProp, HttpRequestProperties httpReqProp, Server server) {
		switch(server) {
			case IMONGGO: {
				JSONRequestor jsonReq = new JSONRequestor(context, ImonggoTools.buildAPIModuleURL(context, session.getToken(), session.getAcctUrlNoProtocol(), Modules.SETTINGS, "", true), true, RequestMethod.GET);
				jsonReq.setOnHttpRequestor(onHttpReq);
				jsonReq.setHttpsRequestProperties(httpsReqProp);
				jsonReq.setModule(Modules.SETTINGS);
				jsonReq.execute();
			} break;
			case IRETAILCLOUD: {
				JSONRequestor jsonReq = new JSONRequestor(context, ImonggoTools.buildAPIModuleURL(context, session.getToken(), session.getAcctUrlNoProtocol(), Modules.SETTINGS, "", false), RequestMethod.GET);
				jsonReq.setOnHttpRequestor(onHttpReq);
				jsonReq.setModule(Modules.SETTINGS);
				jsonReq.setHttpRequestProperties(httpReqProp);
				jsonReq.execute();
			} break;
		}
	}
	
	public static void getUsers(Context context, Session session, OnHttpRequestor onHttpReq, HttpsRequestProperties httpsReqProp, HttpRequestProperties httpReqProp, Server server, int page) {
		switch(server) {
			case IMONGGO: {
				JSONRequestor jsonReq = new JSONRequestor(context, ImonggoTools.buildAPIModuleURL(context, session.getToken(), session.getAcctUrlNoProtocol(), Modules.USERS, "?page="+page, true), true, RequestMethod.GET);
				jsonReq.setOnHttpRequestor(onHttpReq);
				jsonReq.setHttpsRequestProperties(httpsReqProp);
				jsonReq.setModule(Modules.USERS);
				jsonReq.execute();
			} break;
			case IRETAILCLOUD: {
				JSONRequestor jsonReq = new JSONRequestor(context, ImonggoTools.buildAPIModuleURL(context, session.getToken(), session.getAcctUrlNoProtocol(), Modules.USERS, "?page="+page, false), RequestMethod.GET);
				jsonReq.setOnHttpRequestor(onHttpReq);
				jsonReq.setModule(Modules.USERS);
				jsonReq.setHttpRequestProperties(httpReqProp);
				jsonReq.execute();
			} break;
		}
	}
	
	/**
	 * 
	 * Retrieves all the products from Imonggo/IRetail Cloud.
	 * 
	 * @param context
	 * @param session
	 * @param onHttpReq
	 * @param httpsReqProp
	 * @param httpReqProp
	 * @param server
	 * @param page
	 */
	public static void getProducts(Context context, Session session, OnHttpRequestor onHttpReq, HttpsRequestProperties httpsReqProp, HttpRequestProperties httpReqProp, Server server, int page) {
		switch(server) {
			case IMONGGO: {
				JSONRequestor jsonReq = new JSONRequestor(context, ImonggoTools.buildAPIModuleURL(context, session.getToken(), session.getAcctUrlNoProtocol(), Modules.PRODUCTS, "?page="+page, true), true, RequestMethod.GET);
				jsonReq.setOnHttpRequestor(onHttpReq);
				jsonReq.setHttpsRequestProperties(httpsReqProp);
				jsonReq.setModule(Modules.PRODUCTS);
				jsonReq.execute();
			} break;
			case IRETAILCLOUD: {
				JSONRequestor jsonReq = new JSONRequestor(context, ImonggoTools.buildAPIModuleURL(context, session.getToken(), session.getAcctUrlNoProtocol(), Modules.PRODUCTS, "?page="+page, false), RequestMethod.GET);
				jsonReq.setOnHttpRequestor(onHttpReq);
				jsonReq.setModule(Modules.PRODUCTS);
				jsonReq.setHttpRequestProperties(httpReqProp);
				jsonReq.execute();
			} break;
		}
		
	}
	
	public static void getInventories(Context context, Session session, OnHttpRequestor onHttpReq, HttpsRequestProperties httpsReqProp, HttpRequestProperties httpReqProp, Server server, int page) {
		switch(server) {
			case IMONGGO: {
				JSONRequestor jsonReq = new JSONRequestor(context, ImonggoTools.buildAPIModuleURL(context, session.getToken(), session.getAcctUrlNoProtocol(), Modules.INVENTORIES, "?page="+page, true), true, RequestMethod.GET);
				jsonReq.setOnHttpRequestor(onHttpReq);
				jsonReq.setHttpsRequestProperties(httpsReqProp);
				jsonReq.setModule(Modules.INVENTORIES);
				jsonReq.execute();
			} break;
			case IRETAILCLOUD: {
				JSONRequestor jsonReq = new JSONRequestor(context, ImonggoTools.buildAPIModuleURL(context, session.getToken(), session.getAcctUrlNoProtocol(), Modules.INVENTORIES, "?page="+page, false), RequestMethod.GET);
				jsonReq.setOnHttpRequestor(onHttpReq);
				jsonReq.setModule(Modules.INVENTORIES);
				jsonReq.setHttpRequestProperties(httpReqProp);
				jsonReq.execute();
			} break;
		}
		
	}
	
}
