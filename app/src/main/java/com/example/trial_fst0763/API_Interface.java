package com.example.trial_fst0763;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API_Interface {

    @GET("BrandTestRateList/2")
    Call<DataModel> getPost();

}
