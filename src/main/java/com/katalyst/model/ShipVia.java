package com.katalyst.model;

public class ShipVia {
String name;
String provider;
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getProvider() {
	return provider;
}
public void setProvider(String provider) {
	this.provider = provider;
}
@Override
public String toString() {
	return "ShipVia [name=" + name + ", provider=" + provider + "]";
}
}
