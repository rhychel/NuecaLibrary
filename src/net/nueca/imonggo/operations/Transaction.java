package net.nueca.imonggo.operations;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import net.nueca.imonggo.invoice.objects.Payment;
import net.nueca.imonggo.objects.TaxSettings;
import net.nueca.imonggo.tools.ImonggoTools;
import net.nueca.imonggo.tools.InvoiceLineArrayList;
import net.nueca.imonggo.tools.PaymentArrayList;
import net.nueca.imonggo.tools.TaxRatesArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

public class Transaction {
	
	public static String sendInventory(Context context, int totalQuantity, InvoiceLineArrayList listInvoiceLines) {
		JSONObject obj = new JSONObject();
		JSONObject invoice = new JSONObject();
		JSONArray invoiceLines = null;
		JSONArray invoiceTaxRates = null;
		JSONArray payments = new JSONArray();

		String[] invoiceDate = ImonggoTools.getCurrentDateTimeInvoice();
		try {
			JSONObject payment = new JSONObject();
			payment.put("amount", (totalQuantity*30));
			payments.put(payment);
			
			invoiceLines = listInvoiceLines.invoiceLineToJSONArray();
			
			invoice.put("invoice_lines", invoiceLines);
			invoice.put("invoice_date", invoiceDate[0]+"T"+invoiceDate[1]+"Z");
			invoice.put("payments", payments);
			obj.put("invoice", invoice);
			
			return obj.toString();
		} catch(Exception e) {
			
		}
		return "NO data";
	}
	
	public static String sendInvoice(Context context, double totalAmount, double taxAmount, int paymentTypeId, 
				PaymentArrayList listPayment, InvoiceLineArrayList listInvoiceLines, TaxRatesArrayList listTaxRates, 
				TaxSettings taxes, String invoiceURL, String email, Callable<Void> callback) {
		JSONObject obj = new JSONObject();
		JSONObject invoice = new JSONObject();
		JSONArray invoiceLines = null;
		JSONArray invoiceTaxRates = null;
		JSONArray payments = null;
		
		String[] invoiceDate = ImonggoTools.getCurrentDateTimeInvoice();
		try {
			payments = listPayment.paymentsToJSONArray();
			invoiceLines = listInvoiceLines.invoiceLineToJSONArray();
			
			if(taxes.toComputeTax()) {
				/*
				if(taxes.isMultipleTax()) {
					
				}
				else {
					
				}
				*/
				invoiceTaxRates = listTaxRates.taxRatesToJSONArray();
				
				invoice.put("invoice_tax_rates", invoiceTaxRates);
				invoice.put("tax_inclusive", taxes.isTaxInclusive());
			}
			invoice.put("invoice_lines", invoiceLines);
			invoice.put("invoice_date", invoiceDate[0]+"T"+invoiceDate[1]+"Z");
			invoice.put("email", email);
			invoice.put("payments", payments);
			obj.put("invoice", invoice);
			
			// TODO Send it!
			return obj.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return "NO DATA";
	}
	
}
