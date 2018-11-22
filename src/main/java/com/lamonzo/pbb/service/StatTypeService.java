package com.lamonzo.pbb.service;

import com.lamonzo.pbb.domain.StatType;

public interface StatTypeService {

    /**
     * Finds the stat type by its name
     * ex: 'Yds', 'TD', 'Rtg'
     *
     * @param statName the stat type name
     * @return the StatType if located, null if not.
     */
    StatType findStatTypeByName(String statName);

    /**
     * Saves a stat type to the DB
     * @param statType the stat type
     */
    void saveStatType(StatType statType);
}
