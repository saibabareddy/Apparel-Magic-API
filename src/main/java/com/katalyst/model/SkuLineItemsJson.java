package com.katalyst.model;

import net.sf.json.JSONObject;

public class SkuLineItemsJson {
	String Cost;
	String Identifier;
	String PrivateNotes;
	String PublicNotes;
	String Quantity;
	String QuantityTo3PL;
	String SKU;
	String Variant;
	public String getCost() {
		return Cost;
	}
	public void setCost(String cost) {
		Cost = cost;
	}
	public String getIdentifier() {
		return Identifier;
	}
	public void setIdentifier(String identifier) {
		Identifier = identifier;
	}
	public String getPrivateNotes() {
		return PrivateNotes;
	}
	public void setPrivateNotes(String privateNotes) {
		PrivateNotes = privateNotes;
	}
	public String getPublicNotes() {
		return PublicNotes;
	}
	public void setPublicNotes(String publicNotes) {
		PublicNotes = publicNotes;
	}
	public String getQuantity() {
		return Quantity;
	}
	public void setQuantity(String quantity) {
		Quantity = quantity;
	}
	public String getQuantityTo3PL() {
		return QuantityTo3PL;
	}
	public void setQuantityTo3PL(String quantityTo3PL) {
		QuantityTo3PL = quantityTo3PL;
	}
	public String getSKU() {
		return SKU;
	}
	public void setSKU(String sKU) {
		SKU = sKU;
	}
	public String getVariant() {
		return Variant;
	}
	public void setVariant(String variant) {
		Variant = variant;
	}
	@Override
	public String toString() {
		return "SkuLineItemsJson [Cost=" + Cost + ", Identifier=" + Identifier + ", PrivateNotes=" + PrivateNotes
				+ ", PublicNotes=" + PublicNotes + ", Quantity=" + Quantity + ", QuantityTo3PL=" + QuantityTo3PL
				+ ", SKU=" + SKU + ", Variant=" + Variant + "]";
	}
	public JSONObject toJson()
	{
		JSONObject respo = new JSONObject();
		respo.put("Cost", getCost());
		respo.put("Identifier", getIdentifier());
		respo.put("PrivateNotes", getPrivateNotes());
		respo.put("PublicNotes", getPublicNotes());
		respo.put("Quantity", getQuantity());
		respo.put("QuantityTo3PL", getQuantityTo3PL());
		respo.put("SKU", getSKU());
		respo.put("Variant", getVariant());
		
		return respo;
	}
}
