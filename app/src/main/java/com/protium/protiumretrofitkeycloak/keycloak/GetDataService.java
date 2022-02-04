package com.protium.protiumretrofitkeycloak.keycloak;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface GetDataService {
    @FormUrlEncoded
    @POST("/auth/realms/protium/protocol/openid-connect/token")
    Call<AccessToken> getAccessToken(
            @Field("client_id") String clint_id,
            @Field("grant_type") String grant_type,
            @Field("client_secret") String client_secret,
            @Field("scope") String scope,
            @Field("username") String username,
            @Field("password") String password

        );

    @FormUrlEncoded
    @POST("/auth/realms/protium/protocol/openid-connect/logout")
    Call<ResponseBody> logout(
            @Field("client_id") String clint_id,
            @Field("refresh_token") String grant_type,
            @Field("client_secret") String client_secret
    );


    @GET("/auth/admin/realms/protium/users")
    Call<Users> getUser(
            @Header("Authorization") String authHeader
    );


}
