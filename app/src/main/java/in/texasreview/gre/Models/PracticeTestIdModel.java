package in.texasreview.gre.Models;

import java.util.List;

import io.realm.RealmModel;
import io.realm.RealmObject;

/**
 * Created by User on 25-10-2018.
 */

public class PracticeTestIdModel {

    private String response;
    private String mesg;
    private List<DataBean> data;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getMesg() {
        return mesg;
    }

    public void setMesg(String mesg) {
        this.mesg = mesg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 165
         * categoryid : Verbal
         * lessonid :
         * questiontype : Critical Reasoning
         * questionids : ["917699","530636"]
         * status : 1001
         * subcategories : [{"id":"165","categoryid":"Verbal","lessonid":"","questiontype":"Critical Reasoning","questionids":["917699","530636"],"status":"1001"}]
         * totalQuationsInSec : 2
         */

        private String id;
        private String categoryid;
        private String lessonid;
        private String questiontype;
        private String status;
        private int totalQuationsInSec;
        private List<String> questionids;
        private List<SubcategoriesBean> subcategories;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCategoryid() {
            return categoryid;
        }

        public void setCategoryid(String categoryid) {
            this.categoryid = categoryid;
        }

        public String getLessonid() {
            return lessonid;
        }

        public void setLessonid(String lessonid) {
            this.lessonid = lessonid;
        }

        public String getQuestiontype() {
            return questiontype;
        }

        public void setQuestiontype(String questiontype) {
            this.questiontype = questiontype;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getTotalQuationsInSec() {
            return totalQuationsInSec;
        }

        public void setTotalQuationsInSec(int totalQuationsInSec) {
            this.totalQuationsInSec = totalQuationsInSec;
        }

        public List<String> getQuestionids() {
            return questionids;
        }

        public void setQuestionids(List<String> questionids) {
            this.questionids = questionids;
        }

        public List<SubcategoriesBean> getSubcategories() {
            return subcategories;
        }

        public void setSubcategories(List<SubcategoriesBean> subcategories) {
            this.subcategories = subcategories;
        }

        public static class SubcategoriesBean {
            /**
             * id : 165
             * categoryid : Verbal
             * lessonid :
             * questiontype : Critical Reasoning
             * questionids : ["917699","530636"]
             * status : 1001
             */

            private String id;
            private String categoryid;
            private String lessonid;
            private String questiontype;
            private String status;
            private List<String> questionids;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCategoryid() {
                return categoryid;
            }

            public void setCategoryid(String categoryid) {
                this.categoryid = categoryid;
            }

            public String getLessonid() {
                return lessonid;
            }

            public void setLessonid(String lessonid) {
                this.lessonid = lessonid;
            }

            public String getQuestiontype() {
                return questiontype;
            }

            public void setQuestiontype(String questiontype) {
                this.questiontype = questiontype;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public List<String> getQuestionids() {
                return questionids;
            }

            public void setQuestionids(List<String> questionids) {
                this.questionids = questionids;
            }
        }
    }
}
