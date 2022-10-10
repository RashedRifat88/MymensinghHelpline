package com.egsystembd.MymensinghHelpline.retrofit;

import com.egsystembd.MymensinghHelpline.model.DoctorDetailsModel;
import com.egsystembd.MymensinghHelpline.model.DoctorListModel;
import com.egsystembd.MymensinghHelpline.model.HospitalListModel;
import com.egsystembd.MymensinghHelpline.model.LoginModel;
import com.egsystembd.MymensinghHelpline.model.RegisterModel;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface ApiInterface {

//    @FormUrlEncoded
//    @POST(Api.join)
//    Observable<Response<JoinModel>> join(@Header("authorization") String authorization,
//                                         @Header("Accept") String accept,
//                                         @Field("username") String username
//    );
//
//
//    @FormUrlEncoded
//    @POST(Api.auth_confirm)
//    Observable<Response<AuthConfirmModel>> auth_confirm(@Header("authorization") String authorization,
//                                                        @Header("Accept") String accept,
//                                                        @Field("username") String username,
//                                                        @Field("code") String code
//    );
//
//
//    @FormUrlEncoded
//    @POST(Api.auth_reset)
//    Observable<Response<AuthResetModel>> auth_reset_password(@Header("authorization") String authorization,
//                                                             @Header("Accept") String accept,
//                                                             @Field("username") String username,
//                                                             @Field("password") String password,
//                                                             @Field("token") String token
//    );
//
//
//    @FormUrlEncoded
//    @POST(Api.auth_forgot)
//    Observable<Response<AuthForgetPasswordModel>> auth_forgot(@Header("authorization") String authorization,
//                                                              @Header("Accept") String accept,
//                                                              @Field("username") String username
//    );


    @FormUrlEncoded
    @POST(Api.user_registration)
    Observable<Response<RegisterModel>> user_registration(@Header("authorization") String authorization,
                                                          @Header("Accept") String accept,
                                                          @Field("name") String name,
                                                          @Field("username") String username,
                                                          @Field("password") String password,
                                                          @Field("device_name") String device_name,
                                                          @Field("token") String token

    );


    @FormUrlEncoded
    @POST(Api.user_login)
    Observable<Response<LoginModel>> user_login(@Header("authorization") String authorization,
                                                @Header("Accept") String accept,
                                                @Field("email") String email,
                                                @Field("password") String password,
                                                @Field("device_name") String device_name

    );


    @GET(Api.doctors)
    Observable<Response<DoctorListModel>> doctors(@Header("authorization") String authorization,
                                                  @Header("Accept") String accept,
                                                  @Query("department") String department

    );

    @Headers("Content-Type: application/json")
    @GET()
    Observable<Response<DoctorListModel>> doctorsByHospital(@Url String urlString,
                                                            @Header("Authorization") String authorization,
                                                            @Header("Accept") String accept,
                                                            @Query("hospital_name") String hospital_name
    );


//    @GET(Api.doctors_details)
//    Observable<Response<DoctorDetailsModel>> doctorDetailsById(@Header("authorization") String authorization,
//                                                               @Header("Accept") String accept,
//                                                               @Query("doctor_id") String doctor_id
//
//    );

    @Headers("Content-Type: application/json")
    @GET()
    Observable<Response<DoctorDetailsModel>> doctorDetailsById(@Url String urlString,
                                                               @Header("Authorization") String authorization,
                                                               @Header("Accept") String accept
    );


    @GET(Api.hospitals)
    Observable<Response<HospitalListModel>> hospitals(@Header("authorization") String authorization,
                                                      @Header("Accept") String accept

    );


}

