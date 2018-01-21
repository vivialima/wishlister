package br.com.vivia.wishlister.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class Items {
	private String id;
	private String createdAt;
	private String prefix;
	private String sufix;
	private String width;
	private String height;
	private User user;
	private String visibility;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public String getSufix() {
		return sufix;
	}
	public void setSufix(String sufix) {
		this.sufix = sufix;
	}
	public String getWidth() {
		return width;
	}
	public void setWidth(String width) {
		this.width = width;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getVisibility() {
		return visibility;
	}
	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}
	
}
