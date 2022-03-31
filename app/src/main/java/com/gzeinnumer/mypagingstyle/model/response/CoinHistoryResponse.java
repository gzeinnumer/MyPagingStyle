package com.gzeinnumer.mypagingstyle.model.response;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

public class CoinHistoryResponse{

	@SerializedName("no_order_debit")
	private String noOrderDebit;

	@SerializedName("notes")
	private String notes;

	@SerializedName("no_order_credit")
	private String noOrderCredit;

	@SerializedName("status_name")
	private String statusName;

	@SerializedName("invoice_debit")
	private String invoiceDebit;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("invoice_credit")
	private String invoiceCredit;

	@SerializedName("m_status_id")
	private int mStatusId;

	@SerializedName("id")
	private int id;

	@SerializedName("debit")
	private double debit;

	@SerializedName("credit")
	private double credit;

	public String getNoOrderDebit() {
		return noOrderDebit == null ? "-" : noOrderDebit;
	}

	public String getNotes() {
		return notes;
	}

	public String getNoOrderCredit() {
		return noOrderCredit == null ? "-" : noOrderCredit;
	}

	public String getStatusName() {
		return statusName;
	}

	public String getInvoiceDebit() {
		return invoiceDebit;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public String getInvoiceCredit() {
		return invoiceCredit;
	}

	public int getmStatusId() {
		return mStatusId;
	}

	public int getId() {
		return id;
	}

	public int getDebit() {
		return (int) Math.ceil(debit);
	}

	public int getCredit() {
		return (int) Math.ceil(credit);
	}

	@Override
	public String toString() {
	    return new GsonBuilder().setPrettyPrinting().create().toJson(this, CoinHistoryResponse.class);
	}
}