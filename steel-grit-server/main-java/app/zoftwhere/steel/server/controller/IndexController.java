package app.zoftwhere.steel.server.controller;

import app.zoftwhere.steel.server.MainConfiguration;
import app.zoftwhere.steel.server.model.IndexQueryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Index page controller.
 *
 * @since 1.0.0
 */
@Controller
public class IndexController {

    @Autowired
    private MainConfiguration mainConfiguration;

    @Autowired
    private DataSource dataSource;

    /**
     * Index page.
     *
     * @param model Template view model.
     * @return template name.
     * @since 1.0.0
     */
    @GetMapping("/")
    public String getIndex(Model model) {
        mapViewResources(model);
        return "index";
    }

    /**
     * Query page.
     *
     * @param form  Index query form submitted.
     * @param model Template view model.
     * @return template name.
     * @since 1.0.0
     */
    @PostMapping("/query")
    public String postQuery(@RequestBody IndexQueryModel form, Model model) {
        mapViewResources(model);
        populateQueryResult(form.getInput(), model);
        return "query";
    }

    /**
     * Private method for populating parameterized resource string.
     *
     * @param model Template view model.
     * @since 2.0.0
     */
    private void mapViewResources(Model model) {
        model.addAttribute("resourceBootstrapCSS", mainConfiguration.getResourceBootstrapCSS());
        model.addAttribute("resourceBootstrapJS", mainConfiguration.getResourceBootstrapJS());
        model.addAttribute("resourceJQueryJS", mainConfiguration.getResourceJQueryJS());
        model.addAttribute("resourcePopperJS", mainConfiguration.getResourcePopperJS());
    }

    /**
     * Private method for populating view with query result.
     *
     * @param query SQL query to execute.
     * @param model Template view model.
     * @since 1.0.0
     */
    private void populateQueryResult(String query, Model model) {
        model.addAttribute("dateTime", LocalDateTime.now().toString());

        if (query == null || query.isEmpty()) {
            return;
        }
        model.addAttribute("query", query);

        try (final Connection connection = dataSource.getConnection()) {
            try (final Statement statement = connection.createStatement()) {
                final boolean hasResultSet = statement.execute(query);

                final int updateCount = statement.getUpdateCount();
                if (updateCount > 0) {
                    model.addAttribute("updateCount", "" + updateCount);
                }

                if (hasResultSet) {
                    final ResultSet resultSet = statement.getResultSet();
                    final ResultSetMetaData metaData = resultSet.getMetaData();
                    final int count = metaData.getColumnCount();

                    final List<String> header = new ArrayList<>(count);
                    for (int i = 1; i <= count; i++) {
                        header.add(metaData.getColumnName(i));
                    }
                    model.addAttribute("tableHeader", header);

                    final List<List<String>> content = new ArrayList<>();
                    while (resultSet.next()) {
                        final List<String> row = new ArrayList<>(count);
                        for (int i = 1; i <= count; i++) {
                            row.add(resultSet.getString(i));
                        }
                        content.add(row);
                    }

                    model.addAttribute("tableContent", content);
                }
            }
        }
        catch (SQLException exception) {
            model.addAttribute("error", exception.getMessage());
        }
    }

}
