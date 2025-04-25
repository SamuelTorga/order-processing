package br.com.samueltorga.orderprocessing.report.strategies;

import br.com.samueltorga.orderprocessing.report.ReportType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReportFactory {

    private final CompleteReportStrategy completeReportStrategy;
    private final ProductsReportStrategy productsReportStrategy;
    private final CustomersReportStrategy customersReportStrategy;

    public ReportStrategy getReportStrategy(ReportType reportType) {
        return switch (reportType) {
            case COMPLETE -> completeReportStrategy;
            case PRODUCTS -> productsReportStrategy;
            case CUSTOMERS -> customersReportStrategy;
        };
    }
}
