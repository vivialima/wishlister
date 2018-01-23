package br.com.vivia.wishlister.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
	private String id;
	private String firstName;
    private String lastName;
    private Photo photo;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Photo getPhoto() {
		return photo;
	}
	public void setPhoto(Photo photo) {
		this.photo = photo;
	}
	public String getUriPhotoUser(){
		String size="100x100";
		String uri = "";
		if (getPhoto()!=null) {
			System.out.println(getPhoto().getPrefix());
			uri = getPhoto().getPrefix()+size+getPhoto().getSuffix();
		}
		return uri;
	}
}
