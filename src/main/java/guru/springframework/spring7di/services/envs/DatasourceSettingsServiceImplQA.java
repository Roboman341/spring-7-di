package guru.springframework.spring7di.services.envs;

import guru.springframework.spring7di.services.DBSettingsService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("qa")
@Service("DBSettingMyService")
public class DatasourceSettingsServiceImplQA implements DBSettingsService {

    @Override
    public String getDatasourceSettings () {
        String datasource = "qa";
        return datasource;
    }
}
