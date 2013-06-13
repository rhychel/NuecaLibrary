package net.nueca.imonggo.tools;

import java.util.ArrayList;

import net.nueca.imonggo.invoice.objects.InvoiceLine;

import org.json.JSONArray;
import org.json.JSONException;

public class InvoiceLineArrayList extends ArrayList<InvoiceLine> {
	
	public InvoiceLineArrayList() { }
	
	public JSONArray invoiceLineToJSONArray() throws JSONException {
		JSONArray jsonArr = new JSONArray();
		for(InvoiceLine invoiceLine : this) {
			jsonArr.put(invoiceLine.toJSONObject());
		}
		return jsonArr;
	}
	
}
