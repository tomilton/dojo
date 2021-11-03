package co.com.nequi.api.requestmdw;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Property {
    private List<Item> item;

    @JsonProperty("Item")
    public List<Item> getItem() { return item; }
    @JsonProperty("Item")
    public void setItem(List<Item> value) { this.item = value; }
}
