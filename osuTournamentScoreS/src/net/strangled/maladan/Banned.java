package net.strangled.maladan;

class Banned {
    private String song1;
    private String song2;

    Banned(String song1, String song2) {
        if (song1 != null) {
            this.song1 = song1;
        }
        if (song2 != null) {
            this.song2 = song2;
        }
    }

    String getSong1() {
        return this.song1;
    }

    String getSong2() {
        return this.song2;
    }
}

