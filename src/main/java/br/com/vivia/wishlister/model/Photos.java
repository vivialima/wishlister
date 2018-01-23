package br.com.vivia.wishlister.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Photos {
	
	private String count; 
	private List<Items> items; 
    
	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public List<Items> getItems() {
		return items;
	}

	public void setItems(List<Items> items) {
		this.items = items;
	}
	public Items getFirstItem(){
		if (items!=null) {
			return items.get(0);
		}
		return null;
	}
}
