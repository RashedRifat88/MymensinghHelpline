package com.egsystembd.MymensinghHelpline.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class UploadTestOrderModel {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("response")
    @Expose
    private Response response;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }


    public class Response {

        @SerializedName("pat_id")
        @Expose
        private String patId;
        @SerializedName("pat_name")
        @Expose
        private String patName;
        @SerializedName("pat_mobile")
        @Expose
        private String patMobile;
//        @SerializedName("hospital_name")
//        @Expose
//        private String hospitalName;
//        @SerializedName("test_list")
//        @Expose
//        private String testList;
//        @SerializedName("test_price_list")
//        @Expose
//        private String testPriceList;
        @SerializedName("has_prescription")
        @Expose
        private String hasPrescription;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("date")
        @Expose
        private String date;
        @SerializedName("id")
        @Expose
        private Integer id;

        public String getPatId() {
            return patId;
        }

        public void setPatId(String patId) {
            this.patId = patId;
        }

        public String getPatName() {
            return patName;
        }

        public void setPatName(String patName) {
            this.patName = patName;
        }

        public String getPatMobile() {
            return patMobile;
        }

        public void setPatMobile(String patMobile) {
            this.patMobile = patMobile;
        }

//        public String getHospitalName() {
//            return hospitalName;
//        }
//
//        public void setHospitalName(String hospitalName) {
//            this.hospitalName = hospitalName;
//        }
//
//        public String getTestList() {
//            return testList;
//        }
//
//        public void setTestList(String testList) {
//            this.testList = testList;
//        }
//
//        public String getTestPriceList() {
//            return testPriceList;
//        }
//
//        public void setTestPriceList(String testPriceList) {
//            this.testPriceList = testPriceList;
//        }

        public String getHasPrescription() {
            return hasPrescription;
        }

        public void setHasPrescription(String hasPrescription) {
            this.hasPrescription = hasPrescription;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

    }

}