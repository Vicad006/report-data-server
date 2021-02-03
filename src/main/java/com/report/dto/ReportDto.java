package com.report.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(callSuper = false)
@Relation(collectionRelation = "reports", itemRelation = "report")
public class ReportDto extends RepresentationModel<ReportDto> {

    private String serviceType;
    private String location;
    private String date;
    private String ministerName;
    private String connectedDevices;
    private String maleAdult;
    private String femaleAdult;
    private String children;
    private String income;
    private String expense;
    private String testimony;

}
