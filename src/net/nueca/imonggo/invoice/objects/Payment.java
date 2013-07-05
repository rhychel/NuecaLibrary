package net.nueca.imonggo.invoice.objects;

import org.json.JSONException;
import org.json.JSONObject;

public class Payment {
	
	private int payment_type_id;
	private double amount, tender;
	
	public Payment() { }
	
	/**
	 * payment_type_id 		|-> 1: cash, 2: credit card, 3: debit card, 4: gift certificate, 5: check, 6: others
	 * payment_type_name* 	|-> Payment type name (e.g. Cash, Credit card, etc.)
	 * amount 				|-> Amount of the payment, should match the total amount including tax
	 * tender				|-> Amount tendered by the customer, if not given it is the same as the amount. If tender amount is 
	 * 							more than amount of the payment, the difference is the change.
	 */
	public Payment(int paymentType, double amount, double tender) {
		setPayment_type_id(paymentType);
		setAmount(amount);
		setTender(tender);
	}
	
	public JSONObject toJSONObject() throws JSONException {
		JSONObject jsonPayment = new JSONObject();
		jsonPayment.put("payment_type_id", getPayment_type_id());
		jsonPayment.put("amount", getAmount());
		jsonPayment.put("tender", getTender());
		
		return jsonPayment;
	}
	
	public int getPayment_type_id() {
		return payment_type_id;
	}
	
	public void setPayment_type_id(int payment_type_id) {
		this.payment_type_id = payment_type_id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getTender() {
		return tender;
	}

	public void setTender(double tender) {
		this.tender = tender;
	}
}
