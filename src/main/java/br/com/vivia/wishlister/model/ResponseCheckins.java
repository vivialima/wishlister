package br.com.vivia.wishlister.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)

public class ResponseCheckins {
 private Response response;

public Response getResponse() {
	return response;
}

public void setResponse(Response response) {
	this.response = response;
}
 

}
