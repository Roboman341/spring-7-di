package guru.springframework.spring7di.controllers;

import guru.springframework.spring7di.services.DBSettingsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
public class FauxControllerTest {

    @Autowired
    FauxController controller;

    @Test
    void printDatasource() {
        System.out.println(controller.getDatasource());
    }
}