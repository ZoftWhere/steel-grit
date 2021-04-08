package app.zoftwhere.steel;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
public class MainConfiguration {

    private String resourceBootstrapCSS;
    private String resourceBootstrapJS;
    private String resourceJQueryJS;
    private String resourcePopperJS;

    public String getResourceBootstrapCSS() {
        return resourceBootstrapCSS;
    }

    public void setResourceBootstrapCSS(String resourceBootstrapCSS) {
        this.resourceBootstrapCSS = resourceBootstrapCSS;
    }

    public String getResourceBootstrapJS() {
        return resourceBootstrapJS;
    }

    public void setResourceBootstrapJS(String resourceBootstrapJS) {
        this.resourceBootstrapJS = resourceBootstrapJS;
    }

    public String getResourceJQueryJS() {
        return resourceJQueryJS;
    }

    public void setResourceJQueryJS(String resourceJQueryJS) {
        this.resourceJQueryJS = resourceJQueryJS;
    }

    public String getResourcePopperJS() {
        return resourcePopperJS;
    }

    public void setResourcePopperJS(String resourcePopperJS) {
        this.resourcePopperJS = resourcePopperJS;
    }
}
