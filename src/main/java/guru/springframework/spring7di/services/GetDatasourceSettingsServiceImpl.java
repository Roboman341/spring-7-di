package guru.springframework.spring7di.services;

import org.springframework.stereotype.Service;

@Service
public class GetDatasourceSettingsServiceImpl implements GetDatasourceSettingsService {

    @Override
    public String getDatasourceSettings (String datasource) {
        return datasource;
    }
}
