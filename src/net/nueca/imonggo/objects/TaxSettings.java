package net.nueca.imonggo.objects;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import android.util.SparseArray;

public class TaxSettings {
	/*
	 * Case 1: {"compute_tax": false}
	 * 
* 	{
* 		"compute_tax": true, 
* 		"tax_inclusive": true, 
 * 		"tax_rates": [
 * 			{"status": null, "name": "philippine tax", "branch_id": null, "id": 753, "value": 0.12, "tax_rate_type": 1}, 
 * 			{"status": null, "name": "sin tax", "branch_id": null, "id": 754, "value": 0.01, "tax_rate_type": 1}
 * 		]
 * 	}
	 */
	
	private boolean compute_tax, tax_inclusive;
	private JSONArray tax_rates = null;
	public static SparseArray<Double> taxAmounts = new SparseArray<Double>();
	private SparseArray<JSONObject> taxesForSelected = new SparseArray<JSONObject>();
	private SparseArray<JSONObject> taxesForAll = new SparseArray<JSONObject>();
	private SparseArray<JSONObject> taxes = new SparseArray<JSONObject>();
	private double taxForAll = 0.0;
	
	public TaxSettings(boolean ct, boolean ti, JSONArray tr) throws JSONException{
		setComputeTax(ct);
		setIsTaxInclusive(ti);
		setTaxRates(tr);
	}
	
	public void setComputeTax(boolean ct){
		this.compute_tax = ct;
	}
	
	public void setIsTaxInclusive(boolean ti){
		this.tax_inclusive = ti;
	}
	
	public double getTaxForAll(){
		return taxForAll;
	}
	
	public SparseArray<JSONObject> getTaxesForSelected(){
		return taxesForSelected;
	}
	
	public boolean isTaxExistsOnTaxesForAll(int tax_id){
		for(int i = 0;i < getTaxesForAll().size();i++){
			if(getTaxesForAll().keyAt(i) == tax_id)
				return true;
		}
		return false;
	}
	public SparseArray<JSONObject> getTaxesForAll(){
		return taxesForAll;
	}
	
	public SparseArray<JSONObject> getTaxes(){
		return taxes;
	}
	
	public void setTaxRates(JSONArray tr) throws JSONException{
		if(tr == null)
			return;
			
		this.tax_rates = tr;
		if(tr.getJSONObject(0).getString("id") == "null"){
			return;
		}
		for(int i = 0;i < tr.length();i++){
			taxes.put(tr.getJSONObject(i).getInt("id"), tr.getJSONObject(i));
			taxAmounts.put(tr.getJSONObject(i).getInt("id"), 0.0);
			if(tr.getJSONObject(i).getInt("tax_rate_type") == 1)
				taxesForSelected.put(tr.getJSONObject(i).getInt("id"), tr.getJSONObject(i));
			else{
				taxForAll += tr.getJSONObject(i).getDouble("value");
				taxesForAll.put(tr.getJSONObject(i).getInt("id"), tr.getJSONObject(i));
			}
		}
	}
	
	public boolean toComputeTax(){
		return compute_tax;
	}
	
	/**
	 * 
	 *
	 * @param  value
	 *         The initial value of the string
	 * @return  boolean
	 */
	public boolean isTaxInclusive(){
		return tax_inclusive;
	}
	
	public JSONArray getTaxRates(){
		return tax_rates;
	}
	
	public boolean isMultipleTax() throws JSONException{
		if(getTaxRates().getJSONObject(0).getString("id") == "null" && getTaxRates().length() == 1)
			return false;
		return true;
	}
	
	public static class Tax {
		private String taxName;
		private double taxValue;
		private int taxId;
		
		public Tax(String tn, double tv, int id){
			setTaxName(tn);
			setTaxValue(tv);
			setTaxId(id);
		}
		
		public void setTaxId(int id){
			this.taxId = id;
		}
		
		public void setTaxName(String tn){
			this.taxName = tn;
		}
		
		public void setTaxValue(double tv){
			this.taxValue = tv;
		}
		
		public String getTaxName(){
			return taxName;
		}
		
		public double getTaxValue(){
			return taxValue;
		}
		
		public int getTaxId(){
			return taxId;
		}
	}
	
	
	public ArrayList<Tax> getTax() {
		
		ArrayList<Tax> taxes = new ArrayList<Tax>();
		try {
			if(isMultipleTax()){
				for(int i = 0;i < getTaxRates().length();i++){
					taxes.add(new Tax(getTaxRates().getJSONObject(i).getString("name"), getTaxRates().getJSONObject(i).getDouble("value"), getTaxRates().getJSONObject(i).getInt("id")));
				}
			}
			else{
				taxes.add(new Tax(getTaxRates().getJSONObject(0).getString("name"), getTaxRates().getJSONObject(0).getDouble("value"), ((getTaxRates().getJSONObject(0).getString("id") == "null") ? -1 : getTaxRates().getJSONObject(0).getInt("id"))));
			}
		} catch(JSONException e){
			e.printStackTrace();
		}
		return taxes;
	}
	
	public double getTax(int tax_id) throws JSONException{
		if(isMultipleTax()){
			return getTaxes().get(tax_id).getDouble("value");
		}
		return getTaxRates().getJSONObject(tax_id).getDouble("value");
	}
	
	
}
