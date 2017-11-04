package net.strangled.maladan;


class TeamMembers {

    private String redTeamMembers;
    private String blueTeamMembers;

    TeamMembers(String redTeamMembers, String blueTeamMembers) {
        this.redTeamMembers = redTeamMembers;
        this.blueTeamMembers = blueTeamMembers;
    }

    String getRedTeamMembers() {
        return redTeamMembers;
    }

    String getBlueTeamMembers() {
        return blueTeamMembers;
    }
}
