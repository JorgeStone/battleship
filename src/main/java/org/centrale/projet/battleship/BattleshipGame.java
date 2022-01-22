package org.centrale.projet.battleship;
import java.lang.reflect.Array;

public class BattleshipGame {
    public static void main(String[] args) {
        GameBoard gameBoard = new GameBoard(3, '-', 's', 'x', 'o', 3);
        //GameBoard gameBoard = new GameBoard();
        gameBoard.createGameBoard();
        gameBoard.placeShips();
        gameBoard.printGameBoard();
        while(gameBoard.getUndetectedShipNumber()>0){
            int[] userCoordinates = gameBoard.getUserCoordinates();
            gameBoard.evaluateGuessAndGetTarjet(userCoordinates);
            gameBoard.printGameBoard();
        }
    }   
}
