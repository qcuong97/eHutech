package com.example.cpd.ehutech.remote;

import com.example.cpd.ehutech.service.LoginService;

public class ApiUtils {
    public static final String BASE_URL = "https://dacsdacs.herokuapp.com/api/v1/";
    public static LoginService getUserService(){
        return RetrofitClient.getClient(BASE_URL).create(LoginService.class);
    }
}
