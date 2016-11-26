package net.strangled.maladan;

public class Banned {
    private String song1;
    private String song2;

    public Banned(String song1, String song2) {
        if (song1 != null) {
            this.song1 = song1;
        }
        if (song2 != null) {
            this.song2 = song2;
        }
    }

    public String getSong1() {
        return new String(this.song1);
    }

    public String getSong2() {
        return new String(this.song2);
    }
}

