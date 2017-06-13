package com.katalyst.model;

import net.sf.json.JSONObject;

public class SkuJson {
String ArrivalDueDate;
String OrderCancelDate;
String OrderDate;
String PoNumber;
String PrivateNotes;
String PublicNotes;
String RequestedShipDate;
String ShipToAddress;	  
String ShipToWarehouse;
String SupplierName;
String TenantToken;
String TermsName;
String TrackingInfo;
String UserToken;
public String getArrivalDueDate() {
	return ArrivalDueDate;
}
public void setArrivalDueDate(String arrivalDueDate) {
	ArrivalDueDate = arrivalDueDate;
}
public String getOrderCancelDate() {
	return OrderCancelDate;
}
public void setOrderCancelDate(String orderCancelDate) {
	OrderCancelDate = orderCancelDate;
}
public String getOrderDate() {
	return OrderDate;
}
public void setOrderDate(String orderDate) {
	OrderDate = orderDate;
}
public String getPoNumber() {
	return PoNumber;
}
public void setPoNumber(String poNumber) {
	PoNumber = poNumber;
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
public String getRequestedShipDate() {
	return RequestedShipDate;
}
public void setRequestedShipDate(String requestedShipDate) {
	RequestedShipDate = requestedShipDate;
}
public String getShipToAddress() {
	return ShipToAddress;
}
public void setShipToAddress(String shipToAddress) {
	ShipToAddress = shipToAddress;
}
public String getShipToWarehouse() {
	return ShipToWarehouse;
}
public void setShipToWarehouse(String shipToWarehouse) {
	ShipToWarehouse = shipToWarehouse;
}
public String getSupplierName() {
	return SupplierName;
}
public void setSupplierName(String supplierName) {
	SupplierName = supplierName;
}
public String getTenantToken() {
	return TenantToken;
}
public void setTenantToken(String tenantToken) {
	TenantToken = tenantToken;
}
public String getTermsName() {
	return TermsName;
}
public void setTermsName(String termsName) {
	TermsName = termsName;
}
public String getTrackingInfo() {
	return TrackingInfo;
}
public void setTrackingInfo(String trackingInfo) {
	TrackingInfo = trackingInfo;
}
public String getUserToken() {
	return UserToken;
}
public void setUserToken(String userToken) {
	UserToken = userToken;
}
@Override
public String toString() {
	return "SkuJson [ArrivalDueDate=" + ArrivalDueDate + ", OrderCancelDate=" + OrderCancelDate + ", OrderDate="
			+ OrderDate + ", PoNumber=" + PoNumber + ", PrivateNotes=" + PrivateNotes + ", PublicNotes=" + PublicNotes
			+ ", RequestedShipDate=" + RequestedShipDate + ", ShipToAddress=" + ShipToAddress + ", ShipToWarehouse="
			+ ShipToWarehouse + ", SupplierName=" + SupplierName + ", TenantToken=" + TenantToken + ", TermsName="
			+ TermsName + ", TrackingInfo=" + TrackingInfo + ", UserToken=" + UserToken + "]";
}

public JSONObject toJson(){
	JSONObject response = new JSONObject();
	response.put("ArrivalDueDate", getArrivalDueDate());
	response.put("OrderCancelDate", getOrderCancelDate());
	response.put("OrderDate", getOrderDate());
	response.put("PoNumber", getPoNumber());
	response.put("PrivateNotes", getPrivateNotes());
	response.put("PublicNotes", getPublicNotes());
	response.put("RequestedShipDate", getRequestedShipDate());
	response.put("ShipToAddress", getShipToAddress());
	response.put("ShipToWarehouse", getShipToWarehouse());
	response.put("SupplierName", getSupplierName());
	response.put("TenantToken", getTenantToken());
	response.put("TermsName", getTermsName());
	response.put("TrackingInfo", getTrackingInfo());
	response.put("UserToken", getUserToken());
	return response;
}
}


