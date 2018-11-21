package in.texasreview.gre.interfaces;


import java.util.ArrayList;
import java.util.List;

import in.texasreview.gre.Models.AnsLikeBodyModel;
import in.texasreview.gre.Models.AnswersModel;
import in.texasreview.gre.Models.CreateAnsBodyModel;
import in.texasreview.gre.Models.CreateAnsResModel;
import in.texasreview.gre.Models.CreateQusBodyModel;
import in.texasreview.gre.Models.CreateQusResModel;
import in.texasreview.gre.Models.CreatedQuestionsModel;
import in.texasreview.gre.Models.FeedBackBodyModel;
import in.texasreview.gre.Models.LikesBodyModel;
import in.texasreview.gre.Models.LikesResModel;
import in.texasreview.gre.Models.LoginBodyModel;
import in.texasreview.gre.Models.LoginResModel;
import in.texasreview.gre.Models.PractUserQuaDataBodyModel;
import in.texasreview.gre.Models.PracticeTestQusModel;
import in.texasreview.gre.Models.PracticeTestIdModel;
import in.texasreview.gre.Models.SubVideosModel;
import in.texasreview.gre.Models.UserDetails;
import in.texasreview.gre.Models.VideoTestUnlockBodyModel;
import in.texasreview.gre.Models.VideosModel;
import in.texasreview.gre.utils.AppConstants;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Copyright DevApp Tech Solutions Pvt Ltd.,
 * Created by Srinivas on 5/6/2017.
 */

public interface ManageServices {

    @POST(AppConstants.LoginUrl)
    Call<LoginResModel> createlogin(@Body LoginBodyModel loginBodyModel);

    @GET(AppConstants.VideosUrl)
    Call<List<VideosModel>> getVideos();

    @GET("Videos/subcatById_total_vedios/{trackId}/{userId}")
    Call<ArrayList<SubVideosModel>> getSubVideos(@Path("trackId") String trackId, @Path("userId") String userId);

    @POST(AppConstants.CreateQusUrl)
    Call<List<CreateQusResModel>> createQustion(@Body CreateQusBodyModel createQusBodyModel);

    @POST(AppConstants.CreateAnsUrl)
    Call<List<CreateAnsResModel>> createAnswer(@Body CreateAnsBodyModel createAnsBodyModel);


    @GET("Videos/subcatById_vedio_ques/{catId}/{userId}/{id}")
    Call<CreatedQuestionsModel> getQuestionsList(@Path("catId") String trackId, @Path("userId") String userId, @Path("id") String id);


    @GET("User/loginget/{Id}")
    Call<UserDetails> getUserDetails(@Path("Id") String Id);


    @GET("Test/test_by_id/{testId}")
    Call<ResponseBody> getVideoQusByQusId(@Path("testId") String testId);

    @GET("Videos/ans_by_queid/{queId}")
    Call<AnswersModel> getVideoAnsByQusId(@Path("queId") String queId);

    @POST(AppConstants.VideosQusLikesUrl)
    Call<List<LikesResModel>> likeOrUnlike(@Body LikesBodyModel likesBodyModel);


    @POST(AppConstants.VideosAnsLikesUrl)
    Call<List<LikesResModel>> ansLikeOrUnlike(@Body AnsLikeBodyModel ansLikeBodyModel);

    @POST(AppConstants.CreateFeedBackUrl)
    Call<List<LikesResModel>> createFeedBack(@Body FeedBackBodyModel feedBackBodyModel);

    @POST(AppConstants.VideoUnlockInsertUrl)
    Call<List<LikesResModel>> videoUnlockInsert(@Body VideoTestUnlockBodyModel videoTestUnlockBodyModel);


    @GET(AppConstants.GetAllPractcategoryUrl)
    Call<PracticeTestIdModel> getAllPracticeTestQuestions();

    @GET("PractCategory/que_id_practcategory/{qusId}/{userId}")
    Call<PracticeTestQusModel> getPracticeQusById(@Path("qusId") String qusId, @Path("userId") String userId);

    @POST(AppConstants.practUserQuaDataUrl)
    Call<List<LikesResModel>> postPractUserQusData(@Body PractUserQuaDataBodyModel practUserQuaDataBodyModel);

    @GET("PractCategory/dashbord/{UserId}")
    Call<ResponseBody> getDashboard(@Path("UserId") String UserId);


}
