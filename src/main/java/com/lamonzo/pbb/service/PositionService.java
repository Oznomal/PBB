package com.lamonzo.pbb.service;

import com.lamonzo.pbb.domain.Position;

public interface PositionService {

    /**
     * Saves or updates the position in the DB
     * @param position the position
     */
    void saveOrUpdatePosition(Position position);
}
