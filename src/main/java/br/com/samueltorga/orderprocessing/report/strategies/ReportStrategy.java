package br.com.samueltorga.orderprocessing.report.strategies;

import java.io.OutputStream;
import java.time.LocalDate;

public interface ReportStrategy {

    void generateReport(LocalDate startDate, LocalDate endDate, OutputStream outputStream);
}
