

package jetbrains.buildserver.sonarplugin.manager;

import jetbrains.buildServer.controllers.BasePropertiesBean;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

/**
 * Created by linfar on 03.10.16.
 */
public class BaseSQSInfo extends BasePropertiesBean implements SQSInfo {
    public static final String ID = "id";
    public static final String JDBC_URL = "jdbcUrl";
    public static final String JDBC_USERNAME = "jdbcUsername";
    public static final String JDBC_PASSWORD = "secure:jdbcPassword";
    public static final String URL = "url";
    public static final String USE_TOKEN = "useToken";
    public static final String TOKEN = "secure:token";
    public static final String LOGIN = "login";
    public static final String PASSWORD = "secure:password";
    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String[] OPEN_FIELDS = new String[] {ID, JDBC_URL, JDBC_USERNAME, URL, LOGIN, NAME, DESCRIPTION, USE_TOKEN};
    public static final String[] ENCRYPTED_FIELDS = new String[] {JDBC_PASSWORD, PASSWORD, TOKEN};

    public BaseSQSInfo(@Nullable final Map<String, String> properties) {
        super(properties);
    }

    public BaseSQSInfo(@NotNull final String id,
                       @Nullable final String name,
                       @Nullable final String url,
                       @NotNull final String useToken,
                       @Nullable final String token,
                       @Nullable final String login,
                       @Nullable final String password,
                       @Nullable final String jdbcUrl,
                       @Nullable final String jdbcUsername,
                       @Nullable final String jdbcPassword) {
        super(null);
        setProperty(ID, id);
        setProperty(NAME, name);
        setProperty(URL, url);
        setProperty(USE_TOKEN, useToken);
        setProperty(TOKEN, token);
        setProperty(LOGIN, login);
        setProperty(PASSWORD, password);
        setProperty(JDBC_PASSWORD, jdbcPassword);
        setProperty(JDBC_URL, jdbcUrl);
        setProperty(JDBC_USERNAME, jdbcUsername);
    }

    public BaseSQSInfo(@NotNull final String id) {
        super(null);
        setProperty(ID, id);
    }

    @Override
    public synchronized void setProperty(String name, String value) {
        if (value == null) getProperties().remove(name);
        else super.setProperty(name, value);
    }

    protected String get(@NotNull final String key) {
        return getProperties().get(key);
    }

    @Nullable
    public String getUrl() {
        return get(URL);
    }

    @Override
    public boolean isTokenLoginUsed() {
        final String useToken = getToken();
        return useToken != null ? Boolean.parseBoolean(useToken) : getToken() != null;
    }

    @Nullable
    @Override
    public String getToken() {
        // Firstly, read new parameter name, if not present, check if this entity still has old parameter
        return get(TOKEN) != null ? get(TOKEN) : get("token");
    }

    @Nullable
    public String getLogin() {
        return get(LOGIN);
    }

    @Nullable
    public String getPassword() {
        // Firstly, read new parameter name, if not present, check if this entity still has old parameter
        return get(PASSWORD) != null ? get(PASSWORD) : get("password");
    }

    @Nullable
    public String getJDBCUrl() {
        return get(JDBC_URL);
    }

    @Nullable
    public String getJDBCUsername() {
        return get(JDBC_USERNAME);
    }

    @Nullable
    public String getJDBCPassword() {
        // Firstly, read new parameter name, if not present, check if this entity still has old parameter
        return get(JDBC_PASSWORD) != null ? get(JDBC_PASSWORD) : get("jdbcPassword");
    }

    @NotNull
    public String getId() {
        return get(ID);
    }

    @Nullable
    public String getName() {
        return get(NAME);
    }

    @Nullable
    public String getDescription() {
        return get(DESCRIPTION);
    }

    @NotNull
    @Override
    public Map<String, String> getParameters() {
        return getProperties();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseSQSInfo)) return false;

        BaseSQSInfo that = (BaseSQSInfo) o;

        return getId().equals(that.getId());

    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}