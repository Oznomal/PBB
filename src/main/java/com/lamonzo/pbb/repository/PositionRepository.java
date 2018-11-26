package com.lamonzo.pbb.repository;

import com.lamonzo.pbb.domain.Position;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepository extends CrudRepository<Position, Long> {

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
    @Query(value = "SELECT max_votes FROM position WHERE position_name = :positionName", nativeQuery = true)
    Integer getMaxVotesByPositionName(@Param("positionName") String positionName);
}
