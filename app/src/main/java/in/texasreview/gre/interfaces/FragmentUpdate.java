package in.texasreview.gre.interfaces;

import java.util.ArrayList;

import in.texasreview.gre.Models.VideosModel;

public interface FragmentUpdate {

    void onPositionClick(String trackId);
    void onVideoClick(String url,String subCatId,String catId);
    void onTestClick(String testid,String videoId);
    void onPracticetestClick(ArrayList<String> idsList,String session);
    void onSubmitClick();
}
