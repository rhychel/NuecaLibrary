package net.nueca.imonggo.objects;

import org.json.JSONException;
import org.json.JSONObject;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class LastUpdateAt {
	
	// {"last_updated_at": "2013/05/20 06:11:43 +0000", "module":"<name_of_module>"}
	
	@DatabaseField
	private String module, last_update_at;
	
	public LastUpdateAt() { }
	
	public LastUpdateAt(JSONObject lastUpdatedAtObj) {
		try {
			setModule(lastUpdatedAtObj.getString("module"));
			setLast_updated_at(lastUpdatedAtObj.getString("last_updated_at"));
		} catch(JSONException e) {
			e.printStackTrace();
		}
	}
	
	public void setModule(String module) {
		this.module = module;
	}
	
	public String getModule() {
		return module;
	}
	
	public void setLast_updated_at(String last_updated_at) {
		this.last_update_at = last_updated_at;
	}
	
}
