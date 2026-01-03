package guru.springframework.spring7di.services.envs;

import guru.springframework.spring7di.services.DBSettingsService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("uat")
@Service("DBSettingMyService")
public class DatasourceSettingsServiceImplUAT implements DBSettingsService {

    @Override
    public String getDatasourceSettings () {
        String datasource = "uat";
        return datasource;
    }
}
