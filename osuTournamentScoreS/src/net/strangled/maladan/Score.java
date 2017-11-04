package net.strangled.maladan;

class Score {

    //team scores
    private int redScore = 0;
    private int blueScore = 0;

    void redScorePlusOne() {
        redScore++;
    }

    void blueScorePlusOne() {
        blueScore++;
    }

    int getRedScore() {
        return redScore;
    }

    int getBlueScore() {
        return blueScore;
    }
}
