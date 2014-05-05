package de.spinwork.testapp;

import de.spinwork.datacuke.testutil.EmbeddedTomcat;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import usecases.BaseUseCase;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestBaseUseCaseOnTomcat {

    private static EmbeddedTomcat tomcat = new EmbeddedTomcat();

    private BaseUseCase useCase = new BaseUseCase();


    @BeforeClass
    public static void setUp() {
        tomcat.start();
        tomcat.deploy("");
    }

    @Test
    public void testGet() {
        useCase.assertGetReturnValue();
    }

    @Test
    public void testPost() throws Exception {
        Map<String, String> submitValues = new HashMap<>();
        submitValues.put("input", "value");
        ResponseEntity<Map> response = useCase.post(submitValues);
        assertNotNull(response);
        assertEquals(true, response.getBody().get("success"));
        assertEquals("value", response.getBody().get("input"));
    }

    @AfterClass
    public static void tearDown() {
        tomcat.stop();
    }
}
