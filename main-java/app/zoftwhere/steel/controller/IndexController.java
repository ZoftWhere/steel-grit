package app.zoftwhere.steel.controller;

import app.zoftwhere.steel.model.IndexQueryModel;
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
import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private DataSource dataSource;

    @GetMapping("/")
    public String getIndex(Model model) {
        return "index";
    }

    @PostMapping("/query")
    public String postQuery(@RequestBody IndexQueryModel form, Model model) {
        populateQueryResult(form.getInput(), model);
        return "query";
    }

    private void populateQueryResult(String query, Model model) {
        if (query == null || query.isEmpty()) {
            return;
        }

        try (final Connection connection = dataSource.getConnection()) {
            try (final Statement statement = connection.createStatement()) {
                final boolean hasResultSet = statement.execute(query);
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
            model.addAttribute("error", "steel.error.populate.sql.query");
            model.addAttribute("exception", exception.getMessage());
        }
    }

}
