package ui.steps;

import ui.pages.SettingsPage;


public class SettingsStep {

    SettingsPage settingsPage;

    public SettingsStep(SettingsPage settingsPage) {
        this.settingsPage = settingsPage;
    }

    public void changeLanguage() {
        settingsPage.changeTestrailUILanguage().revertTestrailUILanguage();
    }

    public void changeColorTheme() {
        settingsPage.changeTestrailColorScheme().revertTestrailColorScheme();
    }
}