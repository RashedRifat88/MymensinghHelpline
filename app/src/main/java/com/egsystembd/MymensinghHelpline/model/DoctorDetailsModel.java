package com.egsystembd.MymensinghHelpline.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class DoctorDetailsModel {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("bmdc_no")
    @Expose
    private String bmdcNo;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("speciality")
    @Expose
    private String speciality;
    @SerializedName("degree")
    @Expose
    private String degree;
    @SerializedName("fee")
    @Expose
    private String fee;
    @SerializedName("fee_payment_type")
    @Expose
    private String feePaymentType;
    @SerializedName("available_days")
    @Expose
    private List<String> availableDays = null;
    @SerializedName("experience")
    @Expose
    private String experience;
    @SerializedName("hospital")
    @Expose
    private String hospital;
    @SerializedName("room")
    @Expose
    private String room;
    @SerializedName("image")
    @Expose
    private String image;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBmdcNo() {
        return bmdcNo;
    }

    public void setBmdcNo(String bmdcNo) {
        this.bmdcNo = bmdcNo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getFeePaymentType() {
        return feePaymentType;
    }

    public void setFeePaymentType(String feePaymentType) {
        this.feePaymentType = feePaymentType;
    }

    public List<String> getAvailableDays() {
        return availableDays;
    }

    public void setAvailableDays(List<String> availableDays) {
        this.availableDays = availableDays;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}