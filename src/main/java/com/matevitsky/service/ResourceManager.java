package com.matevitsky.service;

import java.util.Locale;
import java.util.ResourceBundle;

public enum ResourceManager {

    INSTANCE;

    private ResourceBundle resourceBundle;

    ResourceManager() {
        resourceBundle = ResourceBundle.getBundle("i18n/common", Locale.getDefault());
    }

    public void changeResource(Locale locale) {
        resourceBundle = ResourceBundle.getBundle("i18n/common", locale);
    }

    public String getString(String key) {
        return resourceBundle.getString(key);
    }
}


