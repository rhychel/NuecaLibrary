package net.nueca.imonggo.tools;

import java.util.ArrayList;

import net.nueca.imonggo.invoice.objects.Payment;

import org.json.JSONArray;
import org.json.JSONException;

public class PaymentArrayList extends ArrayList<Payment> {
	
	public PaymentArrayList() { }
	
	public JSONArray paymentsToJSONArray() throws JSONException {
		JSONArray jsonArr = new JSONArray();
		for(Payment payment : this) {
			jsonArr.put(payment.toJSONObject());
		}
		return jsonArr;
	}
	
}
