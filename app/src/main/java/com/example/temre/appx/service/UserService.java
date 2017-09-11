package com.example.temre.appx.service;

import com.example.temre.appx.models.Login;
import com.example.temre.appx.models.Person;
import com.example.temre.appx.models.Reason;
import com.example.temre.appx.models.personInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface UserService {


    @GET("PersonInfoService/")
    Call<Person> getPerson(@Query("personId") String personId);


    @GET("LoginService/")
    Call<List<Login>> getLoginDatas();

    @GET("LoginPostService/")
    Call<Login> login(@Query("username") String username);

    @GET("Send/")
    Call<String> sendRequest(@Query("username") String username,@Query("role") int role
            ,@Query("startdate") String sdate
            ,@Query("enddate") String edate,@Query("excuse") String excuse
            ,@Query("total") float total,@Query("excuseId") int excuseId);


    @GET("GetMyRequestSituation/")
    Call<Person> GetMyRequestSituationF(@Query("myUserName") String myUserName);

    @GET("GetMembers/")
    Call<List<Person>> GetMyAllTeamMembers(@Query("managerUserName") String managerUserName);

    @GET("GetPerReqMembers/")
    Call<List<Person>> GetMyTeamMemberRequests(@Query("managerUserName") String managerUserName);

    @GET("GetMyReqHistory/")
    Call<List<Person>> GetMyReqHistoryFunc(@Query("userName") String userName);

    @GET("getBasicInfo/")
    Call<personInfo> getMyInformationsFromAccount(@Query("id") int id);

    @GET("ReqAccept/")
    Call<personInfo> reqAcceptFunc(@Query("username") String username);

    @GET("GetPermissionInformations/")
    Call<Person> getDetailedPermissionInfos(@Query("username") String username);

    @GET("GetReasons/")
    Call<List<Reason>> GetReasonsFunc();

    @GET("GetManagerReqHistory/")
    Call<List<Person>>  GetMyPerHistoryFunc(@Query("userName") String userName);



}
