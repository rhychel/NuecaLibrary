package net.nueca.imonggo.objects;

import net.nueca.imonggo.tools.InvoiceLineArrayList;
import net.nueca.imonggo.tools.PaymentArrayList;
import net.nueca.imonggo.tools.TaxRatesArrayList;

public class Invoice {
	
	private PaymentArrayList listOfPayments = null;
	private InvoiceLineArrayList listOfInvoiceLine = null;
	private TaxRatesArrayList listOfTaxRates = null;
	
	public Invoice(){
		listOfPayments = new PaymentArrayList();
		listOfInvoiceLine = new InvoiceLineArrayList();
		listOfTaxRates = new TaxRatesArrayList();
	}
	
	public void sendInvoiceToIRetailcloud() {
		// TODO
	}
	
	public void sendInvoiceToImonggo() {
		// TODO
	}
	
}
