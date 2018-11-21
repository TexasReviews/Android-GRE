package in.texasreview.gre.Models;

/**
 * Created by User on 12-10-2018.
 */

public class UserDetails {


    /**
     * email : admin@gmail.com
     * fullname : revanth enumula
     * id : 51
     * phonenumber : 9848086230
     * branch : Begumpet
     * status : 1001
     * acttype : paid
     */

    private String email;
    private String fullname;
    private String id;
    private String phonenumber;
    private String branch;
    private String status;
    private String acttype;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getActtype() {
        return acttype;
    }

    public void setActtype(String acttype) {
        this.acttype = acttype;
    }
}
