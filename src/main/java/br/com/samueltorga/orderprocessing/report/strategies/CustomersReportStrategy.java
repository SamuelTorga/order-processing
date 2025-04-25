package br.com.samueltorga.orderprocessing.report.strategies;

import org.springframework.stereotype.Component;

import java.io.OutputStream;
import java.time.LocalDate;

@Component
public class CustomersReportStrategy implements ReportStrategy {
    @Override
    public void generateReport(LocalDate startDate, LocalDate endDate, OutputStream outputStream) {

    }
}
