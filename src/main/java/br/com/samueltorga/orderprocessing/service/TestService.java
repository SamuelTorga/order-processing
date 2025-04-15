package br.com.samueltorga.orderprocessing.service;

import br.com.samueltorga.orderprocessing.controller.dto.ListTestRDTO;
import br.com.samueltorga.orderprocessing.controller.dto.TestCreateRDTO;
import br.com.samueltorga.orderprocessing.mapper.TestMapper;
import br.com.samueltorga.orderprocessing.repository.Test;
import br.com.samueltorga.orderprocessing.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TestService {

    private final TestRepository testRepository;
    private final TestMapper testMapper;

    @Transactional
    public ListTestRDTO saveTestEntity(TestCreateRDTO testCreateRDTO) {
        Test testEntity = testMapper.createTestEntity(testCreateRDTO);
        Test saved = testRepository.save(testEntity);
        return testMapper.toListTestRDTO(saved);
    }

    @Transactional(readOnly = true)
    public Page<ListTestRDTO> getAllTests(Pageable pageable) {
        return testRepository.findAll(pageable).map(testMapper::toListTestRDTO);
    }

}
