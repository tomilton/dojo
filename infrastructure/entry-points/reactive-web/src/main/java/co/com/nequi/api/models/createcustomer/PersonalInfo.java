package co.com.nequi.api.models.createcustomer;

import com.fasterxml.jackson.annotation.*;

public class PersonalInfo {
    private String name1;
    private String name2;
    private String lastName1;
    private String lastName2;
    private String idNumber;
    private City secondIDNumber;
    private City expeditionDate;
    private String birthDate;
    private String email;
    private City nickName;
    private String typeID;
    private City secondTypeID;
    private String address;
    private City city;
    private String state;
    private String occupation;

    @JsonProperty("name1")
    public String getName1() { return name1; }
    @JsonProperty("name1")
    public void setName1(String value) { this.name1 = value; }

    @JsonProperty("name2")
    public String getName2() { return name2; }
    @JsonProperty("name2")
    public void setName2(String value) { this.name2 = value; }

    @JsonProperty("lastName1")
    public String getLastName1() { return lastName1; }
    @JsonProperty("lastName1")
    public void setLastName1(String value) { this.lastName1 = value; }

    @JsonProperty("lastName2")
    public String getLastName2() { return lastName2; }
    @JsonProperty("lastName2")
    public void setLastName2(String value) { this.lastName2 = value; }

    @JsonProperty("idNumber")
    public String getIDNumber() { return idNumber; }
    @JsonProperty("idNumber")
    public void setIDNumber(String value) { this.idNumber = value; }

    @JsonProperty("secondIdNumber")
    public City getSecondIDNumber() { return secondIDNumber; }
    @JsonProperty("secondIdNumber")
    public void setSecondIDNumber(City value) { this.secondIDNumber = value; }

    @JsonProperty("expeditionDate")
    public City getExpeditionDate() { return expeditionDate; }
    @JsonProperty("expeditionDate")
    public void setExpeditionDate(City value) { this.expeditionDate = value; }

    @JsonProperty("birthDate")
    public String getBirthDate() { return birthDate; }
    @JsonProperty("birthDate")
    public void setBirthDate(String value) { this.birthDate = value; }

    @JsonProperty("email")
    public String getEmail() { return email; }
    @JsonProperty("email")
    public void setEmail(String value) { this.email = value; }

    @JsonProperty("nickName")
    public City getNickName() { return nickName; }
    @JsonProperty("nickName")
    public void setNickName(City value) { this.nickName = value; }

    @JsonProperty("typeId")
    public String getTypeID() { return typeID; }
    @JsonProperty("typeId")
    public void setTypeID(String value) { this.typeID = value; }

    @JsonProperty("secondTypeId")
    public City getSecondTypeID() { return secondTypeID; }
    @JsonProperty("secondTypeId")
    public void setSecondTypeID(City value) { this.secondTypeID = value; }

    @JsonProperty("address")
    public String getAddress() { return address; }
    @JsonProperty("address")
    public void setAddress(String value) { this.address = value; }

    @JsonProperty("city")
    public City getCity() { return city; }
    @JsonProperty("city")
    public void setCity(City value) { this.city = value; }

    @JsonProperty("state")
    public String getState() { return state; }
    @JsonProperty("state")
    public void setState(String value) { this.state = value; }

    @JsonProperty("occupation")
    public String getOccupation() { return occupation; }
    @JsonProperty("occupation")
    public void setOccupation(String value) { this.occupation = value; }
}
