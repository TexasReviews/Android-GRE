package in.texasreview.gre.Models;

import java.util.ArrayList;
import java.util.Comparator;

public class CreatedQuestionsModel {

    /**
     * video_link : https://www.youtube.com/embed/5rYZ7JTBJGg
     * questions : [{"id":"37","userid":"51","question":"what is angularjs?","date":"2018-08-06 16:01:42","youtube_uniqid":"https://www.youtube.com/embed/5rYZ7JTBJGg","testname":"Test 1","userdata":{"fullname":"revanth enumula"},"ans_count":2,"like":"2","unlike":"0","avatar":"5.png"},{"id":"38","userid":"51","question":"what is android","date":"2018-09-08 12:23:56","youtube_uniqid":"https://www.youtube.com/embed/5rYZ7JTBJGg","testname":"Test 1","userdata":{"fullname":"revanth enumula"},"ans_count":1,"like":"2","unlike":"0","avatar":"14.png"},{"id":"39","userid":"51","question":"sdvhjkl;';lkjhgf","date":"2018-09-25 10:11:15","youtube_uniqid":"https://www.youtube.com/embed/5rYZ7JTBJGg","testname":"Test 1","userdata":{"fullname":"revanth enumula"},"ans_count":2,"like":"0","unlike":"1","avatar":"15.png"},{"id":"40","userid":"51","question":"what is andriod","date":"2018-10-01 09:06:54","youtube_uniqid":"https://www.youtube.com/embed/5rYZ7JTBJGg","testname":"Test 1","userdata":{"fullname":"revanth enumula"},"ans_count":0,"like":"1","unlike":"0","avatar":"14.png"},{"id":"41","userid":"51","question":"what is angular","date":"2018-10-01 09:17:38","youtube_uniqid":"https://www.youtube.com/embed/5rYZ7JTBJGg","testname":"Test 1","userdata":{"fullname":"revanth enumula"},"ans_count":0,"like":"0","unlike":"0","avatar":"14.png"},{"id":"42","userid":"51","question":"what is angular","date":"2018-10-01 12:53:25","youtube_uniqid":"https://www.youtube.com/embed/5rYZ7JTBJGg","testname":"Test 1","userdata":{"fullname":"revanth enumula"},"ans_count":0,"like":"0","unlike":"0","avatar":"18.png"},{"id":"43","userid":"52","question":"what is anjular js","date":"2018-10-03 13:07:49","youtube_uniqid":"https://www.youtube.com/embed/5rYZ7JTBJGg","testname":"Test 1","userdata":{"fullname":"shivareddy"},"ans_count":0,"like":"0","unlike":"0","avatar":"15.png"},{"id":"44","userid":"52","question":"what is anjular js","date":"2018-10-03 13:09:32","youtube_uniqid":"https://www.youtube.com/embed/5rYZ7JTBJGg","testname":"Test 1","userdata":{"fullname":"shivareddy"},"ans_count":0,"like":"0","unlike":"0","avatar":"17.png"},{"id":"45","userid":"51","question":"what is angular","date":"2018-10-03 13:10:14","youtube_uniqid":"https://www.youtube.com/embed/5rYZ7JTBJGg","testname":"Test 1","userdata":{"fullname":"revanth enumula"},"ans_count":0,"like":"0","unlike":"0","avatar":"3.png"},{"id":"46","userid":"52","question":"hiiiii","date":"2018-10-03 13:14:51","youtube_uniqid":"https://www.youtube.com/embed/5rYZ7JTBJGg","testname":"Test 1","userdata":{"fullname":"shivareddy"},"ans_count":0,"like":"0","unlike":"0","avatar":"4.png"},{"id":"47","userid":"52","question":"hiiiii","date":"2018-10-03 13:16:13","youtube_uniqid":"https://www.youtube.com/embed/5rYZ7JTBJGg","testname":"Test 1","userdata":{"fullname":"shivareddy"},"ans_count":0,"like":"0","unlike":"0","avatar":"18.png"},{"id":"48","userid":"52","question":"hiiiii","date":"2018-10-03 13:16:46","youtube_uniqid":"https://www.youtube.com/embed/5rYZ7JTBJGg","testname":"Test 1","userdata":{"fullname":"shivareddy"},"ans_count":0,"like":"0","unlike":"0","avatar":"4.png"},{"id":"49","userid":"52","question":"hiiiii","date":"2018-10-03 13:16:59","youtube_uniqid":"https://www.youtube.com/embed/5rYZ7JTBJGg","testname":"Test 1","userdata":{"fullname":"shivareddy"},"ans_count":0,"like":"0","unlike":"0","avatar":"3.png"},{"id":"50","userid":"52","question":"hiiiii","date":"2018-10-03 13:17:01","youtube_uniqid":"https://www.youtube.com/embed/5rYZ7JTBJGg","testname":"Test 1","userdata":{"fullname":"shivareddy"},"ans_count":0,"like":"0","unlike":"0","avatar":"18.png"},{"id":"51","userid":"52","question":"hiiiii","date":"2018-10-03 13:17:03","youtube_uniqid":"https://www.youtube.com/embed/5rYZ7JTBJGg","testname":"Test 1","userdata":{"fullname":"shivareddy"},"ans_count":0,"like":"0","unlike":"0","avatar":"11.png"},{"id":"85","userid":"52","question":"hiii","date":"2018-10-03 13:32:21","youtube_uniqid":"https://www.youtube.com/embed/5rYZ7JTBJGg","testname":"Test 1","userdata":{"fullname":"shivareddy"},"ans_count":0,"like":"0","unlike":"0","avatar":"6.png"},{"id":"86","userid":"52","question":"question","date":"2018-10-03 13:32:36","youtube_uniqid":"https://www.youtube.com/embed/5rYZ7JTBJGg","testname":"Test 1","userdata":{"fullname":"shivareddy"},"ans_count":0,"like":"0","unlike":"0","avatar":"20.png"},{"id":"87","userid":"52","question":"vghhhh","date":"2018-10-03 13:32:48","youtube_uniqid":"https://www.youtube.com/embed/5rYZ7JTBJGg","testname":"Test 1","userdata":{"fullname":"shivareddy"},"ans_count":0,"like":"0","unlike":"0","avatar":"18.png"},{"id":"88","userid":"52","question":"tfryhb","date":"2018-10-03 13:33:40","youtube_uniqid":"https://www.youtube.com/embed/5rYZ7JTBJGg","testname":"Test 1","userdata":{"fullname":"shivareddy"},"ans_count":0,"like":"0","unlike":"0","avatar":"17.png"},{"id":"89","userid":"52","question":"what is android","date":"2018-10-03 14:52:17","youtube_uniqid":"https://www.youtube.com/embed/5rYZ7JTBJGg","testname":"Test 1","userdata":{"fullname":"shivareddy"},"ans_count":0,"like":"0","unlike":"0","avatar":"5.png"},{"id":"90","userid":"52","question":"what is android","date":"2018-10-03 14:52:29","youtube_uniqid":"https://www.youtube.com/embed/5rYZ7JTBJGg","testname":"Test 1","userdata":{"fullname":"shivareddy"},"ans_count":0,"like":"0","unlike":"0","avatar":"20.png"},{"id":"91","userid":"52","question":"what is android","date":"2018-10-03 14:52:29","youtube_uniqid":"https://www.youtube.com/embed/5rYZ7JTBJGg","testname":"Test 1","userdata":{"fullname":"shivareddy"},"ans_count":0,"like":"0","unlike":"0","avatar":"6.png"},{"id":"92","userid":"52","question":"what is android","date":"2018-10-03 14:52:30","youtube_uniqid":"https://www.youtube.com/embed/5rYZ7JTBJGg","testname":"Test 1","userdata":{"fullname":"shivareddy"},"ans_count":0,"like":"0","unlike":"0","avatar":"1.png"},{"id":"93","userid":"52","question":"what is android","date":"2018-10-03 14:52:31","youtube_uniqid":"https://www.youtube.com/embed/5rYZ7JTBJGg","testname":"Test 1","userdata":{"fullname":"shivareddy"},"ans_count":0,"like":"0","unlike":"0","avatar":"16.png"},{"id":"94","userid":"52","question":"what is android","date":"2018-10-03 14:52:31","youtube_uniqid":"https://www.youtube.com/embed/5rYZ7JTBJGg","testname":"Test 1","userdata":{"fullname":"shivareddy"},"ans_count":0,"like":"0","unlike":"0","avatar":"20.png"},{"id":"95","userid":"52","question":"what is android","date":"2018-10-03 14:52:31","youtube_uniqid":"https://www.youtube.com/embed/5rYZ7JTBJGg","testname":"Test 1","userdata":{"fullname":"shivareddy"},"ans_count":0,"like":"0","unlike":"0","avatar":"11.png"},{"id":"96","userid":"52","question":"what is android","date":"2018-10-03 14:52:32","youtube_uniqid":"https://www.youtube.com/embed/5rYZ7JTBJGg","testname":"Test 1","userdata":{"fullname":"shivareddy"},"ans_count":0,"like":"0","unlike":"0","avatar":"14.png"},{"id":"97","userid":"52","question":"what is android","date":"2018-10-03 14:52:32","youtube_uniqid":"https://www.youtube.com/embed/5rYZ7JTBJGg","testname":"Test 1","userdata":{"fullname":"shivareddy"},"ans_count":0,"like":"0","unlike":"0","avatar":"9.png"},{"id":"98","userid":"52","question":"what is android","date":"2018-10-03 14:52:34","youtube_uniqid":"https://www.youtube.com/embed/5rYZ7JTBJGg","testname":"Test 1","userdata":{"fullname":"shivareddy"},"ans_count":0,"like":"0","unlike":"0","avatar":"7.png"},{"id":"99","userid":"52","question":"what is android","date":"2018-10-03 14:52:34","youtube_uniqid":"https://www.youtube.com/embed/5rYZ7JTBJGg","testname":"Test 1","userdata":{"fullname":"shivareddy"},"ans_count":0,"like":"0","unlike":"0","avatar":"1.png"},{"id":"100","userid":"52","question":"what is android","date":"2018-10-03 14:52:35","youtube_uniqid":"https://www.youtube.com/embed/5rYZ7JTBJGg","testname":"Test 1","userdata":{"fullname":"shivareddy"},"ans_count":0,"like":"0","unlike":"0","avatar":"13.png"},{"id":"101","userid":"52","question":"what is android","date":"2018-10-03 14:52:35","youtube_uniqid":"https://www.youtube.com/embed/5rYZ7JTBJGg","testname":"Test 1","userdata":{"fullname":"shivareddy"},"ans_count":0,"like":"0","unlike":"0","avatar":"12.png"},{"id":"102","userid":"52","question":"what is android","date":"2018-10-03 14:52:36","youtube_uniqid":"https://www.youtube.com/embed/5rYZ7JTBJGg","testname":"Test 1","userdata":{"fullname":"shivareddy"},"ans_count":0,"like":"0","unlike":"0","avatar":"14.png"},{"id":"103","userid":"52","question":"what is android","date":"2018-10-03 14:52:37","youtube_uniqid":"https://www.youtube.com/embed/5rYZ7JTBJGg","testname":"Test 1","userdata":{"fullname":"shivareddy"},"ans_count":0,"like":"0","unlike":"0","avatar":"7.png"},{"id":"104","userid":"52","question":"what is android","date":"2018-10-03 14:52:38","youtube_uniqid":"https://www.youtube.com/embed/5rYZ7JTBJGg","testname":"Test 1","userdata":{"fullname":"shivareddy"},"ans_count":0,"like":"0","unlike":"0","avatar":"5.png"},{"id":"105","userid":"52","question":"what is android","date":"2018-10-03 14:52:38","youtube_uniqid":"https://www.youtube.com/embed/5rYZ7JTBJGg","testname":"Test 1","userdata":{"fullname":"shivareddy"},"ans_count":0,"like":"0","unlike":"0","avatar":"7.png"},{"id":"106","userid":"52","question":"what is android","date":"2018-10-03 14:52:38","youtube_uniqid":"https://www.youtube.com/embed/5rYZ7JTBJGg","testname":"Test 1","userdata":{"fullname":"shivareddy"},"ans_count":0,"like":"0","unlike":"0","avatar":"5.png"},{"id":"107","userid":"52","question":"what is android","date":"2018-10-03 14:52:38","youtube_uniqid":"https://www.youtube.com/embed/5rYZ7JTBJGg","testname":"Test 1","userdata":{"fullname":"shivareddy"},"ans_count":0,"like":"0","unlike":"0","avatar":"20.png"},{"id":"108","userid":"52","question":"what is android","date":"2018-10-03 14:52:39","youtube_uniqid":"https://www.youtube.com/embed/5rYZ7JTBJGg","testname":"Test 1","userdata":{"fullname":"shivareddy"},"ans_count":0,"like":"0","unlike":"0","avatar":"4.png"},{"id":"109","userid":"52","question":"what is android","date":"2018-10-03 14:52:39","youtube_uniqid":"https://www.youtube.com/embed/5rYZ7JTBJGg","testname":"Test 1","userdata":{"fullname":"shivareddy"},"ans_count":0,"like":"0","unlike":"0","avatar":"8.png"},{"id":"110","userid":"52","question":"what is android","date":"2018-10-03 14:52:39","youtube_uniqid":"https://www.youtube.com/embed/5rYZ7JTBJGg","testname":"Test 1","userdata":{"fullname":"shivareddy"},"ans_count":0,"like":"0","unlike":"0","avatar":"3.png"},{"id":"111","userid":"52","question":"what is android","date":"2018-10-03 14:52:40","youtube_uniqid":"https://www.youtube.com/embed/5rYZ7JTBJGg","testname":"Test 1","userdata":{"fullname":"shivareddy"},"ans_count":0,"like":"0","unlike":"0","avatar":"2.png"},{"id":"112","userid":"52","question":"what is android","date":"2018-10-03 14:52:40","youtube_uniqid":"https://www.youtube.com/embed/5rYZ7JTBJGg","testname":"Test 1","userdata":{"fullname":"shivareddy"},"ans_count":0,"like":"0","unlike":"0","avatar":"12.png"},{"id":"113","userid":"52","question":"what is android","date":"2018-10-03 14:52:40","youtube_uniqid":"https://www.youtube.com/embed/5rYZ7JTBJGg","testname":"Test 1","userdata":{"fullname":"shivareddy"},"ans_count":0,"like":"0","unlike":"0","avatar":"6.png"},{"id":"114","userid":"52","question":"what is android","date":"2018-10-03 14:52:40","youtube_uniqid":"https://www.youtube.com/embed/5rYZ7JTBJGg","testname":"Test 1","userdata":{"fullname":"shivareddy"},"ans_count":0,"like":"0","unlike":"0","avatar":"19.png"},{"id":"115","userid":"52","question":"what is android","date":"2018-10-03 14:52:41","youtube_uniqid":"https://www.youtube.com/embed/5rYZ7JTBJGg","testname":"Test 1","userdata":{"fullname":"shivareddy"},"ans_count":0,"like":"0","unlike":"0","avatar":"2.png"},{"id":"116","userid":"52","question":"what is android","date":"2018-10-03 14:52:41","youtube_uniqid":"https://www.youtube.com/embed/5rYZ7JTBJGg","testname":"Test 1","userdata":{"fullname":"shivareddy"},"ans_count":0,"like":"0","unlike":"0","avatar":"12.png"},{"id":"117","userid":"52","question":"what is android","date":"2018-10-03 14:52:41","youtube_uniqid":"https://www.youtube.com/embed/5rYZ7JTBJGg","testname":"Test 1","userdata":{"fullname":"shivareddy"},"ans_count":0,"like":"0","unlike":"0","avatar":"18.png"},{"id":"118","userid":"52","question":"what is android","date":"2018-10-03 14:52:42","youtube_uniqid":"https://www.youtube.com/embed/5rYZ7JTBJGg","testname":"Test 1","userdata":{"fullname":"shivareddy"},"ans_count":0,"like":"0","unlike":"0","avatar":"20.png"},{"id":"119","userid":"52","question":"what is android","date":"2018-10-03 14:52:42","youtube_uniqid":"https://www.youtube.com/embed/5rYZ7JTBJGg","testname":"Test 1","userdata":{"fullname":"shivareddy"},"ans_count":0,"like":"0","unlike":"0","avatar":"8.png"},{"id":"120","userid":"52","question":"what is android","date":"2018-10-03 14:52:43","youtube_uniqid":"https://www.youtube.com/embed/5rYZ7JTBJGg","testname":"Test 1","userdata":{"fullname":"shivareddy"},"ans_count":0,"like":"0","unlike":"0","avatar":"3.png"},{"id":"121","userid":"52","question":"what is android","date":"2018-10-03 14:52:43","youtube_uniqid":"https://www.youtube.com/embed/5rYZ7JTBJGg","testname":"Test 1","userdata":{"fullname":"shivareddy"},"ans_count":0,"like":"0","unlike":"0","avatar":"19.png"},{"id":"122","userid":"52","question":"what is android","date":"2018-10-03 14:52:43","youtube_uniqid":"https://www.youtube.com/embed/5rYZ7JTBJGg","testname":"Test 1","userdata":{"fullname":"shivareddy"},"ans_count":0,"like":"0","unlike":"0","avatar":"14.png"},{"id":"123","userid":"52","question":"what","date":"2018-10-04 04:54:11","youtube_uniqid":"https://www.youtube.com/embed/5rYZ7JTBJGg","testname":"Test 1","userdata":{"fullname":"shivareddy"},"ans_count":0,"like":"0","unlike":"0","avatar":"4.png"},{"id":"124","userid":"52","question":"what is java","date":"2018-10-04 04:57:02","youtube_uniqid":"https://www.youtube.com/embed/5rYZ7JTBJGg","testname":"Test 1","userdata":{"fullname":"shivareddy"},"ans_count":0,"like":"0","unlike":"0","avatar":"14.png"},{"id":"125","userid":"52","question":"what is photo shop","date":"2018-10-04 04:57:35","youtube_uniqid":"https://www.youtube.com/embed/5rYZ7JTBJGg","testname":"Test 1","userdata":{"fullname":"shivareddy"},"ans_count":0,"like":"0","unlike":"0","avatar":"13.png"},{"id":"126","userid":"52","question":"","date":"2018-10-04 04:58:00","youtube_uniqid":"https://www.youtube.com/embed/5rYZ7JTBJGg","testname":"Test 1","userdata":{"fullname":"shivareddy"},"ans_count":0,"like":"0","unlike":"0","avatar":"14.png"},{"id":"127","userid":"52","question":"what is java node?","date":"2018-10-04 04:59:57","youtube_uniqid":"https://www.youtube.com/embed/5rYZ7JTBJGg","testname":"Test 1","userdata":{"fullname":"shivareddy"},"ans_count":0,"like":"0","unlike":"0","avatar":"8.png"},{"id":"128","userid":"52","question":"what is java","date":"2018-10-04 05:02:52","youtube_uniqid":"https://www.youtube.com/embed/5rYZ7JTBJGg","testname":"Test 1","userdata":{"fullname":"shivareddy"},"ans_count":0,"like":"0","unlike":"0","avatar":"2.png"},{"id":"129","userid":"52","question":"what is android","date":"2018-10-04 05:31:38","youtube_uniqid":"https://www.youtube.com/embed/5rYZ7JTBJGg","testname":"Test 1","userdata":{"fullname":"shivareddy"},"ans_count":0,"like":"0","unlike":"0","avatar":"20.png"},{"id":"130","userid":"52","question":"hiii","date":"2018-10-04 05:35:39","youtube_uniqid":"https://www.youtube.com/embed/5rYZ7JTBJGg","testname":"Test 1","userdata":{"fullname":"shivareddy"},"ans_count":0,"like":"0","unlike":"0","avatar":"8.png"},{"id":"131","userid":"52","question":"","date":"2018-10-04 05:35:47","youtube_uniqid":"https://www.youtube.com/embed/5rYZ7JTBJGg","testname":"Test 1","userdata":{"fullname":"shivareddy"},"ans_count":0,"like":"0","unlike":"0","avatar":"14.png"},{"id":"132","userid":"52","question":"","date":"2018-10-04 05:35:49","youtube_uniqid":"https://www.youtube.com/embed/5rYZ7JTBJGg","testname":"Test 1","userdata":{"fullname":"shivareddy"},"ans_count":0,"like":"0","unlike":"0","avatar":"11.png"},{"id":"133","userid":"52","question":"what is android ","date":"2018-10-04 08:53:02","youtube_uniqid":"https://www.youtube.com/embed/5rYZ7JTBJGg","testname":"Test 1","userdata":{"fullname":"shivareddy"},"ans_count":0,"like":"0","unlike":"0","avatar":"2.png"}]
     */

    private String video_link;
    private ArrayList<QuestionsBean> questions;

    public String getVideo_link() {
        return video_link;
    }

    public void setVideo_link(String video_link) {
        this.video_link = video_link;
    }

    public ArrayList<QuestionsBean> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<QuestionsBean> questions) {
        this.questions = questions;
    }

    public static class QuestionsBean {
        /**
         * id : 37
         * userid : 51
         * question : what is angularjs?
         * date : 2018-08-06 16:01:42
         * youtube_uniqid : https://www.youtube.com/embed/5rYZ7JTBJGg
         * testname : Test 1
         * userdata : {"fullname":"revanth enumula"}
         * ans_count : 2
         * like : 2
         * unlike : 0
         * avatar : 5.png
         */

        private String id;
        private String userid;
        private String question;
        private String date;
        private String youtube_uniqid;
        private String testname;
        private UserdataBean userdata;
        private int ans_count;
        private int like;
        private int unlike;
        private String avatar;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getYoutube_uniqid() {
            return youtube_uniqid;
        }

        public void setYoutube_uniqid(String youtube_uniqid) {
            this.youtube_uniqid = youtube_uniqid;
        }

        public String getTestname() {
            return testname;
        }

        public void setTestname(String testname) {
            this.testname = testname;
        }

        public UserdataBean getUserdata() {
            return userdata;
        }

        public void setUserdata(UserdataBean userdata) {
            this.userdata = userdata;
        }

        public int getAns_count() {
            return ans_count;
        }

        public void setAns_count(int ans_count) {
            this.ans_count = ans_count;
        }

        public int getLike() {
            return like;
        }

        public void setLike(int like) {
            this.like = like;
        }

        public int getUnlike() {
            return unlike;
        }

        public void setUnlike(int unlike) {
            this.unlike = unlike;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }



        public static class UserdataBean {
            /**
             * fullname : revanth enumula
             */

            private String fullname;

            public String getFullname() {
                return fullname;
            }

            public void setFullname(String fullname) {
                this.fullname = fullname;
            }
        }

        public static Comparator<QuestionsBean> likeComparator = new Comparator<QuestionsBean>() {
            @Override
            public int compare(QuestionsBean jc1, QuestionsBean jc2) {
                return (jc2.getLike() < jc1.getLike() ? -1 :
                        (jc2.getLike() == jc1.getLike() ? 0 : 1));
            }
        };

    }
}
