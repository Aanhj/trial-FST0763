package com.example.Interface;

import com.example.ModelClasses.DataModel;
import com.example.ModelClasses.Requset_Data_Model;
import com.example.ModelClasses.Response_Data_Model;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;

public interface API_Interface {

    @GET("BrandTestRateList/2")
    Call<DataModel> getPost();


    @POST("Complaint_module/DH_SUBMIT_LID")
    Call<Response_Data_Model> postAPI(@Body Requset_Data_Model requset_data_model);

   /* @Multipart
    @POST("OrderAllocation/SelfiafterWOE")
*/
}
