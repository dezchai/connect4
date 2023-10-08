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
}
