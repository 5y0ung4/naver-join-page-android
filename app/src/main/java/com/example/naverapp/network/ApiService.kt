package com.example.naverapp.network

import com.example.naverapp.model.UserRequest
import com.example.naverapp.model.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    // @POST("endpoint"): POST 방식으로 요청을 보냄
    // @Body: RequestBody
    @POST("/api/auth/signup")
    suspend fun sendUser(@Body request: UserRequest): String // 서버가 응답(Response)으로 String을 돌려줌

    // @GET("endpoint") : GET 방식으로 요청을 보냄
    // @Query(): RequestParam
    @GET("/api/auth/profile")
    suspend fun getUser(@Query("userId") userId : String) : Response<UserResponse> // 서버가 응답(Response)로 UserResponse 객체를 돌려줌
}