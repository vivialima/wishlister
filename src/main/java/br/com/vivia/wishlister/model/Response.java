package br.com.vivia.wishlister.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)

public class Response {

	private List<Recent> recent;
    private Photos photos;
    private User user;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Recent> getRecent() {
		return recent;
	}

	public void setRecent(List<Recent> recent) {
		this.recent = recent;
	}

	public Photos getPhotos() {
		return photos;
	}

	public void setPhotos(Photos photos) {
		this.photos = photos;
	}

}
