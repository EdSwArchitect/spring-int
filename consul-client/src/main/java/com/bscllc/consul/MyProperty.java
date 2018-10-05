package com.bscllc.consul;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Configuration
@RefreshScope
public class MyProperty {
    @Value("${address.owners}")
    private String owners;

    @Value("${address.street}")
    private String street;

    @Value("${address.state}")
    private String state;

    @Value("${address.zipcode}")
    private String zipcode;

    @PostConstruct
    public void postConstruct() {
        // to validate if properties are loaded
        System.out.println("** address.owners: " + owners);
        System.out.println("** address.street: " + street);
        System.out.println("** address.state: " + state);
        System.out.println("** address.zipcode: " + zipcode);
    }


    public String getOwners() {
        return owners;
    }

    public void setOwners(String owners) {
        this.owners = owners;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyProperty that = (MyProperty) o;
        return Objects.equals(owners, that.owners) &&
                Objects.equals(street, that.street) &&
                Objects.equals(state, that.state) &&
                Objects.equals(zipcode, that.zipcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(owners, street, state, zipcode);
    }

    @Override
    public String toString() {
        return "MyProperty{" +
                "owners='" + owners + '\'' +
                ", street='" + street + '\'' +
                ", state='" + state + '\'' +
                ", zipcode='" + zipcode + '\'' +
                '}';
    }
}
