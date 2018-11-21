package com.lamonzo.pbb.repository;

import com.lamonzo.pbb.domain.Stat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatRepository extends CrudRepository<Stat, Long> {
}
