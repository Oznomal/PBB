package com.lamonzo.pbb.service;

import com.lamonzo.pbb.domain.Player;

public interface PlayerService {

    /**
     * Saves or updates the player within the DB
     * @param player the player to be saved or updated
     */
    void saveOrUpdatePlayer(Player player);
}
