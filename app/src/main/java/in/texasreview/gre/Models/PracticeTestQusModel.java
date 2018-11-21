package in.texasreview.gre.Models;

import java.util.List;

/**
 * Created by User on 26-10-2018.
 */

public class PracticeTestQusModel {


    /**
     * response : success
     * mesg :
     * data : {"qua":{"id":"1489","noofblanks":"0","trackid":"725798","category":"Quant","paragraph":"","name":"<p>The median age of five people in a room is 30 years.  One of them, aged 50, leaves the room.  The median age of the remaining individuals<\/p>","question_type_id":"1","note":"Test3-Sec2-Q12","solution":"<p>The correct answer is (E). &nbsp;The median is the middle value of a group of data, listed in ascending (or descending) order. &nbsp;The people in the room could, therefore, be 30, 30, 30, 30, and 50, or the group could have ages &nbsp;10, 20, 30, 40, and 50.  In either case, the median is 30 years.  You can see that when the 50-year-old individual leaves the room, the first median would be 30, but the second median would be (20 + 30)/2 = 25. &nbsp;Thus, the answer is not a specific value and cannot be determined, choice E.<\/p>","status":"1001","is_attend":true,"options":[{"id":"3119","question_id":"725798","name":"<p> is 20<\/p>","is_answer":"0","user_aws":1},{"id":"3120","question_id":"725798","name":"<p> is 25<\/p>","is_answer":"0","user_aws":1},{"id":"3121","question_id":"725798","name":"<p> is 30<\/p>","is_answer":"0","user_aws":""},{"id":"3122","question_id":"725798","name":"<p> is 40<\/p>","is_answer":"0","user_aws":""},{"id":"3123","question_id":"725798","name":"<p>cannot be determined<\/p>","is_answer":"1","user_aws":1}],"result":true},"is_attend":true}
     */

    private String response;
    private String mesg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * qua : {"id":"1489","noofblanks":"0","trackid":"725798","category":"Quant","paragraph":"","name":"<p>The median age of five people in a room is 30 years.  One of them, aged 50, leaves the room.  The median age of the remaining individuals<\/p>","question_type_id":"1","note":"Test3-Sec2-Q12","solution":"<p>The correct answer is (E). &nbsp;The median is the middle value of a group of data, listed in ascending (or descending) order. &nbsp;The people in the room could, therefore, be 30, 30, 30, 30, and 50, or the group could have ages &nbsp;10, 20, 30, 40, and 50.  In either case, the median is 30 years.  You can see that when the 50-year-old individual leaves the room, the first median would be 30, but the second median would be (20 + 30)/2 = 25. &nbsp;Thus, the answer is not a specific value and cannot be determined, choice E.<\/p>","status":"1001","is_attend":true,"options":[{"id":"3119","question_id":"725798","name":"<p> is 20<\/p>","is_answer":"0","user_aws":1},{"id":"3120","question_id":"725798","name":"<p> is 25<\/p>","is_answer":"0","user_aws":1},{"id":"3121","question_id":"725798","name":"<p> is 30<\/p>","is_answer":"0","user_aws":""},{"id":"3122","question_id":"725798","name":"<p> is 40<\/p>","is_answer":"0","user_aws":""},{"id":"3123","question_id":"725798","name":"<p>cannot be determined<\/p>","is_answer":"1","user_aws":1}],"result":true}
         * is_attend : true
         */

        private QuaBean qua;

        private boolean is_attend;

        public QuaBean getQua() {
            return qua;
        }

        public void setQua(QuaBean qua) {
            this.qua = qua;
        }

        public boolean isIs_attend() {
            return is_attend;
        }

        public void setIs_attend(boolean is_attend) {
            this.is_attend = is_attend;
        }

        public static class QuaBean {
            /**
             * id : 1489
             * noofblanks : 0
             * trackid : 725798
             * category : Quant
             * paragraph :
             * name : <p>The median age of five people in a room is 30 years.  One of them, aged 50, leaves the room.  The median age of the remaining individuals</p>
             * question_type_id : 1
             * note : Test3-Sec2-Q12
             * solution : <p>The correct answer is (E). &nbsp;The median is the middle value of a group of data, listed in ascending (or descending) order. &nbsp;The people in the room could, therefore, be 30, 30, 30, 30, and 50, or the group could have ages &nbsp;10, 20, 30, 40, and 50.  In either case, the median is 30 years.  You can see that when the 50-year-old individual leaves the room, the first median would be 30, but the second median would be (20 + 30)/2 = 25. &nbsp;Thus, the answer is not a specific value and cannot be determined, choice E.</p>
             * status : 1001
             * is_attend : true
             * options : [{"id":"3119","question_id":"725798","name":"<p> is 20<\/p>","is_answer":"0","user_aws":1},{"id":"3120","question_id":"725798","name":"<p> is 25<\/p>","is_answer":"0","user_aws":1},{"id":"3121","question_id":"725798","name":"<p> is 30<\/p>","is_answer":"0","user_aws":""},{"id":"3122","question_id":"725798","name":"<p> is 40<\/p>","is_answer":"0","user_aws":""},{"id":"3123","question_id":"725798","name":"<p>cannot be determined<\/p>","is_answer":"1","user_aws":1}]
             * result : true
             */

            private String id;
            private String noofblanks;
            private String trackid;
            private String category;
            private String paragraph;
            private String name;
            private String question_type_id;
            private String note;
            private String solution;
            private String status;
            private boolean is_attend;
            private boolean result;
            private List<OptionsBean> options;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getNoofblanks() {
                return noofblanks;
            }

            public void setNoofblanks(String noofblanks) {
                this.noofblanks = noofblanks;
            }

            public String getTrackid() {
                return trackid;
            }

            public void setTrackid(String trackid) {
                this.trackid = trackid;
            }

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public String getParagraph() {
                return paragraph;
            }

            public void setParagraph(String paragraph) {
                this.paragraph = paragraph;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getQuestion_type_id() {
                return question_type_id;
            }

            public void setQuestion_type_id(String question_type_id) {
                this.question_type_id = question_type_id;
            }

            public String getNote() {
                return note;
            }

            public void setNote(String note) {
                this.note = note;
            }

            public String getSolution() {
                return solution;
            }

            public void setSolution(String solution) {
                this.solution = solution;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public boolean isIs_attend() {
                return is_attend;
            }

            public void setIs_attend(boolean is_attend) {
                this.is_attend = is_attend;
            }

            public boolean isResult() {
                return result;
            }

            public void setResult(boolean result) {
                this.result = result;
            }

            public List<OptionsBean> getOptions() {
                return options;
            }

            public void setOptions(List<OptionsBean> options) {
                this.options = options;
            }

            public static class OptionsBean {
                /**
                 * id : 3119
                 * question_id : 725798
                 * name : <p> is 20</p>
                 * is_answer : 0
                 * user_aws : 1
                 */

                private String id;
                private String question_id;
                private String name;
                private String is_answer;
                private String user_aws;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getQuestion_id() {
                    return question_id;
                }

                public void setQuestion_id(String question_id) {
                    this.question_id = question_id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getIs_answer() {
                    return is_answer;
                }

                public void setIs_answer(String is_answer) {
                    this.is_answer = is_answer;
                }

                public String getUser_aws() {
                    return user_aws;
                }

                public void setUser_aws(String user_aws) {
                    this.user_aws = user_aws;
                }
            }
        }
    }
}
