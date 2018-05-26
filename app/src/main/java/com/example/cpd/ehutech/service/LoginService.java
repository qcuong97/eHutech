package com.example.cpd.ehutech.service;

import com.example.cpd.ehutech.model.Post;
import com.example.cpd.ehutech.model.TrangThai;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {
    @POST("auth/login")
    Call<TrangThai> login_mssv(@Body Post post);
}
