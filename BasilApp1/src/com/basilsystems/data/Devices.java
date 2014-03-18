package com.basilsystems.data;

public class Devices {
	
	private String name;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPlace_name() {
		return place_name;
	}

	public void setPlace_name(String place_name) {
		this.place_name = place_name;
	}

	private String place_name;

	public Devices(String name, String place_name) {
       this.name = name;
       this.place_name = place_name;
	}

}
