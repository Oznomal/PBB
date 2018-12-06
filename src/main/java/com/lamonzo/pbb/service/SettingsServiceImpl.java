package com.lamonzo.pbb.service;

import com.lamonzo.pbb.domain.Settings;
import com.lamonzo.pbb.repository.SettingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SettingsServiceImpl implements SettingsService{

    //== FIELDS ==
    private final SettingsRepository settingsRepository;

    //== CONSTRUCTORS ==
    @Autowired
    public SettingsServiceImpl(SettingsRepository settingsRepository){
        this.settingsRepository = settingsRepository;
    }

    //== PUBLIC METHODS ==
    @Override
    public void saveSettings(Settings settings) {
        if(settings.getId() != null)
            settingsRepository.save(settings);
        else{
            Settings dbSettings = getSettings();
            if(dbSettings != null){
                dbSettings.setVotingGoals(settings.getVotingGoals());
                dbSettings.setNumberOfBrowsers(settings.getNumberOfBrowsers());
                dbSettings.setAutoFill(settings.isAutoFill());
                dbSettings.setShowBrowser(settings.isShowBrowser());
                dbSettings.setRotateProxies(settings.isRotateProxies());
                settingsRepository.save(dbSettings);
            }
            else settingsRepository.save(settings);
        }
    }

    @Override
    public Settings getSettings() {
        for(Settings s : settingsRepository.findAll()){
            if(s != null)
                return s;
        }

        return null;
    }
}
