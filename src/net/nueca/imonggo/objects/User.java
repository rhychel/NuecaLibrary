package net.nueca.imonggo.objects;

import org.json.JSONException;
import org.json.JSONObject;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * 
 * Below are the field that can be processed.
 * <ol>
 * 	<li>id* - <i>Unique ID within your account</i></li>
 * 	<li>name - <i>Unique name that identifies the User</i></li>
 * 	<li>home_branch_id - <i>Branch id of user's home branch</i></li>
 * 	<li>email - <i>User's email address</i></li>
 * 	<li>role_code - <i>User's role (Possible values are: cashier, supervisor, manager, owner)</i></li>
 * 	<li>utc_created_at* - <i>Created date and time</i></li>
 * 	<li>utc_updated_at* - <i>Last updated date and time</i></li>
 * 	<li>status* - <i>D if deleted, otherwise NULL</i></li>
 * </ol>
 * 
 */

@DatabaseTable
public class User {
	
	@DatabaseField(id = true)
	private int id;
	@DatabaseField
	private int home_branch_id;
	@DatabaseField
	private String name, email, role_code, utc_created_at, utc_updated_at, status;
	
	public User(){ }
	
	public User(JSONObject userObj) throws JSONException {
		setId(userObj.getInt("id"));
		setName(userObj.getString("name"));
		setHome_branch_id(userObj.getInt("home_branch_id"));
		setEmail(userObj.getString("email"));
		setRole_code(userObj.getString("role_code"));
		setUtc_created_at(userObj.getString("utc_created_at"));
		setUtc_updated_at(userObj.getString("utc_updated_at"));
		setStatus(userObj.getString("status"));
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public int getHome_branch_id() {
		return home_branch_id;
	}

	public void setHome_branch_id(int home_branch_id) {
		this.home_branch_id = home_branch_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole_code() {
		return role_code;
	}

	public void setRole_code(String role_code) {
		this.role_code = role_code;
	}

	public String getUtc_created_at() {
		return utc_created_at;
	}

	public void setUtc_created_at(String utc_created_at) {
		this.utc_created_at = utc_created_at;
	}

	public String getUtc_updated_at() {
		return utc_updated_at;
	}

	public void setUtc_updated_at(String utc_updated_at) {
		this.utc_updated_at = utc_updated_at;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
