package guru.springframework.spring7di.services.envs;

import guru.springframework.spring7di.services.DBSettingsService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("prod")
@Service("DBSettingMyService")
public class DatasourceSettingsServiceImplProd implements DBSettingsService {

    @Override
    public String getDatasourceSettings () {
        String datasource = "prod";
        return datasource;
    }
}
