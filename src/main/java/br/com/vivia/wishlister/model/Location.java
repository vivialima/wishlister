package br.com.vivia.wishlister.model;

import java.util.List;

import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class Location {
	private String lng;
	private String cc;
	private String state;
	private String country;
	private List<String> formattedAddress;

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	private String lat;

//	public List<String> getFormattedAddress() {
//		return formattedAddress;
//	}
//
//	public void setFormattedAddress(List<String> formattedAddress) {
//		this.formattedAddress = formattedAddress;
//	}

}
