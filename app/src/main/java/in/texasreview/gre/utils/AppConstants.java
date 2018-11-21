package in.texasreview.gre.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;

import in.texasreview.gre.R;

public class AppConstants {

    public static final String YOUTUBE_API_KEY = "AIzaSyATzrSPRFOCE33FyEyNEQvu7Tzd9ndVVso";

    public static final String BaseUrl = "http://18.218.122.78:8080/softprep/api/";
    public static final String LoginUrl = "User/user_login";
    public static final String VideosUrl = "videos/videos_all_list_ui";
    public static final String GetLoginDetailsUrl = "User/loginget/";

    //http://18.218.122.78:8080/softprep/api/User/loginget/51

    /*for this post service call we have to send data through body is
     * {"subcatid":"1","userid":"51","question":"what is angular"}*/
    public static final String CreateQusUrl = "Videos/create_videos_que";
    public static final String CreateAnsUrl = "Videos/create_videos_ans";

    /*http://18.218.122.78:8080/softprep/api/Videos/subcatById_total_vedios/trackid/userid*/
    public static final String SubVideosUrl = "Videos/subcatById_total_vedios/";

    //http://18.218.122.78:8080/softprep/api/Videos/subcatById_vedio_ques/900425/51/1
    /*created quetions get list */
    public static final String CreatedQuListsUrl = "Videos/subcatById_vedio_ques/";

    //videos test get questions
    //http://18.218.122.78:8080/softprep/api/Test/test_by_id/738694

    /*http://18.218.122.78:8080/softprep/api/Videos/ans_by_queid/37*/
    public static final String GetAnswersByQusId = "Videos/ans_by_queid/";

    //video test ans post
   /* http://18.218.122.78:8080/softprep/api/Videos/vedio_unlock_insert
    {userid: "51", videoid: "6"}*/
    public static final String VideoUnlockInsertUrl = "Videos/vedio_unlock_insert";



    //likes
    //http://18.218.122.78:8080/softprep/api/Videos/new_likes
    //body {"que":"39","userid":"51","likes":1,"unlikes":0}
    public static final String VideosQusLikesUrl = "Videos/new_likes";
    public static final String VideosAnsLikesUrl = "Videos/new_likes_ans";

    public static final String CreateFeedBackUrl = "Feedback/create_feedback";
    /* {"action":1001,"useridVal":"51","emailVal":"admin@gmail.com","nameVal":"revanth enumula",
    "expressionper":50,"subjectVal":"Compliments","MessageVal":"swsss"}*/


    /*for get all practice test questions*/
    public static final String GetAllPractcategoryUrl = "PractCategory/all_practcategory";

    //http://18.218.122.78:8080/softprep/api/PractCategory/que_id_practcategory/452071/51
    /*for Get Practice Test Qus By Id*/
    public static final String GetPracticeTestQusById = "PractCategory/que_id_practcategory/";


    public static final String practUserQuaDataUrl = "PractCategory/create_practUserQuaData";


    /*http://18.218.122.78:8080/softprep/api/PractCategory/dashbord/51*/
    public static final String GetDashboard = "PractCategory/dashbord/51";


    public static final String TRACKIDKEY = "subcatlistkey";
    public static final String VIDEOURLKEY = "video_url_key";
    public static final String SUBCATIDKEY = "subcat_id_key";
    public static final String CATIDKEY = "cat_id_key";
    public static final String TESTIDKEY = "test_id_key";
    public static final String QUSTIONIDKEY = "qus_id_key";
    public static final String VIDEOKEY = "video_key";
    public static final String VIDEOIDKEY = "video_id_key";

    public static final String PRACTICETESTIDSKEY = "practicetest_ids_key";
    public static final String SESSIONKEY = "session_key";





}
