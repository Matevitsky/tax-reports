package com.matevitsky.repository.implementation;

import com.matevitsky.dto.ReportWithClientName;
import com.matevitsky.entity.Employee;
import com.matevitsky.entity.EmployeeRole;
import com.matevitsky.entity.ReportStatus;
import com.matevitsky.repository.interfaces.InspectorRepository;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.matevitsky.controller.constant.ParameterConstant.*;

public class InspectorRepositoryImpl extends CrudRepositoryImpl<Employee> implements InspectorRepository {

    private static final Logger LOGGER = Logger.getLogger(InspectorRepositoryImpl.class);

    private static final String CREATE_INSPECTOR_SQL = "INSERT INTO employees " +
            "(employee_first_name, employee_last_name, employee_email, password," +
            " role) VALUES ('%s', '%s', '%s', '%s','%s');";


    private static final String DELETE_INSPECTOR_SQL = "DELETE FROM employees WHERE employee_id=%d";

    private static final String UPDATE_INSPECTOR_SQL =
            "UPDATE employees SET employee_first_name='%s', employee_last_name='%s',employee_email='%s', password='%s', role='%s" +
                    "where employee_id=%d";

    private static final String SELECT_INSPECTOR_BY_ID_SQL = "SELECT * FROM employees WHERE employee_id='%d'";
    private static final String SELECT_ALL_INSPECTORS_SQL = "SELECT * FROM employees WHERE employee_role ='INSPECTOR'";

    private static final String SELECT_INSPECTOR_BY_EMAIL_SQL = "SELECT * FROM employees WHERE employee_email='%s'";
    private static final String SELECT_INSPECTOR_REPORTS_SQL =
            "SELECT report_id,tittle,clients.client_first_name,clients.client_last_name,clients.client_id," +
                "clients.employee_id,report_status\n" +
                    "FROM clients,reports\n" +
                "WHERE reports.client_id=clients.client_id AND clients.employee_id='%d'";



    @Override
    protected String getCreateQuery(Employee inspector) {
        return String.format(CREATE_INSPECTOR_SQL, inspector.getFirstName(), inspector.getLastName(), inspector.getEmail(),
                inspector.getPassword(), inspector.getEmployeeRole());

    }

    @Override
    protected String getDeleteByIdQuery(int id) {
        return String.format(DELETE_INSPECTOR_SQL, id);
    }

    @Override
    protected String getUpdateQuery(Employee inspector) {
        return String.format(UPDATE_INSPECTOR_SQL, inspector.getFirstName(), inspector.getLastName(),
                inspector.getEmail(), inspector.getPassword(), inspector.getEmployeeRole(), inspector.getId());

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
    protected List<Employee> mapToList(ResultSet resultSet) {

        List<Employee> allInspectorList = new ArrayList<>();
        Employee inspector;

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
    protected Employee mapToObject(ResultSet resultSet) {
        Employee inspector = null;

        try {
            if (resultSet.isBeforeFirst()) {
                resultSet.next();
            }
            int id = resultSet.getInt(EMPLOYEE_ID);
            String firstName = resultSet.getString(EMPLOYEE_FIRST_NAME);
            String lastName = resultSet.getString(EMPLOYEE_LAST_NAME);
            String email = resultSet.getString(EMPLOYEE_EMAIL);
            String password = resultSet.getString(PASSWORD);
            String role = resultSet.getString(EMPLOYEE_ROLE);

            inspector = Employee.newBuilder()
                    .withId(id)
                    .withFirstName(firstName)
                    .withLastName(lastName)
                    .withEmail(email)
                    .withPassword(password)
                    .withEmployeeRole(EmployeeRole.valueOf(role))
                    .build();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return inspector;
    }

    @Override
    public Optional<Employee> findByEmail(String email, Connection connection) {

        String sqlQuery = String.format(SELECT_INSPECTOR_BY_EMAIL_SQL, email);

        try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            ResultSet resultSet = statement.executeQuery();
            return Optional.ofNullable(mapToObject(resultSet));

        } catch (SQLException e) {
            LOGGER.error("Failed to get entity by ID " + e.getMessage());
        }
        return Optional.empty();

    }

    @Override
    public Optional<List<ReportWithClientName>> getAllClientReports(int inspectorId, Connection connection) {

        String sqlQuery = String.format(SELECT_INSPECTOR_REPORTS_SQL, inspectorId);

        List<ReportWithClientName> inspectorReportList = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            ResultSet resultSet = statement.executeQuery();
            ReportWithClientName reportWithClientName;

            try {
                while (resultSet.next()) {
                    reportWithClientName = mapToReportWithClientName(resultSet);
                    if (reportWithClientName != null) {
                        inspectorReportList.add(reportWithClientName);
                    }
                }

            } catch (SQLException e) {
                LOGGER.warn("GetAll method return empty RowSet");
            }

        } catch (SQLException e) {
            LOGGER.error("Failed to get entity by ID " + e.getMessage());
        }
        return Optional.of(inspectorReportList);
    }


    private ReportWithClientName mapToReportWithClientName(ResultSet resultSet) {

        ReportWithClientName report = null;

        try {
            int reportId = resultSet.getInt(DB_REPORT_ID);
            String clientFirstName = resultSet.getString(CLIENT_FIRST_NAME);
            String clientLastName = resultSet.getString(CLIENT_LAST_NAME);
            String tittle = resultSet.getString(TITTLE);
            String reportStatus = resultSet.getString(REPORT_STATUS);
            report = new ReportWithClientName(reportId, tittle, clientFirstName, clientLastName,
                    ReportStatus.valueOf(reportStatus));

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return report;
    }
}
