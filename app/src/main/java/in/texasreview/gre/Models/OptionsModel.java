package in.texasreview.gre.Models;

/**
 * Created by User on 16-10-2018.
 */

public class OptionsModel {

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
