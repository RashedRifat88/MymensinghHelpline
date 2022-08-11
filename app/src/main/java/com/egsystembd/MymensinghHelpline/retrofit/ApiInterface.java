package com.egsystembd.MymensinghHelpline.retrofit;

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
//    @POST(Api.user_login)
//    Observable<Response<LoginModel>> login(@Header("Accept") String accept,
//                                           @Field("bmdc_no") String device_id,
//                                           @Field("password") String type,
//                                           @Field("login_type") String login_type
//    );
//
//
//    @FormUrlEncoded
//    @POST(Api.user_registration)
//    Observable<Response<RegisterModel>> register(@Header("Accept") String accept,
//                                                 @Field("name") String name,
//                                                 @Field("mobile_number") String mobile_number,
//                                                 @Field("email") String email,
//                                                 @Field("medical_college_id") String medical_college_id,
//                                                 @Field("bmdc_no") String bmdc_no,
//                                                 @Field("password") String password,
//                                                 @Field("password_confirmation") String password_confirmation
//    );
//
//
//    @GET(Api.medical_colleges)
//    Observable<Response<MedicalCollegeModel>> medical_colleges(@Header("Accept") String accept);




//    @POST("retrofit_example/upload_image.php")
//    Call<ServerResponse> uploadFile(@Part MultipartBody.Part file,
//                                    @Part("file") RequestBody name);


//    @Headers("Content-Type: multipart/form-data")
////    @FormUrlEncoded
//    @Multipart
//    @POST(Api.upload_records_of_report)
//    Observable<Response<RecordUpload>> upload_records_of_report(@Header("Authorization") String authorization,
//                                                                @Part List<MultipartBody.Part> files,
//                                                                @Part List<MultipartBody.Part> files_category,
//                                                                @Part("name") RequestBody name,
//                                                                @Query("title") String title,
//                                                                @Query("recordType") String recordType,
//                                                                @Query("recordDate") String recordDate
//
//    );


//    @Multipart
//    @POST(Api.upload_records_of_report)
//    Observable<Response<RecordUpload>> upload_records_of_report(@Header("Authorization") String authorization,
//                                                                @Part MultipartBody.Part file,
//                                                                @Part("name") RequestBody name
//    );


//    @GET(Api.all_article)
//    Observable<Response<ArticleModel>> getAllArticles();


//    @Headers("Content-Type: application/json")
//    @POST(Api.report_issue_msg)
//    Observable<Response<HelpCenter>> send_report_issue_msg(@Body JsonObject body,
//                                                           @Header("authorization") String authorization);
//

//    @GET(Api.invitation)
//    Observable<Response<Invitation>> get_invitation(@Header("Authorization") String authorization,
//                                                    @Query("pageNumber") String pageNumber
//    );


//    @GET()
//    Observable<Response<DoctorDetailsById>> getDoctorDetailsById(@Url String urlString2,
//                                                                 @Header("authorization") String authorization);


}

