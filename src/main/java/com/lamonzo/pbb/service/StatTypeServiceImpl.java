package com.lamonzo.pbb.service;

import com.lamonzo.pbb.domain.StatType;
import com.lamonzo.pbb.repository.StatTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StatTypeServiceImpl implements StatTypeService {

    //== FIELDS ==
    private final StatTypeRepository statTypeRepository;

    //== CONSTRUCTORS ==
    public StatTypeServiceImpl(StatTypeRepository statTypeRepository){
        this.statTypeRepository = statTypeRepository;
    }

    //== PUBLIC METHODS ==
    @Override
    public StatType findStatTypeByName(String statName) {
        return statTypeRepository.findStatTypeByStatType(statName);
    }

    @Override
    public void saveStatType(StatType statType) {
        try{
            if(statTypeRepository.save(statType) != null)
                log.info("Saved a stat type to the DB: {}", statType);
            else
                log.info("There was an issue saving a stat type to the DB: {}", statType);
        }catch(DataIntegrityViolationException e){
            //This exception is sometimes  thrown when the multi core task updater is used
            //It is because sometimes the DB is first queried before saving a value
            //This hasn't caused an issue yet, but I would like to track when its thrown in case
            log.info("Caught DataIntegrityViolationException when saving stat type");
        }
    }


}
