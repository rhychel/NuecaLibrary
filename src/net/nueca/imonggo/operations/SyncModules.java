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

/**
 * 
 * SyncModules class handlers all requesting methods. Just made easy :)
 * 
 * @author rhymart
 *
 */
public class SyncModules {

	/**
	 * 
	 * SendInventory function handles sending inventory count in a way of sending an invoice.
	 * 
	 * @param context
	 * @param jsonObjectValue
	 * @param session
	 * @param onHttpReq
	 * @param httpsReqProp
	 * @param httpReqProp
	 * @param server
	 */
	public static void sendInventory(Context context, JSONObject jsonObjectValue, Session session, OnHttpRequestor onHttpReq, HttpsRequestProperties httpsReqProp, HttpRequestProperties httpReqProp, Server server) {
		switch(server) {
			case IMONGGO: {
				JSONRequestor jsonReq = new JSONRequestor(context, ImonggoTools.buildAPIModuleURL(context, session.getToken(), session.getAcctUrlNoProtocol(), Modules.INVOICES, "", true), true, RequestMethod.POST);
				jsonReq.setOnHttpRequestor(onHttpReq);
				jsonReq.setHttpsRequestProperties(httpsReqProp);
				jsonReq.setModule(Modules.SETTINGS.ordinal());
				jsonReq.setJsonObj(jsonObjectValue);
				jsonReq.execute();
			} break;
			case IRETAILCLOUD: {
				JSONRequestor jsonReq = new JSONRequestor(context, ImonggoTools.buildAPIModuleURL(context, session.getToken(), session.getAcctUrlNoProtocol(), Modules.INVOICES, "", false), RequestMethod.POST);
				jsonReq.setOnHttpRequestor(onHttpReq);
				jsonReq.setModule(Modules.SETTINGS.ordinal());
				jsonReq.setHttpRequestProperties(httpReqProp);
				jsonReq.setJsonObj(jsonObjectValue);
				jsonReq.execute();
			} break;
		}
	}
	
	/**
	 * 
	 * Requester to retrieve settings.
	 * 
	 * @param context
	 * @param session
	 * @param onHttpReq
	 * @param httpsReqProp
	 * @param httpReqProp
	 * @param server
	 */
	public static void getSettings(Context context, Session session, OnHttpRequestor onHttpReq, HttpsRequestProperties httpsReqProp, HttpRequestProperties httpReqProp, Server server) {
		switch(server) {
			case IMONGGO: {
				JSONRequestor jsonReq = new JSONRequestor(context, ImonggoTools.buildAPIModuleURL(context, session.getToken(), session.getAcctUrlNoProtocol(), Modules.SETTINGS, "", true), true, RequestMethod.GET);
				jsonReq.setOnHttpRequestor(onHttpReq);
				jsonReq.setHttpsRequestProperties(httpsReqProp);
				jsonReq.setModule(Modules.SETTINGS.ordinal());
				jsonReq.execute();
			} break;
			case IRETAILCLOUD: {
				JSONRequestor jsonReq = new JSONRequestor(context, ImonggoTools.buildAPIModuleURL(context, session.getToken(), session.getAcctUrlNoProtocol(), Modules.SETTINGS, "", false), RequestMethod.GET);
				jsonReq.setOnHttpRequestor(onHttpReq);
				jsonReq.setModule(Modules.SETTINGS.ordinal());
				jsonReq.setHttpRequestProperties(httpReqProp);
				jsonReq.execute();
			} break;
		}
	}
	
	/**
	 * 
	 * Requester to retrieve all users from the server.
	 * 
	 * @param context
	 * @param session
	 * @param onHttpReq
	 * @param httpsReqProp
	 * @param httpReqProp
	 * @param server
	 * @param page
	 */
	public static void getUsers(Context context, Session session, OnHttpRequestor onHttpReq, HttpsRequestProperties httpsReqProp, HttpRequestProperties httpReqProp, Server server, int page) {
		switch(server) {
			case IMONGGO: {
				JSONRequestor jsonReq = new JSONRequestor(context, ImonggoTools.buildAPIModuleURL(context, session.getToken(), session.getAcctUrlNoProtocol(), Modules.USERS, "?page="+page, true), true, RequestMethod.GET);
				jsonReq.setOnHttpRequestor(onHttpReq);
				jsonReq.setHttpsRequestProperties(httpsReqProp);
				jsonReq.setModule(Modules.USERS.ordinal());
				jsonReq.execute();
			} break;
			case IRETAILCLOUD: {
				JSONRequestor jsonReq = new JSONRequestor(context, ImonggoTools.buildAPIModuleURL(context, session.getToken(), session.getAcctUrlNoProtocol(), Modules.USERS, "?page="+page, false), RequestMethod.GET);
				jsonReq.setOnHttpRequestor(onHttpReq);
				jsonReq.setModule(Modules.USERS.ordinal());
				jsonReq.setHttpRequestProperties(httpReqProp);
				jsonReq.execute();
			} break;
		}
	}
	
	/**
	 * 
	 * Requester to retrieve all the products from Imonggo/IRetail Cloud.
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
				jsonReq.setModule(Modules.PRODUCTS.ordinal());
				jsonReq.execute();
			} break;
			case IRETAILCLOUD: {
				JSONRequestor jsonReq = new JSONRequestor(context, ImonggoTools.buildAPIModuleURL(context, session.getToken(), session.getAcctUrlNoProtocol(), Modules.PRODUCTS, "?page="+page, false), RequestMethod.GET);
				jsonReq.setOnHttpRequestor(onHttpReq);
				jsonReq.setModule(Modules.PRODUCTS.ordinal());
				jsonReq.setHttpRequestProperties(httpReqProp);
				jsonReq.execute();
			} break;
		}
		
	}
	
	/**
	 * 
	 * Requester to retrieve all inventories from server.
	 * 
	 * @param context
	 * @param session
	 * @param onHttpReq
	 * @param httpsReqProp
	 * @param httpReqProp
	 * @param server
	 * @param page
	 */
	public static void getInventories(Context context, Session session, OnHttpRequestor onHttpReq, HttpsRequestProperties httpsReqProp, HttpRequestProperties httpReqProp, Server server, int page) {
		switch(server) {
			case IMONGGO: {
				JSONRequestor jsonReq = new JSONRequestor(context, ImonggoTools.buildAPIModuleURL(context, session.getToken(), session.getAcctUrlNoProtocol(), Modules.INVENTORIES, "?page="+page, true), true, RequestMethod.GET);
				jsonReq.setOnHttpRequestor(onHttpReq);
				jsonReq.setHttpsRequestProperties(httpsReqProp);
				jsonReq.setModule(Modules.INVENTORIES.ordinal());
				jsonReq.execute();
			} break;
			case IRETAILCLOUD: {
				JSONRequestor jsonReq = new JSONRequestor(context, ImonggoTools.buildAPIModuleURL(context, session.getToken(), session.getAcctUrlNoProtocol(), Modules.INVENTORIES, "?page="+page, false), RequestMethod.GET);
				jsonReq.setOnHttpRequestor(onHttpReq);
				jsonReq.setModule(Modules.INVENTORIES.ordinal());
				jsonReq.setHttpRequestProperties(httpReqProp);
				jsonReq.execute();
			} break;
		}
		
	}
	
}
