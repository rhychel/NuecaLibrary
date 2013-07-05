package net.nueca.imonggo.objects;

import net.nueca.objects.SerializedJSONArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * 	Below are the field that can be processed.
 * <ol>
 * 	<li>id* - <i>System generated ID that uniquely identifies this product</i></li>
 * 	<li>stock_no - <i>Manually assigned code that uniquely identifies this product (may contain letters, space, dash and underscore)</i></li>
 * 	<li>name - <i>product name, duplicate name is not allowed</i></li>
 * 	<li>cost - <i>cost, only zero or positive value is allowed; should have more than 2 decimal places</i></li>
 * 	<li>retail_price - <i>retail price, only zero or positive value is allowed; should not have more than 2 decimal places</i></li>
 * 	<li>description - <i>description</i></li>
 * 	<li>allow_decimal_quantities - <i>Sets this flag to true if decimal value is allowed in quantity (default to false)</i></li>
 * 	<li>disable_discount - <i>Sets this flag to true if retail price cannot be discounted at the store (default to false)</i></li>
 * 	<li>disable_inventory - <i>Sets this flag to true if product does not require inventory tracking (e.g. service), (default to false)</i></li>
 * 	<li>enable_open_price - <i>Sets this flag to true if cashier is allowed to change retail price (default to false)</i></li>
 * 	<li>tax_exempt - <i>Sets this flag to true if tax should not be computed for this item (default to false)</i></li>
 * 	<li>tag_list - <i>list of tags separated with comma</i></li>
 * 	<li>barcode_list - <i>list of barcodes separated with comma</i></li>
 * 	<li>thumbnail_url* - <i>image url</i></li>
 * 	<li>branch_prices* - <i>array containing branch prices</i></li>
 * 	<li>tax_rates* - <i>(read-only) an array containing tax rates</i></li>
 * 	<li>status* - <i>Possible values are NULL and 'D' (NULL = Active, 'D' = Deleted)</i></li>
 * 	<li>utc_created_at - <i>utc_created_at</i></li>
 * 	<li>utc_updated_at - <i>utc_updated_at</i></li>
 * </ol>
 */
@DatabaseTable
public class Product {
	
	@DatabaseField(id = true)
	private int id;
	@DatabaseField
	private double cost, retail_price;
	@DatabaseField
	private String quantity = "", stock_no, name, description, barcode_list, thumbnail_url, status, utc_created_at, utc_updated_at;
	@DatabaseField
	private boolean allow_decimal_quantities, disable_discount, disable_inventory, enable_open_price, tax_exempt;
	@DatabaseField(dataType = DataType.SERIALIZABLE)
	private SerializedJSONArray tax_rates, branch_prices, tag_list;
	// private JSONArray tax_rates, branch_prices, tag_list;
	
	public Product() { }
	
	public Product(JSONObject productObj) {
		processProductObject(productObj);
	}
	
	public void processProductObject(JSONObject jsonObj) {
		try {
			setId(jsonObj.getInt("id"));
			
			setCost(jsonObj.getDouble("cost"));
			setRetail_price(jsonObj.getDouble("retail_price"));
			
			setStock_no(jsonObj.getString("stock_no"));
			setName(jsonObj.getString("name"));
			setDescription(jsonObj.getString("description"));
			setBarcode_list(jsonObj.getString("barcode_list"));
			setThumbnail_url(jsonObj.getString("thumbnail_url"));
			setStatus(jsonObj.getString("status"));
			setUtc_created_at(jsonObj.getString("utc_created_at"));
			setUtc_updated_at(jsonObj.getString("utc_updated_at"));
			
			setAllow_decimal_quantities(jsonObj.getBoolean("allow_decimal_quantities"));
			setDisable_discount(jsonObj.getBoolean("disable_discount"));
			setDisable_inventory(jsonObj.getBoolean("disable_inventory"));
			setEnable_open_price(jsonObj.getBoolean("enable_open_price"));
			setTax_exempt(jsonObj.getBoolean("tax_exempt"));
			
			SerializedJSONArray taxRatesJSONArr = new SerializedJSONArray(jsonObj.getJSONArray("tax_rates"));
			SerializedJSONArray branchPricesJSONArr = new SerializedJSONArray(jsonObj.getJSONArray("branch_prices"));
			SerializedJSONArray tagListJSONArr = new SerializedJSONArray(jsonObj.getJSONArray("tag_list"));
			
			setTax_rates(taxRatesJSONArr);
			setBranch_prices(branchPricesJSONArr);
			setTag_list(tagListJSONArr);
		} catch(JSONException e) {
			e.printStackTrace();
		}
	}
	
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	
	public String getQuantity() {
		return quantity;
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
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public double getRetail_price() {
		return retail_price;
	}
	public void setRetail_price(double retail_price) {
		this.retail_price = retail_price;
	}
	public String getStock_no() {
		return stock_no;
	}
	public void setStock_no(String stock_no) {
		this.stock_no = stock_no;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isAllow_decimal_quantities() {
		return allow_decimal_quantities;
	}
	public void setAllow_decimal_quantities(boolean allow_decimal_quantities) {
		this.allow_decimal_quantities = allow_decimal_quantities;
	}
	public String getBarcode_list() {
		return barcode_list;
	}
	public void setBarcode_list(String barcode_list) {
		this.barcode_list = barcode_list;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getThumbnail_url() {
		return thumbnail_url;
	}
	public void setThumbnail_url(String thumbnail_url) {
		this.thumbnail_url = thumbnail_url;
	}
	public boolean isDisable_discount() {
		return disable_discount;
	}
	public void setDisable_discount(boolean disable_discount) {
		this.disable_discount = disable_discount;
	}
	public boolean isDisable_inventory() {
		return disable_inventory;
	}
	public void setDisable_inventory(boolean disable_inventory) {
		this.disable_inventory = disable_inventory;
	}
	public boolean isEnable_open_price() {
		return enable_open_price;
	}
	public void setEnable_open_price(boolean enable_open_price) {
		this.enable_open_price = enable_open_price;
	}
	public boolean isTax_exempt() {
		return tax_exempt;
	}
	public void setTax_exempt(boolean tax_exempt) {
		this.tax_exempt = tax_exempt;
	}
	public SerializedJSONArray getTax_rates() {
		return tax_rates;
	}
	public void setTax_rates(SerializedJSONArray tax_rates) {
		this.tax_rates = tax_rates;
	}
	public SerializedJSONArray getBranch_prices() {
		return branch_prices;
	}
	public void setBranch_prices(SerializedJSONArray branch_prices) {
		this.branch_prices = branch_prices;
	}
	public SerializedJSONArray getTag_list() {
		return tag_list;
	}
	public void setTag_list(SerializedJSONArray tag_list) {
		this.tag_list = tag_list;
	}
	
}
