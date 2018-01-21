package br.com.vivia.wishlister.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)

public class Response {

	 private List<Recent> recent;
	// private Recent recent;

	public List<Recent> getRecent() {
		return recent;
	}

	public void setRecent(List<Recent> recent) {
		this.recent = recent;
	}

}
