package com.lamonzo.pbb.repository;

import com.lamonzo.pbb.domain.Player;
import com.lamonzo.pbb.domain.Position;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Long> {

    /**
     * Gets all of the players based on a particular position
     * @param position the position
     * @return an iterable of players
     */
    Iterable<Player> findAllByPosition(Position position);
}
