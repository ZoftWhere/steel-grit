package app.zoftwhere.steel.server;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Main configuration.
 *
 * @since 2.0.0
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
public class MainConfiguration {

    private String resourceBootstrapCSS;

    private String resourceBootstrapJS;

    private String resourceJQueryJS;

    private String resourcePopperJS;

    /**
     * Get the Bootstrap resource URL.
     *
     * @return Bootstrap CSS resource URL.
     * @since 2.0.0
     */
    public String getResourceBootstrapCSS() {
        return resourceBootstrapCSS;
    }

    /**
     * Set the Bootstrap resource URL.
     *
     * @param resourceBootstrapCSS Bootstrap CSS resource URL.
     * @since 2.0.0
     */
    public void setResourceBootstrapCSS(String resourceBootstrapCSS) {
        this.resourceBootstrapCSS = resourceBootstrapCSS;
    }

    /**
     * Get the Bootstrap JS resource URL.
     *
     * @return Bootstrap JS resource URL.
     * @since 2.0.0
     */
    public String getResourceBootstrapJS() {
        return resourceBootstrapJS;
    }

    /**
     * Set the Bootsrap JS resource URL.
     *
     * @param resourceBootstrapJS Bootstrap JS resource URL.
     * @since 2.0.0
     */
    public void setResourceBootstrapJS(String resourceBootstrapJS) {
        this.resourceBootstrapJS = resourceBootstrapJS;
    }

    /**
     * Get the JQuery JS resource URL.
     *
     * @return JQuery JS resource URL.
     * @since 2.0.0
     */
    public String getResourceJQueryJS() {
        return resourceJQueryJS;
    }

    /**
     * Set the JQuery JS resource URL.
     *
     * @param resourceJQueryJS JQuery JS resource URL.
     * @since 2.0.0
     */
    public void setResourceJQueryJS(String resourceJQueryJS) {
        this.resourceJQueryJS = resourceJQueryJS;
    }

    /**
     * Get the Popper JS resource URL.
     *
     * @return Popper JS resource URL.
     * @since 2.0.0
     */
    public String getResourcePopperJS() {
        return resourcePopperJS;
    }

    /**
     * Set the Popper JS resource URL.
     *
     * @param resourcePopperJS Popper JS resource URL.
     * @since 2.0.0
     */
    public void setResourcePopperJS(String resourcePopperJS) {
        this.resourcePopperJS = resourcePopperJS;
    }


}
