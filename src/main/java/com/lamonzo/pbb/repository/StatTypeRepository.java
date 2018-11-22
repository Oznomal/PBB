package com.lamonzo.pbb.repository;

import com.lamonzo.pbb.domain.StatType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatTypeRepository extends CrudRepository<StatType, Long> {

    /**
     * Finds the stat type by its name
     * ex: 'Yds', 'TD', 'Rtg'
     *
     * @param statType the stat type name
     * @return the StatType if located, null if not.
     */
    StatType findStatTypeByStatType(String statType);
}
