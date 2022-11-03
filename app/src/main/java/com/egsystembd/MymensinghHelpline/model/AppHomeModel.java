package com.egsystembd.MymensinghHelpline.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class AppHomeModel {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data_response")
    @Expose
    private DataResponse dataResponse;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DataResponse getDataResponse() {
        return dataResponse;
    }

    public void setDataResponse(DataResponse dataResponse) {
        this.dataResponse = dataResponse;
    }


    public class DataResponse {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("top_slider_img1")
        @Expose
        private String topSliderImg1;
        @SerializedName("top_slider_text1")
        @Expose
        private Object topSliderText1;
        @SerializedName("top_slider_img2")
        @Expose
        private String topSliderImg2;
        @SerializedName("top_slider_text2")
        @Expose
        private Object topSliderText2;
        @SerializedName("top_slider_img3")
        @Expose
        private String topSliderImg3;
        @SerializedName("top_slider_text3")
        @Expose
        private Object topSliderText3;
        @SerializedName("top_scroll_text")
        @Expose
        private String topScrollText;
        @SerializedName("top_scroll_text2")
        @Expose
        private String topScrollText2;
        @SerializedName("top_scroll_text3")
        @Expose
        private String topScrollText3;
        @SerializedName("top_scroll_text4")
        @Expose
        private String topScrollText4;
        @SerializedName("top_scroll_text5")
        @Expose
        private String topScrollText5;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getTopSliderImg1() {
            return topSliderImg1;
        }

        public void setTopSliderImg1(String topSliderImg1) {
            this.topSliderImg1 = topSliderImg1;
        }

        public Object getTopSliderText1() {
            return topSliderText1;
        }

        public void setTopSliderText1(Object topSliderText1) {
            this.topSliderText1 = topSliderText1;
        }

        public String getTopSliderImg2() {
            return topSliderImg2;
        }

        public void setTopSliderImg2(String topSliderImg2) {
            this.topSliderImg2 = topSliderImg2;
        }

        public Object getTopSliderText2() {
            return topSliderText2;
        }

        public void setTopSliderText2(Object topSliderText2) {
            this.topSliderText2 = topSliderText2;
        }

        public String getTopSliderImg3() {
            return topSliderImg3;
        }

        public void setTopSliderImg3(String topSliderImg3) {
            this.topSliderImg3 = topSliderImg3;
        }

        public Object getTopSliderText3() {
            return topSliderText3;
        }

        public void setTopSliderText3(Object topSliderText3) {
            this.topSliderText3 = topSliderText3;
        }

        public String getTopScrollText() {
            return topScrollText;
        }

        public void setTopScrollText(String topScrollText) {
            this.topScrollText = topScrollText;
        }

        public String getTopScrollText2() {
            return topScrollText2;
        }

        public void setTopScrollText2(String topScrollText2) {
            this.topScrollText2 = topScrollText2;
        }

        public String getTopScrollText3() {
            return topScrollText3;
        }

        public void setTopScrollText3(String topScrollText3) {
            this.topScrollText3 = topScrollText3;
        }

        public String getTopScrollText4() {
            return topScrollText4;
        }

        public void setTopScrollText4(String topScrollText4) {
            this.topScrollText4 = topScrollText4;
        }

        public String getTopScrollText5() {
            return topScrollText5;
        }

        public void setTopScrollText5(String topScrollText5) {
            this.topScrollText5 = topScrollText5;
        }

    }
}