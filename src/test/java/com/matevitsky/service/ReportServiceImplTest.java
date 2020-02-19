package com.matevitsky.service;

import com.matevitsky.entity.Report;
import com.matevitsky.entity.ReportStatus;
import com.matevitsky.repository.interfaces.ReportRepository;
import com.matevitsky.service.interfaces.ReportService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static junit.framework.TestCase.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ReportServiceImplTest {

    @Mock
    private ReportRepository reportRepository;


    @Test
    public void changeStatusToInProgress() {

        Report report = Report.newBuilder().withStatus(ReportStatus.ACCEPTED).build();

        ReportService reportService = new ReportServiceImpl(reportRepository);


        Report actual = reportService.changeStatusToInProgress(report);

        assertEquals(ReportStatus.IN_PROGRESS, actual.getStatus());
    }
}
