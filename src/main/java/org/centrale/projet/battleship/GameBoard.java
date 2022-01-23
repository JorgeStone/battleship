package org.centrale.projet.battleship;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class GameBoard {
    private int gameBoardLength;
    private char water;
    private char ship;
    private char hit;
    private char miss;
    private int shipNumber;
    private char[][] gameBoard;
    private int undetectedShipNumber;
    private int cruiserNumber;
    
    
    public GameBoard(){
        this.gameBoardLength = 4;
        this.water = '-';
        this.ship = 's';
        this.hit = 'x';
        this.miss = 'o';
        this.shipNumber = 3;
        this.gameBoard = new char[gameBoardLength][gameBoardLength];
        this.cruiserNumber = 2;
        this.undetectedShipNumber = this.shipNumber + this.cruiserNumber*2;
    }  
    
    public GameBoard(int gameBoardLength, char water, char ship, char hit, char miss, int shipNumber, int cruiserNumber) {
        this.gameBoardLength = gameBoardLength;
        this.water = water;
        this.ship = ship;
        this.hit = hit;
        this.miss = miss;
        this.shipNumber = shipNumber;
        this.gameBoard = new char[gameBoardLength][gameBoardLength];
        this.cruiserNumber=cruiserNumber;
        this.undetectedShipNumber = this.shipNumber + this.cruiserNumber*2;
    }

    public int getGameBoardLength() {
        return gameBoardLength;
    }

    public char getWater() {
        return water;
    }

    public char getShip() {
        return ship;
    }

    public char getHit() {
        return hit;
    }

    public char getMiss() {
        return miss;
    }

    public int getShipNumber() {
        return shipNumber;
    }

    public char[][] getGameBoard() {
        return gameBoard;
    }

    public int getUndetectedShipNumber() {
        return undetectedShipNumber;
    }

    public int getCruiserNumber() {
        return cruiserNumber;
    }

    public void setGameBoardLength(int gameBoardLength) {
        this.gameBoardLength = gameBoardLength;
    }

    public void setWater(char water) {
        this.water = water;
    }

    public void setShip(char ship) {
        this.ship = ship;
    }

    public void setHit(char hit) {
        this.hit = hit;
    }

    public void setMiss(char miss) {
        this.miss = miss;
    }

    public void setShipNumber(int shipNumber) {
        this.shipNumber = shipNumber;
    }

    public void setGameBoard(int row, int col, char newUpdate) {
        this.gameBoard [row][col] = newUpdate;
    }

    public void setUndetectedShipNumber(int undetectedShipNumber) {
        this.undetectedShipNumber = undetectedShipNumber;
    }

    public void setCruiserNumber(int cruiserNumber) {
        this.cruiserNumber = cruiserNumber;
    }
    
    public void createGameBoard(){
        for(char[] row : this.gameBoard){
            Arrays.fill(row, water);
        }
    }
    
    public void placeShips(){
        int placedShips = 0;
        while(placedShips<this.getShipNumber()){
            int[] location = generateShipCoordinates();
            if(this.gameBoard[location[0]][location[1]] == water){
                this.setGameBoard(location[0],location[1], this.ship);
                placedShips++;
            }
        }
        
        int orientation = new Random().nextInt(4);
        
        int placedCruisers = 0;
        while(placedCruisers<this.getCruiserNumber()){
            int[] location = generateShipCoordinates();
            if(this.gameBoard[location[0]][location[1]] == water && (location[0]-1) > 0 && (location[0]+1) < this.getGameBoardLength() && (location[1]-1) > 0 && (location[1]+1) < this.getGameBoardLength()){
                if(orientation == 0 && this.gameBoard[location[0]+1][location[1]] == water){
                    this.setGameBoard(location[0],location[1], this.ship);
                    this.setGameBoard(location[0]+1,location[1], this.ship);
                    placedCruisers++;
                }
                else if(orientation == 1 && this.gameBoard[location[0]-1][location[1]] == water){
                    this.setGameBoard(location[0],location[1], this.ship);
                    this.setGameBoard(location[0]-1,location[1], this.ship);
                    placedCruisers++;
                }
                else if(orientation == 2 && this.gameBoard[location[0]][location[1]+1] == water){
                    this.setGameBoard(location[0],location[1], this.ship);
                    this.setGameBoard(location[0],location[1]+1, this.ship);
                    placedCruisers++;
                }
                else if(orientation == 3 && this.gameBoard[location[0]][location[1]-1] == water){
                    this.setGameBoard(location[0],location[1], this.ship);
                    this.setGameBoard(location[0],location[1]-1, this.ship);
                    placedCruisers++;
                }
            }
        }
    }
    
    public int[] generateShipCoordinates() {
        int[] coordinates = new int[2];
        for (int i = 0; i < coordinates.length; i++) {
            coordinates[i] = new Random().nextInt(this.getGameBoardLength());
        }
        return coordinates;
    }
    
    public void printGameBoard(){  
        System.out.print("  ");
        for(int i=0; i<this.gameBoardLength; i++){
            System.out.print(i + 1 + " ");
        }
        
        System.out.println();
        for(int row = 0; row < this.gameBoardLength; row++){
            System.out.print(row + 1+ " ");
            for(int col = 0; col < this.gameBoardLength; col++){     
                if(this.gameBoard[row][col] == ship){
                    System.out.print(water + " ");
                }
                else if(this.gameBoard[row][col] == hit){
                    System.out.print(hit + " ");
                }
                else if(this.gameBoard[row][col] == miss){
                    System.out.print(miss + " ");
                }
                else{
                    System.out.print(water + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
    
    public int[] getUserCoordinates(){
        int[] coordinates = new int[2];
        int row;
        int col;
        do{
            System.out.println("Row: ");
            row = new Scanner(System.in).nextInt();
        }while(row < 0 || row > this.gameBoardLength);
        do{
            System.out.println("Col: ");
            col = new Scanner(System.in).nextInt();
        }while(col < 0 || col > this.gameBoardLength);
        coordinates[0] = row-1;
        coordinates[1] = col-1;
        return coordinates;
    }
    
    public void evaluateGuessAndGetTarjet(int[] userCoordinates){
        String message;
        if(this.gameBoard[userCoordinates[0]][userCoordinates[1]] == ship){
            message = "Hit !\n";
            this.setGameBoard(userCoordinates[0],userCoordinates[1], this.hit);
            this.undetectedShipNumber = this.undetectedShipNumber -1;
        }
        else if(this.gameBoard[userCoordinates[0]][userCoordinates[1]] == water){
            message = "Miss !\n";
            this.setGameBoard(userCoordinates[0],userCoordinates[1], this.miss);
        }
        else{
            message = "Already Hit !\n";
        }
        System.out.println(message);
    }
    
}
