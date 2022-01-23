package org.centrale.projet.battleship;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class GameBoardTest {
    
    public GameBoardTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of createGameBoard method, of class GameBoard.
     */
    @Test
    public void testCreateGameBoard() {
        System.out.println("createGameBoard");
        GameBoard gameBoard = new GameBoard(5, 'w', 's', 'h', 'm', 4, 1);
        assertEquals(5, gameBoard.getGameBoardLength());
        assertEquals(4, gameBoard.getShipNumber());
        assertEquals(1, gameBoard.getCruiserNumber());
        assertEquals(4+1*2, gameBoard.getUndetectedShipNumber());
        assertEquals('h', gameBoard.getHit());
        assertEquals('w', gameBoard.getWater());
        assertEquals('s', gameBoard.getShip());
        assertEquals('m', gameBoard.getMiss());
        gameBoard.createGameBoard();
        assertEquals('w', gameBoard.getGameBoard()[gameBoard.getGameBoardLength()-1][gameBoard.getGameBoardLength()-1]);
        assertEquals('w', gameBoard.getGameBoard()[0][0]);
    }

    /**
     * Test of placeShips method, of class GameBoard.
     */
    @Test
    public void testPlaceShips() {
        System.out.println("placeShips");
        GameBoard gameBoard = new GameBoard(5,'w', 's', 'h', 'm', 4, 1);
        gameBoard.createGameBoard();
        gameBoard.placeShips();
        
        int placedShips = 0;
        for (int i=0; i<gameBoard.getGameBoardLength(); i++){
            for (int j=0; j<gameBoard.getGameBoardLength(); j++){
                if(gameBoard.getGameBoard()[i][j]==gameBoard.getShip()){
                    placedShips++;
                }
            }
        }
        assertEquals(4+1*2,placedShips);
    }

    /**
     * Test of generateShipCoordinates method, of class GameBoard.
     */
    @Test
    public void testGenerateShipCoordinates() {
        System.out.println("generateShipCoordinates");
        GameBoard gameBoard = new GameBoard(7,'w', 's', 'h', 'm', 4, 1);
        int[] coordinates_1 = new int[2];
        int[] coordinates_2 = new int[2];
        int[] coordinates_3 = new int[2];
        coordinates_1 = gameBoard.generateShipCoordinates();
        coordinates_2 = gameBoard.generateShipCoordinates();
        
        Boolean result = true;
        if (coordinates_1.length != coordinates_2.length) {
            result = false;
        }
        for (int i = 0; i < coordinates_1.length; i++) {
            if (coordinates_1[i] != coordinates_2[i]) {
                result = false;
            }
        }
        assertEquals(false, result);
        
        coordinates_3 = gameBoard.generateShipCoordinates();
        if (coordinates_1.length != coordinates_3.length) {
            result = false;
        }
        for (int i = 0; i < coordinates_1.length; i++) {
            if (coordinates_1[i] != coordinates_3[i]) {
                result = false;
            }
        }
        assertEquals(false, result);
    }

    /**
     * Test of printGameBoard method, of class GameBoard.
     */
    @Test
    public void testPrintGameBoard() {
        System.out.println("printGameBoard");
        // This test will be run during the integration tests phase
        //because it will show the final result of each game tour
    }

    /**
     * Test of getUserCoordinates method, of class GameBoard.
     */
    @Test
    public void testGetUserCoordinates() {
        System.out.println("getUserCoordinates");
        // This test will be run during the integration tests phase
        //because it will show the final result of each game tour
    }

    /**
     * Test of evaluateGuessAndGetTarjet method, of class GameBoard.
     */
    @Test
    public void testEvaluateGuessAndGetTarjet() {
        System.out.println("evaluateGuessAndGetTarjet");
        GameBoard gameBoard = new GameBoard(5,'w', 's', 'h', 'm', 4, 1);
        gameBoard.createGameBoard();
        gameBoard.placeShips();
        int[] userCoordinates = {2,1};
        
        gameBoard.evaluateGuessAndGetTarjet(userCoordinates);
        Boolean result = true;
        if(gameBoard.getGameBoard()[userCoordinates[0]][userCoordinates[1]] != 'w'){
            result = false;
        }
        assertEquals(false, result);
    }
}
