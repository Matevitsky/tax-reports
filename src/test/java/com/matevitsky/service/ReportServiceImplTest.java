package com.matevitsky.service;

import com.matevitsky.entity.Report;
import com.matevitsky.entity.ReportStatus;
import com.matevitsky.repository.interfaces.ReportRepository;
import com.matevitsky.service.interfaces.ReportService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Connection;
import java.sql.SQLException;

import static junit.framework.TestCase.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ReportServiceImplTest {

    @Mock
    private ReportRepository reportRepository;

    @Mock
    private Connection connection;

    @Test
    public void changeStatusToInProgress() throws SQLException {
        Report report = Report.newBuilder()
            .withStatus(ReportStatus.ACCEPTED).build();
        Report expected = Report.newBuilder()
            .withStatus(ReportStatus.IN_PROGRESS).build();
        //doNothing().when(reportRepository.update(report, connection));
        ReportService reportService = new ReportServiceImpl(reportRepository);
        Report actual = reportService.changeStatusToInProgress(report);

        assertEquals(expected, actual);
    }
}
