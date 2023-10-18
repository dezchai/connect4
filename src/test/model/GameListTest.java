package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameListTest {
    GameList gameList;
    Game x;
    Game y;

    @BeforeEach
    void runBefore() {
        gameList = new GameList();
        x = new Game();
        y = new Game();
        x.move(0);
        y.move(6);
    }

    @Test
    void testAddAndRemoveGame() {
        gameList.addGame(x);
        gameList.addGame(y);
        assertEquals(x, gameList.getGames().get(0));
        assertEquals(y, gameList.getGames().get(1));
        assertEquals(2, gameList.getGames().size());
        gameList.removeGame(1);
        assertEquals(x, gameList.getGames().get(0));
        assertEquals(1, gameList.getGames().size());
    }

    @Test
    void testGetStats() {
        GameList empty = new GameList();
        assertEquals("Complete: " + "0" + " | In Progress: " + "0", empty.getStats());
        gameList.addGame(x);
        gameList.addGame(y);
        Game z = new Game();
        z.setBoard(new char[][]{
                {'O','X','O','X','O','X','O'},
                {'O','X','O','X','O','X','O'},
                {'O','X','O','X','O','X','O'},
                {'X','O','X','O','X','O','X'},
                {'X','O','X','O','X','O','X'},
                {'X','O','X','O','X','O','X'}
        });
        gameList.addGame(z);
        assertEquals("Complete: " + "1" + " | In Progress: " + "2", gameList.getStats());
    }
}
