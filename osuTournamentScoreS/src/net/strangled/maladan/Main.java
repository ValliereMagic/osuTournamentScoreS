package net.strangled.maladan;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("osu! Tournament Scoring System");
        ArrayList<Round> rounds = new ArrayList<Round>();
        String secondPick = null;
        String firstPick = null;
        boolean teamCorrect = false;
        while (!teamCorrect) {
            System.out.println("Enter the team name that won the roll. (red/blue)");
            firstPick = input.nextLine();
            if(firstPick.toLowerCase().equals("red")){
                secondPick = "blue";
                teamCorrect = true;
            }else if(firstPick.toLowerCase().equals("blue")){
                secondPick = "red";
                teamCorrect = true;
            }else{
            	firstPick = null;
            	System.out.println("Invalid teamname entered.");
            }
        }
        boolean validBans = false;
        String firstBan = null;
        String secondBan = null;
        while (!validBans) {
            System.out.println("Enter " + firstPick + "'s map ban.");
            firstBan = input.nextLine();
            System.out.println("Enter " + secondPick + "'s map ban.");
            secondBan = input.nextLine();
            if (!firstBan.equals("") && !secondBan.equals("")) {
                validBans = true;
            }else{
            	System.out.println("Invalid Bans.");
            }
        }
        Banned banned = new Banned(firstBan, secondBan);
        boolean teamWin = false;
        int redScore = 0;
        int blueScore = 0;
        int roundNumber = 1;
        while (!teamWin) {
            if (roundNumber % 2 == 0) {
                System.out.println("Enter " + secondPick + "'s map pick");
            } else {
                System.out.println("Enter " + firstPick + "'s map pick");
            }
            String map = input.nextLine();
            System.out.println("Enter the winning team name.");
            boolean validWin = false;
            String winner = null;
            while (!validWin) {
                winner = input.nextLine();
                if (!winner.equals("") && winner.toLowerCase().equals("red") || winner.toLowerCase().equals("blue")) {
                    validWin = true;
                }else{
                	System.out.println("Invalid winner.");
                }
            }
            rounds.add(new Round(roundNumber, map, winner));
            if (winner.toLowerCase().equals("red")) {
                redScore++;
            }
            if (winner.toLowerCase().equals("blue")) {
                blueScore++;
            }
            if (redScore + blueScore == 10) {
                System.out.println("Tiebreaker Round!");
            }
            if (redScore == 6 && blueScore <= 5 || blueScore == 6 && redScore <= 5) {
                teamWin = true;
            }
            roundNumber++;
        }
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yy HH-mm-ss");
        Date theDate = new Date();
        try {
            PrintWriter writer = new PrintWriter(new String(df.format(theDate) + " Red- " + redScore + " Blue- " + blueScore + ".txt"), "UTF-8");
            writer.println("Teams:");
            writer.println("Red Team: " + redScore);
            writer.println("Blue Team: " + blueScore);
            for (Round round : rounds) {
                writer.println("Round: " + round.getRoundNumber());
                writer.println("     Map: " + round.getSongName());
                writer.println("     Winner: " + round.getRoundWinner());
            }
            writer.println("Banned Maps:");
            writer.println(banned.getSong1());
            writer.println(banned.getSong2());
            writer.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("File not Found.");
        }
        catch (UnsupportedEncodingException e) {
            System.out.println("Unsupported Encoding.");
        }
        System.out.println("The Round has been Completed. Press Enter to exit.");
        input.nextLine();
    }
}
