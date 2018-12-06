package com.lamonzo.pbb.service;

import com.lamonzo.pbb.domain.Settings;

public interface SettingsService {

    /**
     * Saves the settings in the DB
     * @param settings
     */
    void saveSettings (Settings settings);

    /**
     * Gets and returns the user settings from the DB
     * @return
     */
    Settings getSettings();
}
