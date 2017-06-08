package com.katalyst.model;

public class Shipment {
	String id;
    String provider;
    String name;
    String shipstation_carrier;
    String shipstation_service;
    
    public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getShipstation_carrier() {
		return shipstation_carrier;
	}
	public void setShipstation_carrier(String shipstation_carrier) {
		this.shipstation_carrier = shipstation_carrier;
	}
	public String getShipstation_service() {
		return shipstation_service;
	}
	public void setShipstation_service(String shipstation_service) {
		this.shipstation_service = shipstation_service;
	}
	@Override
	public String toString() {
		return "Shipment [id=" + id + ", provider=" + provider + ", name=" + name + ", shipstation_carrier="
				+ shipstation_carrier + ", shipstation_service=" + shipstation_service + "]";
	}
	
	

}
