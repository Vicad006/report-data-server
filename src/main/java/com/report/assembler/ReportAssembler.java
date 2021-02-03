package com.report.assembler;

import com.report.controller.ReportController;
import com.report.datamodel.Report;
import com.report.dto.ReportDto;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReportAssembler extends RepresentationModelAssemblerSupport<Report, ReportDto> {

    public ReportAssembler(){
        super(ReportController.class, ReportDto.class);
    }

    @Override
    public ReportDto toModel(Report report) {
       return ReportDto.builder()
                .children(report.getChildren())
                .serviceType(report.getServiceType())
                .ministerName(report.getMinisterName())
                .build()
                .add(linkTo(
                        methodOn(ReportController.class)
                                .getOneReport(report.getId())).withSelfRel());

    }

//    @Override
//    public CollectionModel<ReportDto> toCollectionModel(Iterable<? extends Report> reports) {
//        CollectionModel<ReportDto> reportDtos = super.toCollectionModel(reports);
//        reportDtos.add(linkTo(methodOn(ReportController.class).findPaginated()))
//        return null;
//    }


    public static Report assemble(ReportDto reportDto) {
        return Report.builder()
                .serviceType(reportDto.getServiceType())
                .location(reportDto.getLocation())
                .date(reportDto.getDate())
                .ministerName(reportDto.getMinisterName())
                .children(reportDto.getChildren())
                .build();
    }

    public static List<ReportDto> disassemble(Page<Report> reportPage){
        List<ReportDto> reportDtos = new ArrayList<>();
        reportPage.forEach(report -> {
            ReportDto reportDto = ReportDto.builder()
                    .children(report.getChildren())
                    .serviceType(report.getServiceType())
                    .ministerName(report.getMinisterName())
                    .build();
            reportDtos.add(reportDto);
        });
        return reportDtos;
    }
}
