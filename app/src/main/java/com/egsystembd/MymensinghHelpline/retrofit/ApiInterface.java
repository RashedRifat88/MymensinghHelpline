package com.egsystembd.MymensinghHelpline.retrofit;

import com.egsystembd.MymensinghHelpline.model.AllTestModel;
import com.egsystembd.MymensinghHelpline.model.AppHomeModel;
import com.egsystembd.MymensinghHelpline.model.AppointmentHistoryModel;
import com.egsystembd.MymensinghHelpline.model.DoctorDetailsModel;
import com.egsystembd.MymensinghHelpline.model.DoctorListModel;
import com.egsystembd.MymensinghHelpline.model.HospitalListModel;
import com.egsystembd.MymensinghHelpline.model.LoginModel;
import com.egsystembd.MymensinghHelpline.model.MakeAppointmentModel;
import com.egsystembd.MymensinghHelpline.model.RegisterModel;
import com.egsystembd.MymensinghHelpline.model.UploadPrescriptionModel;
import com.egsystembd.MymensinghHelpline.model.UploadTestOrderModel;

import java.util.List;

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
                                                          @Field("phone") String phone,
                                                          @Field("password") String password,
                                                          @Field("device_name") String device_name

    );


    @FormUrlEncoded
    @POST(Api.user_login)
    Observable<Response<LoginModel>> user_login(@Header("authorization") String authorization,
                                                @Header("Accept") String accept,
                                                @Field("email_or_phone") String email_or_phone,
                                                @Field("password") String password,
                                                @Field("device_name") String device_name

    );


    @FormUrlEncoded
    @POST(Api.make_appointment)
    Observable<Response<MakeAppointmentModel>> make_appointment(@Header("authorization") String authorization,
                                                                @Header("Accept") String accept,
                                                                @Field("name") String name,
                                                                @Field("email") String email,
                                                                @Field("phone") String phone,
                                                                @Field("doctor") String doctor,
                                                                @Field("date") String date,
                                                                @Field("message") String message,
                                                                @Field("status") String status


    );

    @FormUrlEncoded
    @POST(Api.appointment_history)
    Observable<Response<AppointmentHistoryModel>> appointment_history(@Header("authorization") String authorization,
                                                                      @Header("Accept") String accept,
                                                                      @Field("phone") String phone
    );


//
//    @Multipart
//    @POST(Api.upload_test_order)
//    Observable<Response<UploadTestOrderModel>> upload_prescription(@Header("Authorization") String authorization,
//                                                                   @Header("Accept") String accept,
//                                                                   @Part MultipartBody.Part test_prescription
//
//    );


    @Multipart
    @POST(Api.upload_test_order)
    Observable<Response<UploadTestOrderModel>> upload_prescription(@Header("Authorization") String authorization,
                                                                   @Header("Accept") String accept,
                                                                   @Part MultipartBody.Part body,
                                                                   @Part("pat_id") String pat_id,
                                                                   @Part("pat_name") String pat_name,
                                                                   @Part("pat_mobile") String pat_mobile,
                                                                   @Part("hospital_name[]") List<String> hospital_name,
                                                                   @Part("test_list[]") List<String> test_list,
                                                                   @Part("test_price_list[]") List<String> test_price_list,
                                                                   @Part("has_prescription") String has_prescription,
                                                                   @Part("date") String date

    );


    @FormUrlEncoded
    @POST(Api.upload_test_order)
    Observable<Response<UploadTestOrderModel>> order_tests(@Header("Authorization") String authorization,
                                                           @Header("Accept") String accept,
                                                           @Field("pat_id") String pat_id,
                                                           @Field("pat_name") String pat_name,
                                                           @Field("pat_mobile") String pat_mobile,
                                                           @Field("hospital_name[]") List<String> hospital_name,
                                                           @Field("test_list[]") List<String> test_list,
                                                           @Field("test_price_list[]") List<String> test_price_list,
                                                           @Field("has_prescription") String has_prescription,
                                                           @Field("date") String date

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

    @GET(Api.app_home)
    Observable<Response<AppHomeModel>> appHome(@Header("authorization") String authorization,
                                               @Header("Accept") String accept

    );

    @GET(Api.show_all_test)
    Observable<Response<AllTestModel>> show_all_test(@Header("authorization") String authorization,
                                                     @Header("Accept") String accept

    );


}

