package org.centrale.projet.battleship;

public class BattleshipGame {
    public static void main(String[] args) {
        GameBoard gameBoard = new GameBoard(6, '-', 's', 'x', 'o', 4, 1);
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
