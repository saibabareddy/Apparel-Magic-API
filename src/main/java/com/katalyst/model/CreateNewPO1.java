package com.katalyst.model;

public class CreateNewPO1 {
	String po_id;
	String style_number;
	String attr2;
	String size;
	String qty;
	String amount;
	
	public String getPo_id() {
		return po_id;
	}
	public void setPo_id(String po_id) {
		this.po_id = po_id;
	}
	public String getStyle_number() {
		return style_number;
	}
	public void setStyle_number(String style_number) {
		this.style_number = style_number;
	}
	public String getAttr2() {
		return attr2;
	}
	public void setAttr2(String attr2) {
		this.attr2 = attr2;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getQty() {
		return qty;
	}
	public void setQty(String qty) {
		this.qty = qty;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "CreateNewPO1 [style_number=" + style_number + ", attr2=" + attr2 + ", size=" + size + ", qty=" + qty
				+ ", amount=" + amount + "]";
	}
	
}
