package net.nueca.imonggo.tools;

import java.util.ArrayList;

import net.nueca.imonggo.invoice.objects.TaxRates;
import net.nueca.imonggo.objects.TaxSettings;

import org.json.JSONArray;
import org.json.JSONException;

public class TaxRatesArrayList extends ArrayList<TaxRates> {
	
	public TaxRatesArrayList() { }
	
	public JSONArray taxRatesToJSONArray() throws JSONException {
		JSONArray jsonArr = new JSONArray();
		for(TaxRates taxRates : this) {
			if(TaxSettings.taxAmounts.get(taxRates.getTax_rate_id()) > 0.0)
				jsonArr.put(taxRates.toJSONObject());
		}
		return jsonArr;
	}
	
}
