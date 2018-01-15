package com.loveholidays.addressclient.model;


public class Address {
    private String summaryline;
    private String organisation;
    private String buildingname;
    private String number;
    private String premise;
    private String street;
    private String posttown;
    private String county;
    private String postcode;

    public Address(String summaryline, String organisation, String buildingname, String number, String premise, String street, String posttown, String county, String postcode) {
        this.summaryline = summaryline;
        this.organisation = organisation;
        this.buildingname = buildingname;
        this.number = number;
        this.premise = premise;
        this.street = street;
        this.posttown = posttown;
        this.county = county;
        this.postcode = postcode;
    }

    public Address() {
    }

    public String getSummaryline() {
        return summaryline;
    }

    public void setSummaryline(String summaryline) {
        this.summaryline = summaryline;
    }

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public String getBuildingname() {
        return buildingname;
    }

    public void setBuildingname(String buildingname) {
        this.buildingname = buildingname;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPremise() {
        return premise;
    }

    public void setPremise(String premise) {
        this.premise = premise;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPosttown() {
        return posttown;
    }

    public void setPosttown(String posttown) {
        this.posttown = posttown;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    @Override public String toString() {
        return summaryline;
    }
}
