package com.report.services;

import com.report.datamodel.Report;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ReportService {

     Report createReport(Report report);

     Page<Report> findAllReports(Pageable pageable);

     Optional<Report> getOneReport(Integer id);

     void deleteReport(Integer id);

     Boolean checkExistence(Integer id);

     long countRecord();

     List<Report> createListOfReports(List<Report> reports);
}
