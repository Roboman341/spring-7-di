package guru.springframework.spring7di.controllers;

import guru.springframework.spring7di.services.DBSettingsService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class FauxController {

    private final DBSettingsService settingsService;

    public FauxController(@Qualifier("DBSettingMyService") DBSettingsService settingsService) {
        this.settingsService = settingsService;
    }

    public String getDatasource() {
        return settingsService.getDatasourceSettings();
    }
}
