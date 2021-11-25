package co.com.nequi.model.customer;

import co.com.nequi.model.exceptions.CreateCustomerException;

public class PersonalInfo {
    private String name1;
    private String name2;
    private String lastName1;
    private String lastName2;
    private String idNumber;
    private String secondIDNumber;
    private String expeditionDate;
    private String birthDate;
    private String email;
    private String nickName;
    private String typeID;
    private String secondTypeID;
    private String address;
    private String city;
    private String state;
    private String occupation;

    /**
     * Validación de datos requeridos
     */
    public void validarIdNumber() throws CreateCustomerException {
        if (this.idNumber == null || this.idNumber.isEmpty()) {
            throw new CreateCustomerException("El campo idNumber es obligatorio");
        }
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String value) {
        this.name1 = value;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String value) {
        this.name2 = value;
    }

    public String getLastName1() {
        return lastName1;
    }

    public void setLastName1(String value) {
        this.lastName1 = value;
    }

    public String getLastName2() {
        return lastName2;
    }

    public void setLastName2(String value) {
        this.lastName2 = value;
    }

    public String getIDNumber() {
        return idNumber;
    }

    public void setIDNumber(String value) {
        this.idNumber = value;
    }


    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String value) {
        this.birthDate = value;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String value) {
        this.email = value;
    }


    public String getTypeID() {
        return typeID;
    }

    public void setTypeID(String value) {
        this.typeID = value;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getSecondIDNumber() {
        return secondIDNumber;
    }

    public void setSecondIDNumber(String secondIDNumber) {
        this.secondIDNumber = secondIDNumber;
    }

    public String getExpeditionDate() {
        return expeditionDate;
    }

    public void setExpeditionDate(String expeditionDate) {
        this.expeditionDate = expeditionDate;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSecondTypeID() {
        return secondTypeID;
    }

    public void setSecondTypeID(String secondTypeID) {
        this.secondTypeID = secondTypeID;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String value) {
        this.address = value;
    }


    public String getState() {
        return state;
    }

    public void setState(String value) {
        this.state = value;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String value) {
        this.occupation = value;
    }
}
