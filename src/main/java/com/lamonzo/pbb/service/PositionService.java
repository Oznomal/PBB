package com.lamonzo.pbb.service;

import com.lamonzo.pbb.domain.Position;
import org.springframework.data.jpa.repository.Query;

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

    /**
     * Gets the position from the DB based on the position name
     * @param positionName the position name
     * @return the Position object
     */
    Position findByPositionName(String positionName);

    /**
     * Gets the maximum amount of votes based on the position name
     * @param positionName the position name
     * @return the maximum votes for the position
     */
    Integer getMaxVotesByPositionName(String positionName);
}
