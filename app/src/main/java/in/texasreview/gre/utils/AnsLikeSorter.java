package in.texasreview.gre.utils;

import java.util.ArrayList;
import java.util.Collections;

import in.texasreview.gre.Models.AnswersModel;

/**
 * Created by User on 25-10-2018.
 */

public class AnsLikeSorter {

    ArrayList<AnswersModel.AnsBean> ansBeans = new ArrayList<>();

    public AnsLikeSorter(ArrayList<AnswersModel.AnsBean> ansBeans) {
        this.ansBeans = ansBeans;
    }

    public ArrayList<AnswersModel.AnsBean> getSortedByLike() {
        Collections.sort(ansBeans, AnswersModel.AnsBean.ansLikeComparator);
        return ansBeans;
    }
}
