package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Tests for the Game class
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
    void testDraw() {
        emptyBoard.setBoard(new char[][]{
                {'O','X','O','X','O','X',' '},
                {'O','X','O','X','O','X','O'},
                {'O','X','O','X','O','X','O'},
                {'X','O','X','O','X','O','X'},
                {'X','O','X','O','X','O','X'},
                {'X','O','X','O','X','O','X'}
        });
        assertFalse(emptyBoard.isGameOver());
        assertFalse(emptyBoard.overDraw());
        assertTrue(emptyBoard.move(6));
        assertTrue(emptyBoard.isGameOver());
        assertTrue(emptyBoard.overDraw());
    }
    @Test
    void testGetters() {
        assertEquals( "Player " + 'O' + "'s" + " turn", filledBoard.getTurnMessage());
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

        emptyBoard.setBoard(new char[][]{
                {'O','X','O','X','O','X','O'},
                {'O','X','O','X','O','X','O'},
                {'O','X','O','X','O','X','O'},
                {'X','O','X','O','X','O','X'},
                {'X','O','X','O','X','O','X'},
                {'X','O','X','O','X','O','X'}
        });
        assertTrue(emptyBoard.isGameOver());
        assertEquals("Draw!", emptyBoard.getWinner());
    }

    @Test
    void testGuiGetters() {
        assertEquals("Yellow's turn", emptyBoard.getTurnGui());
        emptyBoard.move(1);
        assertEquals("Red's turn", emptyBoard.getTurnGui());
        emptyBoard.setBoard(new char[][]{
                {'O','X','O','X','O','X','O'},
                {'O','X','O','X','O','X','O'},
                {'O','X','O','X','O','X','O'},
                {'X','O','X','O','X','O','X'},
                {'X','O','X','O','X','O','X'},
                {'X','O','X','O','X','O','X'}
        });
        assertEquals("Draw!", emptyBoard.getWinnerGui());

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
        assertEquals( "Yellow" + " has won!", filledBoard.getWinnerGui());

        Game redWin = new Game();
        redWin.setBoard(new char[][]{
                {'O','X','O','X','O','X','O'},
                {'O','X','O','X','O','X','O'},
                {'O','X','O','X','O','X','O'},
                {'X','X','X','O','X','O','X'},
                {'X','O','X','O','X','O','X'},
                {'X','O','X','O','X','O','X'}
        });
        assertTrue(redWin.isGameOver());
        assertEquals( "Red" + " has won!", redWin.getWinnerGui());
    }
}