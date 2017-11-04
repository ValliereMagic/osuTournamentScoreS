package net.strangled.maladan;

class Round {
    private String songName;
    private String winner;

    Round(String songName, String winner) {
        if (songName != null) {
            this.songName = songName;
        }
        if (winner != null) {
            this.winner = winner;
        }
    }

    String getSongName() {
        return this.songName;
    }

    String getRoundWinner() {
        return this.winner;
    }
}

