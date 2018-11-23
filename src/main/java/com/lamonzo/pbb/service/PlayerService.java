package com.lamonzo.pbb.service;

import com.lamonzo.pbb.domain.Player;
import com.lamonzo.pbb.domain.Position;

public interface PlayerService {

    /**
     * Saves or updates the player within the DB
     * @param player the player to be saved or updated
     */
    void saveOrUpdatePlayer(Player player);

    /**
     * Gets all of the players from the DB
     * @return an iterable of players
     */
    Iterable<Player> getAllPlayers();

    /**
     * Gets all of the players based on a particular position
     * @param position the position
     * @return an iterable of players
     */
    Iterable<Player> getPlayersByPosition(Position position);
}
