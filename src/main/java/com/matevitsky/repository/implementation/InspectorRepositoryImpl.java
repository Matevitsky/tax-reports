package com.matevitsky.repository.implementation;

import com.matevitsky.entity.Inspector;
import com.matevitsky.repository.interfaces.InspectorRepository;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InspectorRepositoryImpl extends CrudRepositoryImpl<Inspector> implements InspectorRepository {

    private static final Logger LOGGER = Logger.getLogger(InspectorRepositoryImpl.class);

    private static final String CREATE_INSPECTOR_SQL = "INSERT INTO inspectors (first_name, last_name, email, password, company_id, client_id) \n" +
        "VALUES ('%s', '%s', '%s', '%s','%d','%d');";


    private static final String DELETE_INSPECTOR_SQL = "DELETE FROM inspectors WHERE inspector_id=%d";

    private static final String UPDATE_INSPECTOR_SQL =
        "UPDATE inspectors SET first_name='%s', last_name='%s', email='%s', password='%s'," +
            " client_id='%d' where inspector_id=%d";

    private static final String SELECT_INSPECTOR_BY_ID_SQL = "SELECT * FROM inspectors WHERE inspector_id=%d";
    private static final String SELECT_ALL_INSPECTORS_SQL = "SELECT * FROM inspectors";

    @Override
    protected String getCreateQuery(Inspector inspector) {
        return String.format(CREATE_INSPECTOR_SQL, inspector.getFirstName(), inspector.getLastName(), inspector.getEmail(),
            inspector.getPassword(), inspector.getClientId());

    }

    @Override
    protected String getDeleteByIdQuery(int id) {
        return String.format(DELETE_INSPECTOR_SQL, id);
    }

    @Override
    protected String getUpdateQuery(Inspector inspector) {
        return String.format(UPDATE_INSPECTOR_SQL, inspector.getFirstName(), inspector.getLastName(),
            inspector.getEmail(), inspector.getPassword(), inspector.getId());

    }

    @Override
    protected String getByIdQuery(int id) {
        return String.format(SELECT_INSPECTOR_BY_ID_SQL, id);
    }

    @Override
    protected String getAllQuery() {
        return SELECT_ALL_INSPECTORS_SQL;
    }

    @Override
    protected List<Inspector> mapToList(ResultSet resultSet) {
        List<Inspector> allInspectorList = new ArrayList<>();
        Inspector inspector;
        try {
            while (resultSet.next()) {
                inspector = mapToObject(resultSet);
                allInspectorList.add(inspector);
            }
        } catch (SQLException e) {
            LOGGER.warn("GetAll method return empty RowSet");
        }

        return allInspectorList;
    }

    @Override
    protected Inspector mapToObject(ResultSet resultSet) {
        Inspector inspector = null;
        try {
            if (!resultSet.first()) {
                resultSet.next();
            }
            Integer id = resultSet.getInt("inspector_id");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String email = resultSet.getString("email");
            String password = resultSet.getString("password");
            int clientId = resultSet.getInt("client_id");

            inspector = Inspector.newBuilder()
                .withId(id)
                .withFirstName(firstName)
                .withLastName(lastName)
                .withEmail(email)
                .withPassword(password)
                .withClientId(clientId)
                .build();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return inspector;
    }
}
