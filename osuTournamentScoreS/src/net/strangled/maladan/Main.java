package net.strangled.maladan;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("osu! Tournament Scoring System");

        while (true) {
            TeamMembers members = getTeamMembers(input);
            RollOrder rollOrder = getRollOrder(input);
            Banned bans = getBannedMaps(rollOrder, input);
            Score score = new Score();
            List<Round> rounds = new ArrayList<>();
            boolean matchComplete = false;

            while (!matchComplete) {
                Round thisRound = executeRound(input);
                rounds.add(thisRound);

                if (thisRound.getRoundWinner().equals("red")) {
                    score.redScorePlusOne();
                } else {
                    score.blueScorePlusOne();
                }

                int redScore = score.getRedScore();
                int blueScore = score.getBlueScore();

                if (redScore + blueScore == 10) {
                    System.out.println("Tiebreaker Round!");
                }

                if (redScore == 6 && blueScore <= 5 || blueScore == 6 && redScore <= 5) {
                    matchComplete = true;
                }
            }
            writeFile(bans, score, rounds, members);

            System.out.print("The Round has been Completed. Play another round?(y/n): ");
            String userResponse = input.nextLine().toLowerCase().trim();

            if (!userResponse.equals("y")) {
                break;
            }
        }
        System.out.println("See you next time!");
    }

    private static TeamMembers getTeamMembers(Scanner input) {
        System.out.print("Enter the members of team red separated by commas: ");
        String redTeamMembers = formatSeparatedString(input.nextLine().trim());

        System.out.print("Enter the members of team blue separated by commas: ");
        String blueTeamMembers = formatSeparatedString(input.nextLine().trim());

        return new TeamMembers(redTeamMembers, blueTeamMembers);
    }

    private static String formatSeparatedString(String unformatted) {
        StringBuilder formattedString = new StringBuilder();

        for (char c : unformatted.toCharArray()) {
            if (c != ',') {
                formattedString.append(c);
            } else {
                formattedString.append(c).append(" ");
            }
        }
        return formattedString.toString();
    }

    private static Round executeRound(Scanner input) {
        String mapName = getMapToBePlayed(input);

        while (true) {
            System.out.print("Enter the winning team name: ");
            String winner = input.nextLine().toLowerCase();

            switch (winner) {
                case "red":
                    return new Round(mapName, "red");

                case "blue":
                    return new Round(mapName, "blue");

                default:
                    System.out.println("Invalid data, please try again.");
                    break;
            }
        }
    }

    private static String getMapToBePlayed(Scanner input) {
        while (true) {
            System.out.print("Enter the name of the map to be played: ");
            String mapName = input.nextLine().toLowerCase();

            if (!mapName.trim().isEmpty()) {
                return mapName;
            }
            System.out.println("You can't play an empty map!");
        }
    }

    private static RollOrder getRollOrder(Scanner input) {
        while (true) {
            System.out.print("Enter the name of the team that won the roll: ");

            String firstTeam = input.nextLine().toLowerCase().trim();

            if (firstTeam.equals("red")) {
                return new RollOrder(firstTeam, "blue");

            } else if (firstTeam.equals("blue")) {
                return new RollOrder(firstTeam, "red");
            }
            System.out.println("Invalid team entered.");
        }
    }

    private static Banned getBannedMaps(RollOrder rollOrder, Scanner input) {
        boolean validBans = false;
        String firstBan = "";
        String secondBan = "";

        while (!validBans) {
            System.out.print("Enter " + rollOrder.getFirstUser() + "'s map ban: ");
            firstBan = input.nextLine();

            System.out.print("Enter " + rollOrder.getSecondUser() + "'s map ban: ");
            secondBan = input.nextLine();

            if (!firstBan.trim().equals("") && !secondBan.trim().equals("")) {
                validBans = true;

            } else {
                System.out.println("Invalid Bans.");
            }
        }
        return new Banned(firstBan, secondBan);
    }

    private static void writeFile(Banned bannedMaps, Score finalScore, List<Round> rounds, TeamMembers members) {
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yy HH-mm-ss");

        Date theDate = new Date();
        int redScore = finalScore.getRedScore();
        int blueScore = finalScore.getBlueScore();

        try (PrintWriter writer = new PrintWriter("Scores" + File.separator +
                df.format(theDate) + " Red- " + redScore + " Blue- " + blueScore + ".txt", StandardCharsets.UTF_8)) {

            writer.println("Teams:");

            writer.println("Red Team Score: " + redScore);
            writer.println("Blue Team Score: " + blueScore);

            writer.println("Red Team Members: " + members.getRedTeamMembers());
            writer.println("Blue Team Members: " + members.getBlueTeamMembers());

            for (Round round : rounds) {
                writer.println("Round: " + rounds.indexOf(round));
                writer.println("     Map: " + round.getSongName());
                writer.println("     Winner: " + round.getRoundWinner());
            }

            writer.println("Banned Maps:");
            writer.println("     " + bannedMaps.getSong1());
            writer.println("     " + bannedMaps.getSong2());

        } catch (FileNotFoundException e) {
            System.err.println("File not Found.");

        } catch (UnsupportedEncodingException e) {
            System.err.println("Unsupported Encoding.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
