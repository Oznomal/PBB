package com.lamonzo.pbb.service;

import com.lamonzo.pbb.domain.Position;
import com.lamonzo.pbb.repository.PositionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PositionServiceImpl implements PositionService{

    //== FIELDS ==
    private final PositionRepository positionRepository;

    //== CONSTRUCTORS ==
    public PositionServiceImpl(PositionRepository positionRepository){
        this.positionRepository = positionRepository;
    }

    //== PUBLIC METHODS ==
    @Override
    public void saveOrUpdatePosition(Position position) {
        if(positionRepository.save(position) != null)
            log.info("Saved a position to the DB: {}", position);
        else
            log.warn("There was an issue saving the position to the DB: {}", position);
    }
}
