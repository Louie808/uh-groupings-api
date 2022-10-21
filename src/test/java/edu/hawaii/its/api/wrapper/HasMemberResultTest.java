package edu.hawaii.its.api.wrapper;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import edu.hawaii.its.api.util.JsonUtil;

import edu.internet2.middleware.grouperClient.ws.beans.WsHasMemberResults;

import java.io.FileInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class HasMemberResultTest {
    private static Properties properties;

    @BeforeAll
    public static void beforeAll() throws Exception {
        Path path = Paths.get("src/test/resources");
        Path file = path.resolve("grouper.test.properties");
        properties = new Properties();
        properties.load(new FileInputStream(file.toFile()));
    }

    @Test
    public void construction() {
        String json = propertyValue("ws.has.member.result.single.is.not.member");
        WsHasMemberResults wsHasMemberResults = JsonUtil.asObject(json, WsHasMemberResults.class);
        HasMemberResult hasMemberResult = new HasMemberResult(wsHasMemberResults);

        assertNotNull(hasMemberResult);
        assertEquals("IS_NOT_MEMBER", hasMemberResult.getResultCode());
        JsonUtil.printJson(hasMemberResult);

        json = propertyValue("ws.has.member.result.single.is.member");
        wsHasMemberResults = JsonUtil.asObject(json, WsHasMemberResults.class);
        hasMemberResult = new HasMemberResult(wsHasMemberResults);

        assertNotNull(hasMemberResult);
        assertEquals("IS_MEMBER", hasMemberResult.getResultCode());
    }

    private String propertyValue(String key) {
        return properties.getProperty(key);
    }
}
