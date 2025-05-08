package br.com.samueltorga.orderprocessing.report.strategies;

import br.com.samueltorga.orderprocessing.exceptions.InternalServerErrorException;
import br.com.samueltorga.orderprocessing.model.Order;
import br.com.samueltorga.orderprocessing.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.OutputStream;
import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class CompleteReportStrategy implements ReportStrategy {

    private final OrderRepository orderRepository;

    @Override
    @Transactional(readOnly = true)
    public void generateReport(LocalDate startDate, LocalDate endDate, OutputStream outputStream) {
        AtomicInteger rowCount = new AtomicInteger(1);
        AtomicInteger columnCount = new AtomicInteger(1);
        try (Workbook workbook = new SXSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("test");
            try (Stream<Order> ordersForReportStream = orderRepository.findOrdersForReportStream(startDate.atStartOfDay(), endDate.atTime(23, 59, 59))) {
                ordersForReportStream
                        .forEach(order -> {
                            Row row = sheet.createRow(rowCount.getAndIncrement());
                            row.createCell(columnCount.getAndIncrement()).setCellValue(order.getId());
                            row.createCell(columnCount.getAndIncrement()).setCellValue(order.getCustomer().getName());
                            row.createCell(columnCount.getAndIncrement()).setCellValue(order.getCustomer().getEmail());
                            row.createCell(columnCount.getAndIncrement()).setCellValue(order.getCreated());
                            order.getOrderItems().forEach(orderItem -> {
                                row.createCell(columnCount.getAndIncrement()).setCellValue(orderItem.getProduct().getName());
                                row.createCell(columnCount.getAndIncrement()).setCellValue(orderItem.getQuantity());
                                row.createCell(columnCount.getAndIncrement()).setCellValue(orderItem.getPrice().doubleValue());
                            });
                            columnCount.set(0);
                        });
            }
            workbook.write(outputStream);
        } catch (Exception e) {
            throw new InternalServerErrorException("Error generating report", e);
        }
    }
}
