package in.texasreview.gre.Models;

/**
 * Created by User on 15-11-2018.
 */

public class GreScoreModel {

    public String getTestname() {
        return testname;
    }

    public void setTestname(String testname) {
        this.testname = testname;
    }

    public String getVerbal() {
        return verbal;
    }

    public void setVerbal(String verbal) {
        this.verbal = verbal;
    }

    public String getQuant() {
        return quant;
    }

    public void setQuant(String quant) {
        this.quant = quant;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    private String testname;
    private String verbal;
    private String quant;
    private String total;
    private String status;
    private String score;

}
