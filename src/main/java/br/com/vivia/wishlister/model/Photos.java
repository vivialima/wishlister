package br.com.vivia.wishlister.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)

public class Photos {
	private String count; 
    //private List<String> items; 

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}
}
