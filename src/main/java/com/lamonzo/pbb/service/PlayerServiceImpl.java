package com.lamonzo.pbb.service;

import com.lamonzo.pbb.domain.Player;
import com.lamonzo.pbb.repository.PlayerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PlayerServiceImpl implements PlayerService{

    //== FIELDS ==
    private final PlayerRepository playerRepository;

    //== CONSTRUCTORS ==
    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository){
        this.playerRepository = playerRepository;
    }

    //== PUBLIC METHODS ==
    @Override
    public void saveOrUpdatePlayer(Player player) {
        if(playerRepository.save(player) != null)
            log.info("Player saved or updated: {}", player);
        else
            log.warn("There was an issue saving player: {}", player);
    }
}