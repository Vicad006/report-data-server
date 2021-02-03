package com.report.controller;

import com.report.assembler.ReportAssembler;
import com.report.datamodel.Report;
import com.report.dto.ReportDto;
import com.report.services.ReportService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.data.web.SortDefault;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class ReportController {

    private final ReportService reportService;
    private ReportAssembler reportAssembler;

    public ReportController(ReportService reportService,
                            ReportAssembler reportAssembler) {
        this.reportService = reportService;
        this.reportAssembler = reportAssembler;
    }

    @PostMapping("/reports")
    public ResponseEntity<Object> createReport(@RequestBody ReportDto reportDto) {
        Report report = ReportAssembler.assemble(reportDto);
        report = reportService.createReport(report);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(report.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/reports")
    public ResponseEntity<Object> getAllReports(PagedResourcesAssembler<Report> assembler,
                                         @SortDefault(sort = "date", direction = Sort.Direction.ASC)
                                         @PageableDefault(size = 10, page = 0) Pageable pageable) {

        Page<Report> reportPage = reportService.findAllReports(pageable);
        PagedModel<ReportDto> pagedModel = assembler.toModel(reportPage, reportAssembler);
        return new ResponseEntity<>(pagedModel, HttpStatus.OK);
    }

    @GetMapping("/reports/{id}")
    public ResponseEntity<Object> getOneReport(@PathVariable Integer id) {
        Optional<Report> report = reportService.getOneReport(id);
        if (report.isPresent()) {
            EntityModel<Report> resource = EntityModel.of(report.get());
            resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ReportController.class)).withSelfRel());
            return new ResponseEntity<>(resource, HttpStatus.OK);

        }
        return new ResponseEntity<>("Report " + id + " not found", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/reports/bulk")
    public ResponseEntity<Object> createReports(@RequestBody List<Report> reports) {
        List<Report> reportList = reportService.createListOfReports(reports);
        return new ResponseEntity<>(reportList, HttpStatus.OK);
    }

    @DeleteMapping("reports/{id}")
    public void removeReport(@PathVariable Integer id) {
        reportService.deleteReport(id);
    }

    @PutMapping("/reports/{id}")
    public ResponseEntity<Object> updateReport(@RequestBody Report report, @PathVariable Integer id) {
        if (!reportService.checkExistence(id))
            return new ResponseEntity<>("Report of " + id + "not found ", HttpStatus.NOT_FOUND);
        report.setId(id);
        reportService.createReport(report);
        return ResponseEntity.noContent().build();
    }

}
