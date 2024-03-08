package com.srsoft.pradeepservices.modals;

public class MyPolicies {

    private String policyNumber;
    private String nextDueDate;
    private String mobileNumber;
    private String nomineeName;
    private String nomineeAge;
    private String name;
    private String planName;
    private String dateofCommencement;
    private String lastPremiumDate;
    private String maturity;
    private String sumAssured;
    private String premiumFrequency;

    private String dob;

    public MyPolicies(String name, String planName, String dateofCommencement, String lastPremiumDate, String maturity, String sumAssured, String premiumFrequency, String dob,String nomineeAge, String mobileNumber, String nextDueDate, String policyNumber,String nomineeName) {
        this.name = name;
        this.planName = planName;
        this.dateofCommencement = dateofCommencement;
        this.lastPremiumDate = lastPremiumDate;
        this.maturity = maturity;
        this.sumAssured = sumAssured;
        this.premiumFrequency = premiumFrequency;
        this.dob = dob;
        this.nomineeAge = nomineeAge;
        this.mobileNumber = mobileNumber;
        this.nextDueDate = nextDueDate;
        this.policyNumber = policyNumber;
        this.nomineeName = nomineeName;
    }

    public MyPolicies() {
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public String getNomineeName() {
        return nomineeName;
    }

    public void setNomineeName(String nomineeName) {
        this.nomineeName = nomineeName;
    }

    public String getnextDueDate() {
        return nextDueDate;
    }

    public void setnextDueDate(String nextDueDate) {
        this.nextDueDate = nextDueDate;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getNomineeAge() {
        return nomineeAge;
    }

    public void setNomineeAge(String nomineeAge) {
        this.nomineeAge = nomineeAge;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getDateofCommencement() {
        return dateofCommencement;
    }

    public void setDateofCommencement(String dateofCommencement) {
        this.dateofCommencement = dateofCommencement;
    }

    public String getLastPremiumDate() {
        return lastPremiumDate;
    }

    public void setLastPremiumDate(String lastPremiumDate) {
        this.lastPremiumDate = lastPremiumDate;
    }

    public String getMaturity() {
        return maturity;
    }

    public void setMaturity(String maturity) {
        this.maturity = maturity;
    }

    public String getSumAssured() {
        return sumAssured;
    }

    public void setSumAssured(String sumAssured) {
        this.sumAssured = sumAssured;
    }

    public String getPremiumFrequency() {
        return premiumFrequency;
    }

    public void setPremiumFrequency(String premiumFrequency) {
        this.premiumFrequency = premiumFrequency;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
}
