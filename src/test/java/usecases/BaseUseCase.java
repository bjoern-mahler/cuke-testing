package usecases;

import de.spinwork.datacuke.HttpUseCase;

import java.util.Map;

import static org.junit.Assert.assertEquals;

public class BaseUseCase extends HttpUseCase<Map> {

    @Override
    protected Class<Map> getType() {
        return Map.class;
    }

    public void assertGetReturnValue() {
        get();
        assertEquals("testValue", getResponse().get("test"));
    }
}
