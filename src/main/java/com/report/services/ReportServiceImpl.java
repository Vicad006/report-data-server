package com.report.services;

import com.report.datamodel.Report;
import com.report.repository.ReportRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;

    public ReportServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Override
    public Page<Report> findAllReports(Pageable pageable){
        return reportRepository.findAll(pageable);
    }

    @Override
    public Report createReport(Report report) {
        return reportRepository.save(report);
    }

    @Override
    public Optional<Report> getOneReport(Integer id) {
        return reportRepository.findById(id);
    }

    @Override
    public void deleteReport(Integer id) {
        reportRepository.deleteById(id);
    }

    @Override
    public Boolean checkExistence(Integer id) {
        return reportRepository.existsById(id);
    }

    @Override
    public long countRecord() {
        return reportRepository.count();
    }

    @Override
    public List<Report> createListOfReports(List<Report> reports) {
        return reportRepository.saveAll(reports);
    }
}
