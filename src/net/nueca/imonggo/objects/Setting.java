package net.nueca.imonggo.objects;

import org.json.JSONException;
import org.json.JSONObject;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Setting {
	
	/*
	 * JSONObject format
	 * {"value": "1", "name": "enable_customer_membership"}
	 */

	@DatabaseField(id = true)
	private String name;
	@DatabaseField
	private String value;
	
	public Setting(){ }
	
	public Setting(JSONObject settingObj) throws JSONException {
		setValue(settingObj.getString("value"));
		setName(settingObj.getString("name"));
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
