package net.nueca.imonggo.invoice.objects;

import org.json.JSONException;
import org.json.JSONObject;

public class TaxRates {
	
	private int tax_rate_id = -1;
	private double rate, amount;
	
	public TaxRates() { }
	
	/**
	 * rate 	|-> Actual Rate used
	 * amount 	|-> Tax amount
	 */
	public TaxRates(double rate, double amount) {
		setRate(rate);
		setAmount(amount);
	}
	
	public TaxRates(double rate, double amount, int tax_rate_id) {
		setRate(rate);
		setAmount(amount);
		setTax_rate_id(tax_rate_id);
	}
	
	public JSONObject toJSONObject() throws JSONException {
		JSONObject jsonTaxRates = new JSONObject();
		jsonTaxRates.put("rate", getRate());
		jsonTaxRates.put("amount", getAmount());
		if(getTax_rate_id() > -1)
			jsonTaxRates.put("tax_rate_id", getTax_rate_id());
		
		return jsonTaxRates;
	}
	
	public double getRate() {
		return rate;
	}
	
	public void setRate(double rate) {
		this.rate = rate;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public int getTax_rate_id() {
		return tax_rate_id;
	}
	
	public void setTax_rate_id(int tax_rate_id) {
		this.tax_rate_id = tax_rate_id;
	}

}
