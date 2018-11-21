package in.texasreview.gre.utils;

import java.util.ArrayList;
import java.util.Collections;

import in.texasreview.gre.Models.CreatedQuestionsModel;

/**
 * Created by User on 25-10-2018.
 */

public class QusLikesSorter {

    ArrayList<CreatedQuestionsModel.QuestionsBean> questionsBeans = new ArrayList<>();

    public QusLikesSorter(ArrayList<CreatedQuestionsModel.QuestionsBean> questionsBeans) {
        this.questionsBeans = questionsBeans;
    }

    public ArrayList<CreatedQuestionsModel.QuestionsBean> getSortedByLike() {
        Collections.sort(questionsBeans, CreatedQuestionsModel.QuestionsBean.likeComparator);
        return questionsBeans;
    }

}
