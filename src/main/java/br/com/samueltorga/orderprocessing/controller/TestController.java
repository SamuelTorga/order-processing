package br.com.samueltorga.orderprocessing.controller;

import br.com.samueltorga.orderprocessing.controller.dto.ListTestRDTO;
import br.com.samueltorga.orderprocessing.controller.dto.TestCreateRDTO;
import br.com.samueltorga.orderprocessing.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController("test")
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @GetMapping
    public Page<ListTestRDTO> findAll(Pageable pageable) {
        return testService.getAllTests(pageable);
    }

    @PostMapping
    public ListTestRDTO save(@RequestBody TestCreateRDTO testCreateRDTO) {
        return testService.saveTestEntity(testCreateRDTO);
    }

}
