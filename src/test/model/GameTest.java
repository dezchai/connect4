package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    Game filledBoard;
    @BeforeEach
    void runBefore() {
        filledBoard = new Game();
        filledBoard.setBoard(new char[][]{
            {' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', 'O', ' ', ' ', ' ', ' '},
            {' ', 'X', 'O', 'X', ' ', ' ', ' '},
            {'O', 'X', 'X', 'O', 'O', ' ', ' '},
            {'O', 'O', 'X', 'O', 'X', 'X', 'O'}
        });
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
    }
}