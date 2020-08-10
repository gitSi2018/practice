package om.si.practice.algorithm.order;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

@Slf4j
public class OtherOrder {

    public static int[]  generateScores(int size, int maxScore){

        long startTime  = System.currentTimeMillis();
        int[] scores = new int[size];
        for (int i = 0; i < size; i++){
            scores[i] = (int)(Math.random() * maxScore) + (int)Math.round(Math.random());
        }
        log.info("generateScores run time:{}", System.currentTimeMillis() - startTime);
        return scores;
    }

    public static void main(String... args){

        int maxScore = 750;
        int[] scores = generateScores(100000, maxScore);
//        int[] sort1 = orderTotalScore(scores, maxScore);

        int[] querySort = queryOrder(scores);

        int[] jiShuSort = jiShuSort(scores, maxScore);

//        StringBuilder sort1Result = new StringBuilder();
        StringBuilder querySortResult = new StringBuilder();
        StringBuilder jiShuSortResult = new StringBuilder();
        for (int i = 0; i < 100; i++){
//            sort1Result.append(sort1[i]).append(":");
            querySortResult.append(querySort[i]).append(":");
            jiShuSortResult.append(jiShuSort[i]).append(":");
        }
//        log.info("    sort1Result:{}", sort1Result.toString());
        log.info("querySortResult:{}", querySortResult.toString());
        log.info("jiShuSortResult:{}", jiShuSortResult.toString());

    }

    public static int[] jiShuSort(int[] scores, int maxScore){
        Long startTime = System.currentTimeMillis();

        int[] scoreJiShu = new int[maxScore + 1];
//        for (int i = 0; i < scoreJiShu.length; i++){
//
//            scoreJiShu[i] = -1;
//        }
        for (int i = 0; i < scores.length; i++){

            int scoreIndex = scores[i];
            scoreJiShu[scoreIndex] += 1;
        }

        int[] sortedScores = new int[scores.length];
        int index = 0;
        for (int i = 0; i < scoreJiShu.length; i++){

            for (int j = 0; j < scoreJiShu[i]; j++){
                sortedScores[index++] = i;
            }
        }
        log.info("jiShuSort run time:{}", System.currentTimeMillis() - startTime);
        return sortedScores;
    }

    public static int[] orderTotalScore(int[] scores, int maxScore){

        Long startTime = System.currentTimeMillis();
//        LinkedList[] allScores = new LinkedList[751];
        LinkScore[] allScores = new LinkScore[maxScore + 1];
//        ArrayList<LinkedList<Integer>> allScores = new ArrayList<>(750);
        for (int i = 0; i < scores.length; i++){

            int scoreIndex = scores[i];
//            LinkedList specifySores = allScores[scoreIndex];
            LinkScore specifySores = allScores[scoreIndex];
            if (specifySores == null){
//                specifySores = new LinkedList<>();
                specifySores = new LinkScore();
                specifySores.add(scores[i]);
                allScores[scoreIndex] = specifySores;
                continue;
            }
            specifySores.add(scores[i]);
        }
        Long endTime = System.currentTimeMillis();
        int[] lastScore = new int[scores.length];
        int index = 0;
        for(int i = 0; i < allScores.length; i++){
            LinkScore indexIScores = allScores[i];
            if (indexIScores == null || indexIScores.getHead() == null) continue;
            LinkScore next = indexIScores.getHead();
            while ((next = next.getNext()) != null){
                lastScore[index++] = next.getScore();
            }
        }
        log.info("orderTotalScore run time:{}", endTime - startTime);
        return lastScore;
    }


    public static int[] queryOrder(int[] scores){

        Long startTime = System.currentTimeMillis();
        Arrays.sort(scores);
        Long endTime = System.currentTimeMillis();
        log.info("queryOrder run time:{}", endTime - startTime);
        return scores;
    }
}

@Data
class LinkScore{


    private int score;

    private LinkScore next;

    private LinkScore head;

    private LinkScore tail;

    public LinkScore(){
        this.head = this;
//        LinkScore newNode = new LinkScore(score);
        this.next = this;
        this.tail = this;
    }
    private LinkScore(int score){

        this.score = score;
        this.next = null;
        this.head = null;
        this.tail = null;
    }

    public void add(int score){

        LinkScore newNode = new LinkScore(score);
        this.tail.next = newNode;
        this.tail = newNode;
    }

    @Override
    public String toString(){

        return score + ":";
    }

}