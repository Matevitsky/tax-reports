package com.matevitsky.repository.implementation;

import com.matevitsky.entity.Report;
import com.matevitsky.entity.ReportStatus;
import com.matevitsky.repository.interfaces.ReportRepository;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.matevitsky.controller.constant.ParameterConstant.*;

public class ReportRepositoryImpl extends CrudRepositoryImpl<Report> implements ReportRepository {

    private static final Logger LOGGER = Logger.getLogger(ReportRepositoryImpl.class);

    private static final String CREATE_REPORT = "INSERT INTO reports (tittle, content, report_status, reason_to_reject, client_id) \n" +
            "VALUES ('%s', '%s', '%s', '%s','%d');";
    private static final String DELETE_REPORT = "DELETE FROM reports WHERE report_id='%d'";

    private static final String UPDATE_REPORT =
            "UPDATE reports SET tittle='%s', content='%s', report_status='%s', reason_to_reject='%s'," +
                    " client_id='%d' where report_id=%d";

    private static final String SELECT_REPORT_BY_ID = "SELECT * FROM reports WHERE report_id='%d'";
    private static final String SELECT_ALL_REPORTS = "SELECT * FROM reports";
    private static final String SELECT_ALL_BY_CLIENT_ID = "SELECT * FROM reports WHERE client_id='%d'";

    @Override
    protected String getCreateQuery(Report report) {
        return String.format(CREATE_REPORT, report.getTittle(), report.getContent(), report.getStatus(), report.getReasonToReject(),
                report.getClientId());

    }

    @Override
    protected String getDeleteByIdQuery(int id) {
        return String.format(DELETE_REPORT, id);
    }

    @Override
    protected String getUpdateQuery(Report report) {
        return String.format(UPDATE_REPORT, report.getTittle(), report.getContent(), report.getStatus(),
                report.getReasonToReject(), report.getClientId(), report.getId());
    }

    @Override
    protected String getByIdQuery(int id) {
        return String.format(SELECT_REPORT_BY_ID, id);
    }

    @Override
    protected String getAllQuery() {
        return SELECT_ALL_REPORTS;
    }

    @Override
    protected List<Report> mapToList(ResultSet resultSet) {

        List<Report> allReportList = new ArrayList<>();
        Report report;
        try {
            while (resultSet.next()) {
                report = mapToObject(resultSet);
                allReportList.add(report);
            }
        } catch (SQLException e) {
            LOGGER.warn("GetAll method return empty RowSet");
        }

        return allReportList;
    }

    @Override
    protected Report mapToObject(ResultSet resultSet) {

        Report report = null;
        try {
            if (resultSet.isBeforeFirst()) {
                resultSet.next();
            }
            int id = resultSet.getInt(DB_REPORT_ID);
            String tittle = resultSet.getString(TITTLE);
            String content = resultSet.getString(CONTENT);
            String reportStatus = resultSet.getString(REPORT_STATUS);
            String cancellationReason = resultSet.getString(DB_REASON_TO_REJECT);
            int clientId = resultSet.getInt(DB_CLIENT_ID);

            report = Report.newBuilder()
                    .withId(id)
                    .withTittle(tittle)
                    .withContent(content)
                    .withStatus(ReportStatus.valueOf(reportStatus))
                    .withreasonToReject(cancellationReason)
                    .withClientId(clientId)
                    .build();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return report;
    }

    @Override
    public Optional<List<Report>> getByClientId(int clientId, Connection connection) {

        String sqlQuery = String.format(SELECT_ALL_BY_CLIENT_ID, clientId);

        try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            ResultSet resultSet = statement.executeQuery();
            return Optional.ofNullable(mapToList(resultSet));
        } catch (SQLException e) {
            LOGGER.error("Failed to get entity by ID " + e.getMessage());
        }
        return Optional.empty();
    }


}
