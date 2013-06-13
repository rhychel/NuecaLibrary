package net.nueca.imonggo.operations;

import net.nueca.http.HttpRequestProperties;
import net.nueca.http.HttpRequestor;
import net.nueca.http.HttpsRequestProperties;
import net.nueca.http.JSONRequestor;
import net.nueca.http.RequestMethod;
import net.nueca.imonggo.enums.Modules;
import net.nueca.imonggo.enums.Server;
import net.nueca.imonggo.exceptions.LoginException;
import net.nueca.imonggo.interfaces.OnHttpRequestor;
import net.nueca.imonggo.objects.Session;
import net.nueca.imonggo.tools.ImonggoTools;
import net.nueca.tools.EmailValidator;
import android.content.Context;
import android.util.Log;

public class Login {
	
	private String account_id = "", email = "", password = "";
	
	public Login() { }
	
	public Login(String accountId, String email, String password) throws LoginException {
		setAccount_id(accountId);
		setEmail(email);
		setPassword(password);
	}
	
	public String getAccount_id() {
		return account_id;
	}

	public void setAccount_id(String account_id) throws LoginException {
		if(account_id == null)
			throw new LoginException("Account ID is null.");
		else if(!account_id.trim().equals(""))
			this.account_id = account_id;
		else
			throw new LoginException("Account ID is not supplied.");
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) throws LoginException {
		if(email == null)
			throw new LoginException("Email is null.");
		else if(!email.trim().equals("")) {
			EmailValidator emailValidator = new EmailValidator();
			if(emailValidator.validate(email))
				this.email = email;
			else
				throw new LoginException("Email is not valid.");				
		}
		else
			throw new LoginException("Email is not supplied.");
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) throws LoginException {
		if(password == null)
			throw new LoginException("Password is null.");
		else if(!password.trim().equals(""))
			this.password = password;
		else
			throw new LoginException("Password is not supplied.");
	}

	public void requestForAccountUrl(Context context, OnHttpRequestor onHttpReq, HttpsRequestProperties httpReqProp, Server server) {
		switch(server) {
			case IMONGGO:{
				HttpRequestor httpReq = new HttpRequestor(context, ImonggoTools.buildAPIUrlImonggo(context, getAccount_id()), RequestMethod.GET);
				Log.e("URL", httpReq.getRequestUrl());
				httpReq.setModule(Modules.ACCOUNT_URL);
				httpReq.setOnHttpRequestor(onHttpReq);
				httpReq.execute();
			} break;
			case IRETAILCLOUD: {
				HttpRequestor httpReq = new HttpRequestor(context, ImonggoTools.buildAPIUrlIRetailCloud(context, getAccount_id()), RequestMethod.GET);
				httpReq.setModule(Modules.ACCOUNT_URL);
				httpReq.setOnHttpRequestor(onHttpReq);
				httpReq.execute();
			} break;
		}
	}
	
	public void requestForApiToken(Context context, Session session, OnHttpRequestor onHttpReq, HttpsRequestProperties httpReqProp, Server server) {
		switch(server) {
			case IMONGGO: {
				JSONRequestor jsonReq = new JSONRequestor(context, ImonggoTools.buildAPITokenUrl(context, session.getAcctUrl(), Modules.TOKENS, getEmail(), getPassword()), true, RequestMethod.GET);
				jsonReq.setOnHttpRequestor(onHttpReq);
				jsonReq.setModule(Modules.TOKENS);
				jsonReq.execute();
			} break;
			case IRETAILCLOUD: {
				JSONRequestor jsonReq = new JSONRequestor(context, ImonggoTools.buildAPITokenUrl(context, session.getAcctUrl(), Modules.TOKENS, getEmail(), getPassword()), RequestMethod.GET);
				jsonReq.setOnHttpRequestor(onHttpReq);
				jsonReq.setModule(Modules.TOKENS);
				jsonReq.execute();
			} break;
		}
	}
	
}
