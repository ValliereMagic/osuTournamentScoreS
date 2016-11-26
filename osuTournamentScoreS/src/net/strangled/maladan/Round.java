package net.strangled.maladan;

public class Round {
    private int roundNumber;
    private String songName;
    private String winner;

    public Round(int roundNumber, String songName, String winner) {
        if (roundNumber > 0) {
            this.roundNumber = roundNumber;
        }
        if (songName != null) {
            this.songName = songName;
        }
        if (winner != null) {
            this.winner = winner;
        }
    }

    public String getSongName() {
        return new String(this.songName);
    }

    public String getRoundWinner() {
        return new String(this.winner);
    }

    public int getRoundNumber() {
        return this.roundNumber;
    }
}

