package com.egsystembd.MymensinghHelpline.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginModel {

    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("token")
    @Expose
    private String token;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    public class User {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("phone")
        @Expose
        private String phone;
        @SerializedName("address")
        @Expose
        private String address;
        @SerializedName("device_name")
        @Expose
        private String deviceName;
        @SerializedName("user_type")
        @Expose
        private String userType;
        @SerializedName("email_verified_at")
        @Expose
        private Object emailVerifiedAt;
        @SerializedName("two_factor_confirmed_at")
        @Expose
        private Object twoFactorConfirmedAt;
        @SerializedName("current_team_id")
        @Expose
        private Object currentTeamId;
        @SerializedName("profile_photo_path")
        @Expose
        private Object profilePhotoPath;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;
        @SerializedName("profile_photo_url")
        @Expose
        private String profilePhotoUrl;

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

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getDeviceName() {
            return deviceName;
        }

        public void setDeviceName(String deviceName) {
            this.deviceName = deviceName;
        }

        public String getUserType() {
            return userType;
        }

        public void setUserType(String userType) {
            this.userType = userType;
        }

        public Object getEmailVerifiedAt() {
            return emailVerifiedAt;
        }

        public void setEmailVerifiedAt(Object emailVerifiedAt) {
            this.emailVerifiedAt = emailVerifiedAt;
        }

        public Object getTwoFactorConfirmedAt() {
            return twoFactorConfirmedAt;
        }

        public void setTwoFactorConfirmedAt(Object twoFactorConfirmedAt) {
            this.twoFactorConfirmedAt = twoFactorConfirmedAt;
        }

        public Object getCurrentTeamId() {
            return currentTeamId;
        }

        public void setCurrentTeamId(Object currentTeamId) {
            this.currentTeamId = currentTeamId;
        }

        public Object getProfilePhotoPath() {
            return profilePhotoPath;
        }

        public void setProfilePhotoPath(Object profilePhotoPath) {
            this.profilePhotoPath = profilePhotoPath;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public String getProfilePhotoUrl() {
            return profilePhotoUrl;
        }

        public void setProfilePhotoUrl(String profilePhotoUrl) {
            this.profilePhotoUrl = profilePhotoUrl;
        }

    }

}