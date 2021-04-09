package app.zoftwhere.steel.server.model;

/**
 * Model for Index Query form submission.
 *
 * @since 1.0.0
 */
public class IndexQueryModel {

    private String input;

    /**
     * Get input SQL query.
     *
     * @return SQL query.
     * @since 1.0.0
     */
    public String getInput() {
        return input;
    }

    /**
     * Set input SQL query.
     *
     * @param input SQL query.
     * @since 1.0.0
     */
    public void setInput(String input) {
        this.input = input;
    }

}
