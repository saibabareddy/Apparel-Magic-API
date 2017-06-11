package com.katalyst.model;

public class CreateNewPO {
	String date_due;
	String date;
	String purchase_order_id;
	String warehouse_id;
	String ship_via;
	String vendor_id;
	String terms_id;
	@Override
	public String toString() {
		return "CreateNewPO [date_due=" + date_due + ", date=" + date + ", purchase_order_id=" + purchase_order_id
				+ ", warehouse_id=" + warehouse_id + ", ship_via=" + ship_via + ", vendor_id=" + vendor_id
				+ ", terms_id=" + terms_id + "]";
	}
	public String getDate_due() {
		return date_due;
	}
	public void setDate_due(String date_due) {
		this.date_due = date_due;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getPurchase_order_id() {
		return purchase_order_id;
	}
	public void setPurchase_order_id(String purchase_order_id) {
		this.purchase_order_id = purchase_order_id;
	}
	public String getWarehouse_id() {
		return warehouse_id;
	}
	public void setWarehouse_id(String warehouse_id) {
		this.warehouse_id = warehouse_id;
	}
	public String getShip_via() {
		return ship_via;
	}
	public void setShip_via(String ship_via) {
		this.ship_via = ship_via;
	}
	public String getVendor_id() {
		return vendor_id;
	}
	public void setVendor_id(String vendor_id) {
		this.vendor_id = vendor_id;
	}
	public String getTerms_id() {
		return terms_id;
	}
	public void setTerms_id(String terms_id) {
		this.terms_id = terms_id;
	}
	

}