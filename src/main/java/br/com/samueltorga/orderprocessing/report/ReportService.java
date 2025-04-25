package br.com.samueltorga.orderprocessing.report;

import br.com.samueltorga.orderprocessing.report.strategies.ReportFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ReportFactory reportFactory;

    public void generateReport(ReportType reportType, LocalDate startDate, LocalDate endDate, OutputStream outputStream) {
        reportFactory.getReportStrategy(reportType).generateReport(startDate, endDate, outputStream);
    }
}
