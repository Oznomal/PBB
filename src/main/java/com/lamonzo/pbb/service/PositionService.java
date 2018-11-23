package com.lamonzo.pbb.service;

import com.lamonzo.pbb.domain.Position;

import java.util.List;

public interface PositionService {

    /**
     * Saves or updates the position in the DB
     * @param position the position
     */
    void saveOrUpdatePosition(Position position);

    /**
     * Gets all of the position information from the DB
     * @return an iterable of positions
     */
    Iterable<Position> getAllPositions();
}
