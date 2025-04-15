package br.com.samueltorga.orderprocessing.mapper;

import br.com.samueltorga.orderprocessing.controller.dto.ListTestRDTO;
import br.com.samueltorga.orderprocessing.controller.dto.TestCreateRDTO;
import br.com.samueltorga.orderprocessing.repository.Test;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface TestMapper {

    @Mapping(target = "id", ignore = true)
    Test createTestEntity(TestCreateRDTO testCreateRDTO);

    ListTestRDTO toListTestRDTO(Test test);

}
