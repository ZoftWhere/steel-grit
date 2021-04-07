package app.zoftwhere.steel.controller;

import app.zoftwhere.steel.model.IndexQueryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.thymeleaf.context.LazyContextVariable;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private Connection connection;

    @GetMapping("/")
    public String getIndex(Model model) {
        return "index";
    }

    @PostMapping("/query")
    public String postQuery(@RequestBody IndexQueryModel form, Model model) throws SQLException {
        populateQueryResult(form.getInput(), model);
        return "query";
    }

    private void populateQueryResult(String query, Model model) throws SQLException {
        if (query == null || query.isEmpty()) {
            return;
        }

        final ResultSet resultSet = connection.createStatement().executeQuery(query);
        final ResultSetMetaData meta = resultSet.getMetaData();
        final int count = meta.getColumnCount();

        final List<String> tableHeader = new ArrayList<>(count);
        for (int i = 1; i <= count; i++) {
            tableHeader.add(meta.getColumnName(i));
        }
        model.addAttribute("tableHeader", tableHeader);

        model.addAttribute("tableContent", new LazyContextVariable<Iterable<List<String>>>() {
            @Override
            protected Iterable<List<String>> loadValue() {
                return () -> new Iterator<>() {
                    private boolean loadedRow;

                    {
                        try {
                            loadedRow = resultSet.next();
                        } catch (SQLException exception) {
                            loadedRow = false;
                            exception.printStackTrace();
                        }
                    }

                    @Override
                    public boolean hasNext() {
                        return loadedRow;
                    }

                    @Override
                    public List<String> next() {
                        if (!loadedRow) {
                            return new ArrayList<>(count);
                        }
                        try {
                            final List<String> list = new ArrayList<>(count);
                            for (int i = 1; i <= count; i++) {
                                list.add(resultSet.getString(i));
                            }
                            loadedRow = resultSet.next();
                            return list;
                        } catch (SQLException e) {
                            e.printStackTrace();
                            return new ArrayList<>();
                        }
                    }
                };
            }
        });
    }

}
