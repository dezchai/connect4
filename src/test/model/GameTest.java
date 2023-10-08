package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    Game filledBoard;
    Game emptyBoard;
    String time;

    @BeforeEach
    void runBefore() {
        filledBoard = new Game();
        emptyBoard = new Game();
        filledBoard.setBoard(new char[][]{
            {' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', 'O', ' ', ' ', ' ', ' '},
            {' ', 'X', 'O', 'X', ' ', ' ', ' '},
            {'O', 'X', 'X', 'O', 'O', ' ', ' '},
            {'O', 'O', 'X', 'O', 'X', 'X', 'O'}
        });
        time = filledBoard.getName();
    }

    @Test
    void testMoveNormal() {
        assertTrue(filledBoard.move(0));
        assertArrayEquals(new char[][]{
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', 'O', ' ', ' ', ' ', ' '},
                {'O', 'X', 'O', 'X', ' ', ' ', ' '},
                {'O', 'X', 'X', 'O', 'O', ' ', ' '},
                {'O', 'O', 'X', 'O', 'X', 'X', 'O'}
        }, filledBoard.getBoard());
    }

    @Test
    void testMoveEdge() {
        filledBoard.setBoard(new char[][]{
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', 'X', ' ', ' ', ' ', ' '},
                {' ', ' ', 'O', ' ', ' ', ' ', ' '},
                {' ', 'X', 'O', 'X', ' ', ' ', ' '},
                {'O', 'X', 'X', 'O', 'O', ' ', ' '},
                {'O', 'O', 'X', 'O', 'X', 'X', 'O'}
        });
        assertTrue(filledBoard.move(2));
        assertArrayEquals(new char[][]{
                {' ', ' ', 'O', ' ', ' ', ' ', ' '},
                {' ', ' ', 'X', ' ', ' ', ' ', ' '},
                {' ', ' ', 'O', ' ', ' ', ' ', ' '},
                {' ', 'X', 'O', 'X', ' ', ' ', ' '},
                {'O', 'X', 'X', 'O', 'O', ' ', ' '},
                {'O', 'O', 'X', 'O', 'X', 'X', 'O'}
        }, filledBoard.getBoard());
        assertFalse(filledBoard.move(2));
    }

    @Test
    void testMoveButGameOver() {
        filledBoard.setBoard(new char[][]{
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', 'O', ' ', ' ', ' ', 'O'},
                {' ', 'X', 'O', 'X', ' ', 'O', 'X'},
                {'O', 'X', 'X', 'O', 'O', 'O', 'X'},
                {'O', 'O', 'X', 'O', 'X', 'X', 'O'}
        });
        assertTrue(filledBoard.isGameOver());
        assertFalse(filledBoard.move(0));
        assertFalse(filledBoard.move(1));
        assertFalse(filledBoard.move(2));
        assertFalse(filledBoard.move(3));
        assertFalse(filledBoard.move(4));
        assertFalse(filledBoard.move(5));
        assertFalse(filledBoard.move(6));
    }
    @Test
    void testOverUp() {
        assertTrue(filledBoard.move(2));
        assertFalse(filledBoard.overUp());
        assertTrue(filledBoard.move(0));
        assertFalse(filledBoard.overUp());
        assertTrue(filledBoard.move(2));
        assertArrayEquals(new char[][]{
                {' ', ' ', 'O', ' ', ' ', ' ', ' '},
                {' ', ' ', 'O', ' ', ' ', ' ', ' '},
                {' ', ' ', 'O', ' ', ' ', ' ', ' '},
                {'X', 'X', 'O', 'X', ' ', ' ', ' '},
                {'O', 'X', 'X', 'O', 'O', ' ', ' '},
                {'O', 'O', 'X', 'O', 'X', 'X', 'O'}
        }, filledBoard.getBoard());
        assertTrue(filledBoard.overUp());
        assertTrue(filledBoard.isGameOver());
    }

    @Test
    void testOverRight() {
        assertTrue(filledBoard.move(5));
        assertFalse(filledBoard.overRight());
        assertTrue(filledBoard.move(5));
        assertFalse(filledBoard.overRight());
        assertTrue(filledBoard.move(6));
        assertArrayEquals(new char[][]{
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', 'O', ' ', ' ', ' ', ' '},
                {' ', 'X', 'O', 'X', ' ', 'X', ' '},
                {'O', 'X', 'X', 'O', 'O', 'O', 'O'},
                {'O', 'O', 'X', 'O', 'X', 'X', 'O'}
        }, filledBoard.getBoard());
        assertTrue(filledBoard.overRight());
        assertTrue(filledBoard.isGameOver());
    }

    @Test
    void testOverDiagonalRight() {
        assertTrue(filledBoard.move(3));
        assertFalse(filledBoard.overDiagonalRight());
        assertTrue(filledBoard.move(0));
        assertFalse(filledBoard.overDiagonalRight());
        assertTrue(filledBoard.move(4));
        assertFalse(filledBoard.overDiagonalRight());
        assertTrue(filledBoard.move(0));
        assertFalse(filledBoard.overDiagonalRight());
        assertTrue(filledBoard.move(5));
        assertArrayEquals(new char[][]{
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {'X', ' ', 'O', 'O', ' ', ' ', ' '},
                {'X', 'X', 'O', 'X', 'O', ' ', ' '},
                {'O', 'X', 'X', 'O', 'O', 'O', ' '},
                {'O', 'O', 'X', 'O', 'X', 'X', 'O'}
        }, filledBoard.getBoard());
        assertTrue(filledBoard.overDiagonalRight());
        assertTrue(filledBoard.isGameOver());
    }

    @Test
    void testOverDiagonalLeft() {
        assertTrue(filledBoard.move(5));
        assertFalse(filledBoard.overDiagonalLeft());
        assertTrue(filledBoard.move(6));
        assertFalse(filledBoard.overDiagonalLeft());
        assertTrue(filledBoard.move(5));
        assertFalse(filledBoard.overDiagonalLeft());
        assertTrue(filledBoard.move(6));
        assertFalse(filledBoard.overDiagonalLeft());
        assertTrue(filledBoard.move(6));
        assertArrayEquals(new char[][]{
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', 'O', ' ', ' ', ' ', 'O'},
                {' ', 'X', 'O', 'X', ' ', 'O', 'X'},
                {'O', 'X', 'X', 'O', 'O', 'O', 'X'},
                {'O', 'O', 'X', 'O', 'X', 'X', 'O'}
        }, filledBoard.getBoard());
        assertTrue(filledBoard.overDiagonalLeft());
        assertTrue(filledBoard.isGameOver());

        assertFalse(emptyBoard.overDiagonalLeft());
        emptyBoard.setBoard(new char[][]{
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', 'X', ' ', ' ', ' '},
                {' ', ' ', ' ', 'X', ' ', 'O', ' '},
                {' ', ' ', 'X', 'O', 'O', 'O', 'X'},
                {' ', 'O', 'X', 'O', 'X', 'X', 'O'}
        });
        assertFalse(emptyBoard.overDiagonalLeft());
        emptyBoard.setBoard(new char[][]{
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', 'X', 'O', ' '},
                {' ', ' ', ' ', ' ', 'O', 'O', ' '},
                {' ', ' ', 'X', 'O', 'O', 'X', ' '},
                {' ', 'O', 'O', 'X', 'X', 'O', 'O'}
        });
        assertTrue(emptyBoard.overDiagonalLeft());
        emptyBoard.setBoard(new char[][]{
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', 'X', 'X', ' '},
                {' ', ' ', 'O', 'X', 'O', 'O', ' '},
                {' ', 'O', 'O', 'O', 'O', 'X', ' '},
                {'O', 'X', 'O', 'X', 'X', 'O', 'O'}
        });
        assertFalse(emptyBoard.overDiagonalLeft());
        emptyBoard.setBoard(new char[][]{
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', 'O', 'X', 'X', ' '},
                {' ', ' ', 'O', 'X', 'O', 'O', ' '},
                {' ', 'O', 'O', 'O', 'O', 'X', ' '},
                {'X', 'X', 'O', 'X', 'X', 'O', 'O'}
        });
        assertFalse(emptyBoard.overDiagonalLeft());
    }

    @Test
    void testGetters() {
        assertEquals( "Player " + 'O' + "'s" + " turn", filledBoard.getTurn());
        filledBoard.setBoard(new char[][]{
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', 'O', ' ', ' ', ' ', ' '},
                {' ', 'X', 'O', 'X', ' ', 'O', 'X'},
                {'O', 'X', 'X', 'O', 'O', 'O', 'X'},
                {'O', 'O', 'X', 'O', 'X', 'X', 'O'}
        });
        filledBoard.move(6);
        assertTrue(filledBoard.isGameOver());
        assertEquals( "Player " + 'O' + " has won!", filledBoard.getWinner());
        assertEquals(time, filledBoard.getName());
    }
}