package com.mobile.constpack.network;

import com.mobile.constpack.network.request.LoginRequest;
import com.mobile.constpack.network.request.UpdateTaskRequest;
import com.mobile.constpack.network.response.BaseResponse;
import com.mobile.constpack.network.response.BlocksResponse;
import com.mobile.constpack.network.response.FloorsResponse;
import com.mobile.constpack.network.response.LoginResponse;
import com.mobile.constpack.network.response.MahalsResponse;
import com.mobile.constpack.network.response.ProductsResponse;
import com.mobile.constpack.network.response.ProjectsResponse;
import com.mobile.constpack.network.response.TasksResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


/**
 * Created by bsoykal on 26/08/16.
 */
public interface RestCalls {

    @POST("Login")
    Call<LoginResponse> requestLogin(@Body LoginRequest loginResponse);

    @GET("Project/{userId}")
    Call<ProjectsResponse> requestProjects(@Path("userId") int userId);

    @GET("Block/{projectId}")
    Call<BlocksResponse> requestBlocks(@Path("projectId") int projectId);

    @GET("Floor/{blockId}")
    Call<FloorsResponse> requestFloors(@Path("blockId") int blockId);

    @GET("Product/{floorId}")
    Call<ProductsResponse> requestProducts(@Path("floorId") int floorId);

    @GET("Locus/{productId}")
    Call<MahalsResponse> requestMahals(@Path("productId") int productId);

    @GET("Task/{locusId}")
    Call<TasksResponse> requestTasks(@Path("locusId") int locusId);

    @POST("Task")
    Call<BaseResponse> requestUpdateTask(@Body UpdateTaskRequest updateTaskRequest);

}
