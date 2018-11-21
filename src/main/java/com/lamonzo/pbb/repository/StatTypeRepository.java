package com.lamonzo.pbb.repository;

import com.lamonzo.pbb.domain.StatType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatTypeRepository extends CrudRepository<StatType, Long> {
}
