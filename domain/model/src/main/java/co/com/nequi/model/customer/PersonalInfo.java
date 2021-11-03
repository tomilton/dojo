package co.com.nequi.model.customer;

import co.com.nequi.model.exceptions.CreateCustomerException;

import java.time.LocalDate;

public class PersonalInfo {
    private String name1;
    private String name2;
    private String lastName1;
    private String lastName2;
    private String idNumber;
    private City secondIDNumber;
    private City expeditionDate;
    private LocalDate birthDate;
    private String email;
    private City nickName;
    private String typeID;
    private City secondTypeID;
    private String address;
    private City city;
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

    public City getSecondIDNumber() {
        return secondIDNumber;
    }

    public void setSecondIDNumber(City value) {
        this.secondIDNumber = value;
    }

    public City getExpeditionDate() {
        return expeditionDate;
    }

    public void setExpeditionDate(City value) {
        this.expeditionDate = value;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate value) {
        this.birthDate = value;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String value) {
        this.email = value;
    }

    public City getNickName() {
        return nickName;
    }

    public void setNickName(City value) {
        this.nickName = value;
    }

    public String getTypeID() {
        return typeID;
    }

    public void setTypeID(String value) {
        this.typeID = value;
    }

    public City getSecondTypeID() {
        return secondTypeID;
    }

    public void setSecondTypeID(City value) {
        this.secondTypeID = value;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String value) {
        this.address = value;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City value) {
        this.city = value;
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
