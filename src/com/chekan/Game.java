package com.chekan;

import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Game {

    static int n = 3;
    final char EMPTY = '-';
    char[][] field;
    Random random;
    static Scanner scanner = new Scanner(System.in);

    public Game(){
        field = new char[n][n];
        random = new Random();
    }

    public void runGame(){
        boolean invalidNum = true;
        do{
            System.out.print("Choose the number of players: 1 or 2 -> ");
            String input = scanner.nextLine();
            if(input.equals("1")){
                invalidNum = false;
                Scanner sc = new Scanner(System.in);
                System.out.print("Player Name : ");
                String nameOfPlayer1 = sc.nextLine();
                Player player1 = new Player(nameOfPlayer1, 'X');
                Player player2 = new Player("AI", 'O');
                play(player1, player2);
                result(player1, player2);
            }else if(input.equals("2")){
                invalidNum = false;
                Scanner sc = new Scanner(System.in);
                System.out.print("First Player Name : ");
                String nameOfPlayer1 = sc.nextLine();
                Player player1 = new Player(nameOfPlayer1, 'X');
                System.out.print("Second Player Name : ");
                String nameOfPlayer2 = sc.nextLine();
                Player player2 = new Player(nameOfPlayer2, 'O');
                play(player1, player2);
                result(player1,player2);
            }
        }while (invalidNum);

    }

    public void play(Player player1, Player player2){
        System.out.println("OK! Let's start!");
        fillAmptyField();
        while (true){
            peopleTurn(player1);
            printField();
            if(isWin(player1)){
                System.out.println(player1.getName() + " is winner!");
                player1.setStatus("winner");
                break;
            }
            if(drawInGame()){
                System.out.println("Draw in the game!");
                player1.setStatus("draw");
                player2.setStatus("draw");
                break;
            }
            if(player2.getName().equals("AI")){
                AITurn(player2);
            }else {
                peopleTurn(player2);
            }
            if(isWin(player2)){
                System.out.println(player2.getName() + " is winner!");
                player2.setStatus("winner");
                break;
            }
            if(drawInGame()){
                System.out.println("Draw in the game!");
                player1.setStatus("draw");
                player2.setStatus("draw");
                break;
            }
            printField();
        }
    }

    public void fillAmptyField(){
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                field[i][j] = EMPTY;
                System.out.print(field[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void peopleTurn(Player player){
        if(player.getName().equals("AI")){

        }
        boolean badInput = true;
        do{
            System.out.println(player.getName() + ", please enter coordinate (x y) of " + player.getSign() + " in the range 1..." + n);
            Scanner scanner = new Scanner(System.in);
            String s = scanner.nextLine();
            Pattern pattern = Pattern.compile("^([123]\\s[123])$");
            Matcher matcher = pattern.matcher(s);
            if (matcher.find()) {
                String[] starr = s.split("\\s");
                Integer[] num = new Integer[2];
                for(int i = 0; i < starr.length; i++){
                    num[i] = Integer.parseInt(starr[i]) - 1;
                }
                if(field[num[1]][num[0]] == EMPTY){
                    badInput = false;
                    field[num[1]][num[0]] = player.getSign();
                }
            }
        }while (badInput);
    }

    public void AITurn(Player player){
        int x;
        int y;
        System.out.println("Turn of AI : ");
        do {
            x = random.nextInt(n);
            y = random.nextInt(n);
        } while (field[y][x] != EMPTY);
        field[y][x] = player.getSign();
    }

    public void printField(){
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                System.out.print(field[i][j] + " ");
            }
            System.out.println();
        }
    }

    public boolean isWin(Player player){
        int counter1 = 0;
        int counter2 = 0;
        int counter3 = 0;
        int counter4 = 0;
        int counter5 = 0;
        int counter6 = 0;
        for(int i = 0; i < n; i++){
            if(field[i][i] == player.getSign())
                counter1++;
            if(field[i][n - i - 1] == player.getSign())
                counter2++;
            if(field[0][i] == player.getSign())
                counter3++;
            if(field[i][0] == player.getSign())
                counter4++;
            if(field[n-1][i] == player.getSign())
                counter5++;
            if(field[i][n-1] == player.getSign())
                counter6++;
        }
        return (counter1 == n || counter2 == n || counter3 == n || counter4 == n || counter5 == n || counter6 == n);
    }

    public boolean drawInGame(){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (field[i][j] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    public void result(Player player1,Player player2){
        System.out.println("Results of the game : ");
        System.out.println(player1);
        System.out.println(player2);
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.runGame();
    }
}