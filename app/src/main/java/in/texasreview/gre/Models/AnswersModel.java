package in.texasreview.gre.Models;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by User on 17-10-2018.
 */

public class AnswersModel {


    /**
     * quetion : what is angularjs?
     * qid : 37
     * userdata : {"fullname":"revanth enumula"}
     * qdate : 2018-08-06 16:01:42
     * like : 0
     * unlike : 2
     * qavatar : 1.png
     * ans : [{"id":"79","question":"what is angularjs?","qdate":"2018-08-06 16:01:42","adate":"2018-08-06 16:02:17","auser":"51","quser":"51","ans":"angularjs is a frotend languagae","userdata":{"fullname":"revanth enumula"},"like":"2","unlike":"0","avatar":"12.png"},{"id":"80","question":"what is angularjs?","qdate":"2018-08-06 16:01:42","adate":"2018-08-09 18:08:47","auser":"51","quser":"51","ans":"hhhh","userdata":{"fullname":"revanth enumula"},"like":"1","unlike":"1","avatar":"20.png"},{"id":"84","question":"what is angularjs?","qdate":"2018-08-06 16:01:42","adate":"2018-10-04 09:44:03","auser":"51","quser":"51","ans":"angular js is best launguage","userdata":{"fullname":"revanth enumula"},"like":"0","unlike":"0","avatar":"4.png"},{"id":"85","question":"what is angularjs?","qdate":"2018-08-06 16:01:42","adate":"2018-10-08 06:35:50","auser":"53","quser":"51","ans":"i don't have any idea regarding angularjs","userdata":{"fullname":"rakesh"},"like":"2","unlike":"0","avatar":"13.png"},{"id":"87","question":"what is angularjs?","qdate":"2018-08-06 16:01:42","adate":"2018-10-16 12:11:15","auser":"51","quser":"51","ans":"sss","userdata":{"fullname":"revanth enumula"},"like":"0","unlike":"0","avatar":"3.png"}]
     */

    private String quetion;
    private String qid;
    private UserdataBean userdata;
    private String qdate;
    private int like;
    private int unlike;
    private String qavatar;
    private ArrayList<AnsBean> ans;

    public String getQuetion() {
        return quetion;
    }

    public void setQuetion(String quetion) {
        this.quetion = quetion;
    }

    public String getQid() {
        return qid;
    }

    public void setQid(String qid) {
        this.qid = qid;
    }

    public UserdataBean getUserdata() {
        return userdata;
    }

    public void setUserdata(UserdataBean userdata) {
        this.userdata = userdata;
    }

    public String getQdate() {
        return qdate;
    }

    public void setQdate(String qdate) {
        this.qdate = qdate;
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

    public String getQavatar() {
        return qavatar;
    }

    public void setQavatar(String qavatar) {
        this.qavatar = qavatar;
    }

    public ArrayList<AnsBean> getAns() {
        return ans;
    }

    public void setAns(ArrayList<AnsBean> ans) {
        this.ans = ans;
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

    public static class AnsBean {
        /**
         * id : 79
         * question : what is angularjs?
         * qdate : 2018-08-06 16:01:42
         * adate : 2018-08-06 16:02:17
         * auser : 51
         * quser : 51
         * ans : angularjs is a frotend languagae
         * userdata : {"fullname":"revanth enumula"}
         * like : 2
         * unlike : 0
         * avatar : 12.png
         */

        private String id;
        private String question;
        private String qdate;
        private String adate;
        private String auser;
        private String quser;
        private String ans;
        private UserdataBeanX userdata;
        private int like;
        private int unlike;
        private String avatar;


        public static Comparator<AnsBean> ansLikeComparator = new Comparator<AnsBean>() {
            @Override
            public int compare(AnsBean jc1, AnsBean jc2) {
                return (jc2.getLike() < jc1.getLike() ? -1 :
                        (jc2.getLike() == jc1.getLike() ? 0 : 1));
            }
        };


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public String getQdate() {
            return qdate;
        }

        public void setQdate(String qdate) {
            this.qdate = qdate;
        }

        public String getAdate() {
            return adate;
        }

        public void setAdate(String adate) {
            this.adate = adate;
        }

        public String getAuser() {
            return auser;
        }

        public void setAuser(String auser) {
            this.auser = auser;
        }

        public String getQuser() {
            return quser;
        }

        public void setQuser(String quser) {
            this.quser = quser;
        }

        public String getAns() {
            return ans;
        }

        public void setAns(String ans) {
            this.ans = ans;
        }

        public UserdataBeanX getUserdata() {
            return userdata;
        }

        public void setUserdata(UserdataBeanX userdata) {
            this.userdata = userdata;
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

        public static class UserdataBeanX {
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
    }
}
