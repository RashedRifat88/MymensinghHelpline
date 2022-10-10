package com.egsystembd.MymensinghHelpline.model;


import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class HospitalListModel {

    @SerializedName("hospital_list")
    @Expose
    private List<Hospital> hospitalList = null;

    public List<Hospital> getHospitalList() {
        return hospitalList;
    }

    public void setHospitalList(List<Hospital> hospitalList) {
        this.hospitalList = hospitalList;
    }


    public class Hospital {

        @SerializedName("hospital")
        @Expose
        private String hospital;

        public String getHospital() {
            return hospital;
        }

        public void setHospital(String hospital) {
            this.hospital = hospital;
        }

    }


}
