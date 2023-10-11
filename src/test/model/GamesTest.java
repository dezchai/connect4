package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GamesTest {
    Games games;
    Game x;
    Game y;

    @BeforeEach
    void runBefore() {
        games = new Games();
        x = new Game();
        y = new Game();
        x.move(0);
        y.move(6);
    }

    @Test
    void testAddAndRemoveGame() {
        games.addGame(x);
        games.addGame(y);
        assertEquals(x, games.getGames().get(0));
        assertEquals(y, games.getGames().get(1));
        assertEquals(2, games.getGames().size());
        games.removeGame(1);
        assertEquals(x, games.getGames().get(0));
        assertEquals(1, games.getGames().size());
    }

    @Test
    void testGetStats() {
        Games empty = new Games();
        assertEquals("Complete: " + "0" + " | In Progress: " + "0", empty.getStats());
        games.addGame(x);
        games.addGame(y);
        Game z = new Game();
        z.setBoard(new char[][]{
                {'O','X','O','X','O','X','O'},
                {'O','X','O','X','O','X','O'},
                {'O','X','O','X','O','X','O'},
                {'X','O','X','O','X','O','X'},
                {'X','O','X','O','X','O','X'},
                {'X','O','X','O','X','O','X'}
        });
        games.addGame(z);
        assertEquals("Complete: " + "1" + " | In Progress: " + "2", games.getStats());
    }
}
