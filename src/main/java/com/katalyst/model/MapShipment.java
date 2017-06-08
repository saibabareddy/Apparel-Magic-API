package com.katalyst.model;

import net.sf.json.JSONObject;

public class MapShipment {
	String provider;
    String name;
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
	public JSONObject toJson()
	{
		JSONObject newShipment = new JSONObject();
		newShipment.put("provider", getProvider());
		newShipment.put("name", getName());
		return newShipment;
		
	}
	@Override
	public String toString() {
		return "MapShipment [provider=" + provider + ", name=" + name + ", getProvider()=" + getProvider()
				+ ", getName()=" + getName() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	
    
    
    
    

}
