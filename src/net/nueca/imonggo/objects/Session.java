package net.nueca.imonggo.objects;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * 
 * Session class that holds the login session of the logged in user.
 * 
 * @author rhymart
 */
@DatabaseTable
public class Session {

	@DatabaseField(id = true)
	private String email = "";
	@DatabaseField
	private String password = "";
	@DatabaseField
	private String api_token = "";
	@DatabaseField
	private String account_id = "";
	@DatabaseField
	private String account_url = "";
	@DatabaseField
	private String api_authentication = "";
	
	public void setEmail(String e){
		this.email = e;
	}
	
	public void setPassword(String p){
		this.password = p;
	}
	
	public void setToken(String t){
		this.api_token = t;
	}
	
	public void setAcctUrl(String au){
		this.account_url = au;
	}
	
	public void setAcctId(String ai){
		this.account_id = ai;
	}
	
	public void setApiAuthentication(String aauth) {
		this.api_authentication = aauth;
	}
	
	public String getEmail(){
		return email;
	}
	
	public String getPassword(){
		return password;
	}
	
	/**
	 * 
	 * Retrieved api_token from the server.
	 * 
	 * @return
	 */
	public String getToken(){
		return api_token;
	}
	
	/**
	 * 
	 * Account URL retrieved.
	 * 
	 * @return
	 */
	public String getAcctUrl(){
		return account_url;
	}
	
	/**
	 * 
	 * Account URL retrieved without protocol.
	 * 
	 * @return
	 */
	public String getAcctUrlNoProtocol(){
		return account_url.replace("http://", "").replace("https://", "");
	}
	
	public String getAcctId(){
		return account_id;
	}
	
	/**
	 * 
	 * Base64 value converted from api_token.
	 * 
	 * @return
	 */
	public String getApiAuthentication() {
		return api_authentication;
	}
	
}
