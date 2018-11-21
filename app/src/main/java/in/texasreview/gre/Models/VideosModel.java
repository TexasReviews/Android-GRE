package in.texasreview.gre.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class VideosModel implements Parcelable{


    /**
     * id : 158
     * title : Geometry
     * testid : 533008
     * trackid : 900425
     * status : 1001
     * total_vedios : 6
     * total_test_que : 30
     */

    private String id;
    private String title;
    private String testid;
    private String trackid;
    private String status;
    private int total_vedios;
    private int total_test_que;

    protected VideosModel(Parcel in) {
        id = in.readString();
        title = in.readString();
        testid = in.readString();
        trackid = in.readString();
        status = in.readString();
        total_vedios = in.readInt();
        total_test_que = in.readInt();
    }

    public static final Creator<VideosModel> CREATOR = new Creator<VideosModel>() {
        @Override
        public VideosModel createFromParcel(Parcel in) {
            return new VideosModel(in);
        }

        @Override
        public VideosModel[] newArray(int size) {
            return new VideosModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(title);
        parcel.writeString(testid);
        parcel.writeString(trackid);
        parcel.writeString(status);
        parcel.writeInt(total_vedios);
        parcel.writeInt(total_test_que);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTestid() {
        return testid;
    }

    public void setTestid(String testid) {
        this.testid = testid;
    }

    public String getTrackid() {
        return trackid;
    }

    public void setTrackid(String trackid) {
        this.trackid = trackid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotal_vedios() {
        return total_vedios;
    }

    public void setTotal_vedios(int total_vedios) {
        this.total_vedios = total_vedios;
    }

    public int getTotal_test_que() {
        return total_test_que;
    }

    public void setTotal_test_que(int total_test_que) {
        this.total_test_que = total_test_que;
    }
}
