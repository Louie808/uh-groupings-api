package edu.hawaii.its.api.service;

import edu.hawaii.its.api.configuration.SpringBootWebApplication;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = { SpringBootWebApplication.class })
public class MemberAttributeServiceTest {

    /*
    private static Properties properties;

    @MockBean
    private GrouperApiService grouperApiService;

    @Autowired
    private MemberAttributeService memberAttributeService;

    @BeforeAll
    public static void beforeAll() throws Exception {
        Path path = Paths.get("src/test/resources");
        Path file = path.resolve("grouper.test.properties");
        properties = new Properties();
        properties.load(new FileInputStream(file.toFile()));
    }

    @Test
    public void construction() {
        assertNotNull(memberAttributeService);
    }

    @Test
    public void getMemberAttributesSubjectFound() {
        final String groupAdminPath = "uh-settings:groupingAdmins";
        final String groupOwnerPath = "uh-settings:groupingOwners";
        final String username = "uuu";
        final String uid = "123";

        given(grouperApiService.hasMemberResults(groupAdminPath, username))
                .willReturn(makeWsHasMemberResults("IS_MEMBER"));
        given(grouperApiService.hasMemberResults(groupOwnerPath, username))
                .willReturn(makeWsHasMemberResults("IS_MEMBER"));

        String json = propertyValue("subject.found");
        WsGetSubjectsResults wsGetSubjectsResults = JsonUtil.asObject(json, WsGetSubjectsResults.class);
        given(grouperApiService.subjectsResults(any())).willReturn(wsGetSubjectsResults);

        Person person = memberAttributeService.getMemberAttributes(username, uid);
        assertThat(person, is(notNullValue()));
    }

    @Test
    public void getMemberAttributesSubjectNotFound() {
        final String groupAdminPath = "uh-settings:groupingAdmins";
        final String groupOwnerPath = "uh-settings:groupingOwners";
        final String username = "uuu";
        final String uid = "123";

        given(grouperApiService.hasMemberResults(groupAdminPath, username))
                .willReturn(makeWsHasMemberResults("IS_MEMBER"));
        given(grouperApiService.hasMemberResults(groupOwnerPath, username))
                .willReturn(makeWsHasMemberResults("IS_MEMBER"));

        String json = propertyValue("subject.not.found");
        WsGetSubjectsResults wsGetSubjectsResults = JsonUtil.asObject(json, WsGetSubjectsResults.class);
        given(grouperApiService.subjectsResults(any())).willReturn(wsGetSubjectsResults);

        assertThrows(UhMemberNotFoundException.class,
                () -> memberAttributeService.getMemberAttributes(username, uid));
    }

    @Test
    public void getMemberAttributesNotAdminNotOwner() {
        final String groupAdminPath = "uh-settings:groupingAdmins";
        final String groupOwnerPath = "uh-settings:groupingOwners";
        final String username = "uuu";
        final String uid = "123";

        given(grouperApiService.hasMemberResults(groupAdminPath, username))
                .willReturn(makeWsHasMemberResults("IS_MEMBER_FALSE"));
        given(grouperApiService.hasMemberResults(groupOwnerPath, username))
                .willReturn(makeWsHasMemberResults("IS_MEMBER_FALSE"));

        String json = propertyValue("subject.found");
        WsGetSubjectsResults wsGetSubjectsResults = JsonUtil.asObject(json, WsGetSubjectsResults.class);
        given(grouperApiService.subjectsResults(any())).willReturn(wsGetSubjectsResults);

        Person person = memberAttributeService.getMemberAttributes(username, uid);
        assertThat(person, is(notNullValue()));
    }

    @Test
    @Ignore
    public void getMemberAttributesAdminButNotOwner() {
        final String groupAdminPath = "uh-settings:groupingAdmins";
        final String groupOwnerPath = "uh-settings:groupingOwners";
        final String username = "uuu";
        final String uid = "123";

        given(grouperApiService.hasMemberResults(groupAdminPath, username))
                .willReturn(makeWsHasMemberResults("NOT_IS_MEMBER"));
        given(grouperApiService.hasMemberResults(groupOwnerPath, username))
                .willReturn(makeWsHasMemberResults("IS_MEMBER"));

        String json = propertyValue("subject.found");
        WsGetSubjectsResults wsGetSubjectsResults = JsonUtil.asObject(json, WsGetSubjectsResults.class);
        given(grouperApiService.subjectsResults(any())).willReturn(wsGetSubjectsResults);

        Person person = memberAttributeService.getMemberAttributes(username, uid);
        assertThat(person, is(notNullValue()));
    }

    @Test
    @Ignore
    public void getMemberAttributesOwnerButNotAdmin() {
        final String groupAdminPath = "uh-settings:groupingAdmins";
        final String groupOwnerPath = "uh-settings:groupingOwners";
        final String username = "uuu";
        final String uid = "123";

        given(grouperApiService.hasMemberResults(groupAdminPath, username))
                .willReturn(makeWsHasMemberResults("IS_MEMBER"));
        given(grouperApiService.hasMemberResults(groupOwnerPath, username))
                .willReturn(makeWsHasMemberResults("NOT_IS_MEMBER"));

        String json = propertyValue("subject.found");
        WsGetSubjectsResults wsGetSubjectsResults = JsonUtil.asObject(json, WsGetSubjectsResults.class);
        given(grouperApiService.subjectsResults(any())).willReturn(wsGetSubjectsResults);

        Person person = memberAttributeService.getMemberAttributes(username, uid);
        assertThat(person, is(notNullValue()));
    }

    private String propertyValue(String key) {
        return properties.getProperty(key);
    }

    private WsHasMemberResults makeWsHasMemberResults(final String resultCode) {
        return new WsHasMemberResults() {
            @Override
            public WsHasMemberResult[] getResults() {
                WsHasMemberResult a = new WsHasMemberResult() {
                    @Override
                    public WsResultMeta getResultMetadata() {
                        WsResultMeta b = new WsResultMeta() {
                            @Override
                            public String getResultCode() {
                                return resultCode;
                            }
                        };
                        return b;
                    }
                };
                WsHasMemberResult[] results = { a };
                return results;
            }
        };
    }
     */
}
