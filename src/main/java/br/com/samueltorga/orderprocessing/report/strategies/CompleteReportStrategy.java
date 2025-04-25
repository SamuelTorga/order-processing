package br.com.samueltorga.orderprocessing.report.strategies;

import br.com.samueltorga.orderprocessing.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.OutputStream;
import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class CompleteReportStrategy implements ReportStrategy {

    private final OrderRepository orderRepository;

    @Override
    public void generateReport(LocalDate startDate, LocalDate endDate, OutputStream outputStream) {

    }
}
