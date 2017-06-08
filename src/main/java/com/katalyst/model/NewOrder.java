package com.katalyst.model;

import net.sf.json.JSONObject;

public class NewOrder {
	String customer_id;
	String division_id;
	String ar_acct;
	String warehouse_id;
	String currency_id;
	String sku_id;
	String qty;
	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	public String getDivision_id() {
		return division_id;
	}
	public void setDivision_id(String division_id) {
		this.division_id = division_id;
	}
	public String getAr_acct() {
		return ar_acct;
	}
	public void setAr_acct(String ar_acct) {
		this.ar_acct = ar_acct;
	}
	public String getWarehouse_id() {
		return warehouse_id;
	}
	public void setWarehouse_id(String warehouse_id) {
		this.warehouse_id = warehouse_id;
	}
	public String getCurrency_id() {
		return currency_id;
	}
	public void setCurrency_id(String currency_id) {
		this.currency_id = currency_id;
	}
	public String getSku_id() {
		return sku_id;
	}
	public void setSku_id(String sku_id) {
		this.sku_id = sku_id;
	}
	public String getQty() {
		return qty;
	}
	public void setQty(String qty) {
		this.qty = qty;
	}
	public JSONObject toJson()
	{
		JSONObject newOrder = new JSONObject();
		newOrder.put("customer_id", getCustomer_id());
		newOrder.put("division_id", getDivision_id());
		newOrder.put("ar_acct", getAr_acct());
		newOrder.put("warehouse_id", getWarehouse_id());
		newOrder.put("currency_id", getCurrency_id());
		newOrder.put("sku_id", getSku_id());
		newOrder.put("qty", getQty());
		return newOrder;
		
	}
}
