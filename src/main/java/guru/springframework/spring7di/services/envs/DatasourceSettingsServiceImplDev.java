package guru.springframework.spring7di.services.envs;

import guru.springframework.spring7di.services.DBSettingsService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile({"dev", "default"})
@Service("DBSettingMyService")
public class DatasourceSettingsServiceImplDev implements DBSettingsService {

    @Override
    public String getDatasourceSettings () {
        String datasource = "dev";
        return datasource;
    }
}
