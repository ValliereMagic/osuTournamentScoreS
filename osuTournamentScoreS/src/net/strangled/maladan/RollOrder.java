package net.strangled.maladan;

class RollOrder {
    //team order
    private String firstUser;
    private String secondUser;

    RollOrder(String firstUser, String secondUser) {
        this.firstUser = firstUser;
        this.secondUser = secondUser;
    }

    String getFirstUser() {
        return firstUser;
    }

    String getSecondUser() {
        return secondUser;
    }
}
