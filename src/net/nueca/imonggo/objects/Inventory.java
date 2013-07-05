package net.nueca.imonggo.objects;

import org.json.JSONException;
import org.json.JSONObject;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * {
 * 		"branch_id": 85, 
 * 		"utc_created_at": "2013/05/20 05:59:05 +0000", 
 * 		"stock_no": "25", 
 * 		"quantity": 2000.0, 
 * 		"product_id": 2924, 
 * 		"utc_updated_at": "2013/05/20 05:59:05 +0000"
 * }
 */
@DatabaseTable
public class Inventory {
	
	@DatabaseField(id = true)
	private int product_id;
	@DatabaseField
	private int branch_id, quantity;
	@DatabaseField
	private String utc_created_at, utc_updated_at, stock_no;
	
	public Inventory(){ }
	
	public Inventory(JSONObject inventoryObj) {
		try {
			setBranch_id(inventoryObj.getInt("branch_id"));
			setProduct_id(inventoryObj.getInt("product_id"));
			setQuantity(inventoryObj.getInt("quantity"));
			setUtc_created_at(inventoryObj.getString("utc_created_at"));
			setUtc_updated_at(inventoryObj.getString("utc_updated_at"));
			setStock_no(inventoryObj.getString("stock_no"));
		} catch(JSONException e) {
			e.printStackTrace();
		}
	}

	public int getBranch_id() {
		return branch_id;
	}

	public void setBranch_id(int branch_id) {
		this.branch_id = branch_id;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
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

	public String getStock_no() {
		return stock_no;
	}

	public void setStock_no(String stock_no) {
		this.stock_no = stock_no;
	}
	
}
