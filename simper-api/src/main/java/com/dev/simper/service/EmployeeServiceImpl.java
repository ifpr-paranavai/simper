package com.dev.simper.service;

import java.util.List;
import java.util.Locale;

import org.springframework.context.MessageSource;

import com.dev.simper.dto.EmployeeDto;
import com.dev.simper.exception.ResourceNotFoundException;
import com.dev.simper.model.Employee;
import com.dev.simper.repository.EmployeeRepository;
import com.dev.simper.utils.ParseUtils;
import org.springframework.stereotype.Service;


public class EmployeeServiceImpl implements EmployeeService {

  private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, MessageSource messageSource) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<EmployeeDto> findAll() {
        return ParseUtils.parse(employeeRepository.findAll(), EmployeeDto.class);
    }

    @Override
    public EmployeeDto findById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        messageSource.getMessage("user.notfound", new Object[] { id }, Locale.getDefault())));
        return ParseUtils.parse(employee, EmployeeDto.class);
    }

    @Override
    public EmployeeDto save(EmployeeDto dto) {
        return ParseUtils.parse(
          employeeRepository.saveAndFlush(ParseUtils.parse(dto, Employee.class)), 
            EmployeeDto.class);
    }

    @Override
    public List<EmployeeDto> saveAll(List<EmployeeDto> dtos) {
        return ParseUtils.parse(
          employeeRepository.saveAllAndFlush(ParseUtils.parse(dtos, Employee.class)), 
            EmployeeDto.class);
    }

    @Override
    public EmployeeDto update(EmployeeDto dto) {
        return ParseUtils.parse(
          employeeRepository.saveAndFlush(ParseUtils.parse(dto, Employee.class)), 
            EmployeeDto.class);
    }

    @Override
    public void delete(Long id) {
      employeeRepository.deleteById(id);
    }
  
}
