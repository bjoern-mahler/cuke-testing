package de.spinwork.datacuke;

import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Optional;

/**
 * A http-usecase encapsulates all client methods to communicate with a http server part. A response value of the server
 * can be accessed by a getResponse method after a get or post has been done.
 *
 * @param <T> type of the response or the used type in this usecase communication.
 */
public abstract class HttpUseCase<T> implements UseCase {

    private ResponseEntity<T> response;

    private RestTemplate template;
    private String baseUrl;

    private String action;

    public HttpUseCase(String hostname, int port, String appName, String action) {
        baseUrl = "http://" + hostname + ":" + port + "/" + (!StringUtils.isEmpty(appName) ? appName + "/" : appName);
        this.action = action;
        template = new RestTemplate();
    }

    public HttpUseCase() {
        this("localhost", 8090, "", null);
    }

    public HttpUseCase(String action) {
        this.action = action;
    }

    public String getAction() {
        if (action == null) {
            String className = getClass().getSimpleName();
            return className.substring(0, className.indexOf("UseCase")).toLowerCase();
        } else {
            return action;
        }
    }

    private String getUrl() {
        return baseUrl + getAction();
    }

    public ResponseEntity<T> get() {
        response = template.getForEntity(getUrl(), getType());
        return response;
    }

    public ResponseEntity<T> get(Map<String, ?> variables) {
        response = template.getForEntity(getUrl(), getType(), variables);
        return response;
    }

    /**
     * @return return type or used type of this usecase
     */
    protected abstract Class<T> getType();

    public T getResponse() {
        return Optional.ofNullable(response).orElseThrow(IllegalStateException::new).getBody();
    }


    /**
     * Posts a given object to the configured uri
     * @param requestObject object to post
     * @return response of the server
     */
    public ResponseEntity<T> post(Object requestObject) {
        response = template.postForEntity(getUrl(), requestObject, getType());
        return response;
    }
}
