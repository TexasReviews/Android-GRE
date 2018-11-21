package in.texasreview.gre.Models;

import java.util.ArrayList;

/**
 * Created by User on 16-10-2018.
 */

public class QuestionsModel {

    private String id;
    private String noofblanks;
    private String trackid;
    private String category;
    private String paragraph;
    private String qusname;
    private String question_type_id;
    private String note;
    private String solution;
    private String status;

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

    public String getQusname() {
        return qusname;
    }

    public void setQusname(String qusname) {
        this.qusname = qusname;
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

    public ArrayList<OptionsModel> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<OptionsModel> options) {
        this.options = options;
    }

    private ArrayList<OptionsModel> options;
}
