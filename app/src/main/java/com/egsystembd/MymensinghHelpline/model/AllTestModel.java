package com.egsystembd.MymensinghHelpline.model;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllTestModel implements Serializable {

    @SerializedName("test_list")
    @Expose
    private List<Test> testList = null;

    public List<Test> getTestList() {
        return testList;
    }

    public void setTestList(List<Test> testList) {
        this.testList = testList;
    }


    public class Test implements Serializable {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("hospital_name")
        @Expose
        private String hospitalName;
        @SerializedName("test_name")
        @Expose
        private String testName;
        @SerializedName("test_price")
        @Expose
        private String testPrice;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getHospitalName() {
            return hospitalName;
        }

        public void setHospitalName(String hospitalName) {
            this.hospitalName = hospitalName;
        }

        public String getTestName() {
            return testName;
        }

        public void setTestName(String testName) {
            this.testName = testName;
        }

        public String getTestPrice() {
            return testPrice;
        }

        public void setTestPrice(String testPrice) {
            this.testPrice = testPrice;
        }

    }

}