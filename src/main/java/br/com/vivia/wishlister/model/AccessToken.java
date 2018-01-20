package br.com.vivia.wishlister.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AccessToken {
	private String access_token;
	
	public AccessToken(){
		
	}


    public String getAccess_token() {
		return access_token;
	}


	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}


	@Override
    public String toString() {
        return "AccessToken{" +
                "access_token='" + access_token+ '}';
    }
}
