package com.lamonzo.pbb.repository;

import com.lamonzo.pbb.domain.Settings;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SettingsRepository extends CrudRepository<Settings, Long> {

}
