package net.nueca.imonggo.invoice.objects;

import org.json.JSONException;
import org.json.JSONObject;

public class InvoiceLine {
	
	private int product_id, quantity;
	private String discount_text, remark;
	private double retail_price;
	
	public InvoiceLine() { }
	
	/**
	 * product_id+ 			|-> product ID
	 * discount_text++ 		|-> discount either in percent or in value (e.g. 10%, 5)
	 * quantity++ 			|-> quantity (default is 1)
	 * remark++ 			|-> remark or notes
	 */
	public InvoiceLine(int product_id, int quantity, String discount_text, String remark, double retail_price) {
		setProduct_id(product_id);
		setQuantity(quantity);
		setDiscount_text(discount_text);
		setRetail_price(retail_price);
		setRemark(remark);
	}
	
	public JSONObject toJSONObject() throws JSONException {
		JSONObject jsonInvoiceLine = new JSONObject();
		jsonInvoiceLine.put("product_id", getProduct_id());
		jsonInvoiceLine.put("quantity", getQuantity());
		jsonInvoiceLine.put("discount_text", getDiscount_text());
		jsonInvoiceLine.put("retail_price", getRetail_price());
		jsonInvoiceLine.put("remark", getRemark());
		
		return jsonInvoiceLine;
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

	public String getDiscount_text() {
		return discount_text;
	}

	public void setDiscount_text(String discount_text) {
		this.discount_text = discount_text;
	}

	public double getRetail_price() {
		return retail_price;
	}

	public void setRetail_price(double retail_price) {
		this.retail_price = retail_price;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
