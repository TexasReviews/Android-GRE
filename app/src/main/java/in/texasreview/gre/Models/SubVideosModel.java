package in.texasreview.gre.Models;

public class SubVideosModel {


    /**
     * id : 1
     * titlelink : Video 1
     * youtube_uniqid : https://www.youtube.com/embed/5rYZ7JTBJGg
     * testid : 738694
     * catid : 900425
     * testname : Test 1
     * status : 1001
     * is_open : true
     */

    private String id;
    private String titlelink;
    private String youtube_uniqid;
    private String testid;
    private String catid;
    private String testname;
    private String status;
    private boolean is_open;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitlelink() {
        return titlelink;
    }

    public void setTitlelink(String titlelink) {
        this.titlelink = titlelink;
    }

    public String getYoutube_uniqid() {
        return youtube_uniqid;
    }

    public void setYoutube_uniqid(String youtube_uniqid) {
        this.youtube_uniqid = youtube_uniqid;
    }

    public String getTestid() {
        return testid;
    }

    public void setTestid(String testid) {
        this.testid = testid;
    }

    public String getCatid() {
        return catid;
    }

    public void setCatid(String catid) {
        this.catid = catid;
    }

    public String getTestname() {
        return testname;
    }

    public void setTestname(String testname) {
        this.testname = testname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isIs_open() {
        return is_open;
    }

    public void setIs_open(boolean is_open) {
        this.is_open = is_open;
    }
}
