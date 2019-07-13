package com.matevitsky.repository.implementation;

import com.matevitsky.entity.Report;
import com.matevitsky.entity.ReportStatus;
import com.matevitsky.repository.interfaces.ReportRepository;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReportRepositoryImpl extends CrudRepositoryImpl<Report> implements ReportRepository {

    private static final Logger LOGGER = Logger.getLogger(ReportRepositoryImpl.class);

    private static final String CREATE_REPORT_SQL = "INSERT INTO reports (tittle, content, report_status, cancellation_reason, client_id) \n" +
        "VALUES ('%s', '%s', '%s', '%s','%d');";
    private static final String DELETE_REPORT_SQL = "DELETE FROM reports WHERE report_id=%d";

    private static final String UPDATE_REPORT_SQL =
        "UPDATE reports SET tittle='%s', content='%s', report_status='%s', cancellation_reason='%s'," +
            " client_id='%d' where report_id=%d";

    private static final String SELECT_REPORT_BY_ID_SQL = "SELECT * FROM reports WHERE report_id=%d";
    private static final String SELECT_ALL_REPORTS_SQL = "SELECT * FROM reports";

    @Override
    protected String getCreateQuery(Report report) {
        return String.format(CREATE_REPORT_SQL, report.getTittle(), report.getContent(), report.getCancellationReason(),
            report.getClientId());

    }

    @Override
    protected String getDeleteByIdQuery(int id) {
        return String.format(DELETE_REPORT_SQL, id);
    }

    @Override
    protected String getUpdateQuery(Report report) {
        return String.format(UPDATE_REPORT_SQL, report.getTittle(), report.getContent(), report.getStatus(),
            report.getCancellationReason(), report.getClientId(), report.getId());
    }

    @Override
    protected String getByIdQuery(int id) {
        return String.format(SELECT_REPORT_BY_ID_SQL, id);
    }

    @Override
    protected String getAllQuery() {
        return SELECT_ALL_REPORTS_SQL;
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
            if (!resultSet.first()) {
                resultSet.next();
            }
            Integer id = resultSet.getInt("report_id");
            String tittle = resultSet.getString("tittle");
            String content = resultSet.getString("content");
            String reportStatus = resultSet.getString("report_status");
            String cancellationReason = resultSet.getString("cancellation_reason");
            int clientId = resultSet.getInt("client_id");

            report = Report.newBuilder()
                .withId(id)
                .withTittle(tittle)
                .withContent(content)
                .withStatus(ReportStatus.valueOf(reportStatus))
                .withCancellationReason(cancellationReason)
                .withClientId(clientId)
                .build();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return report;
    }
}
