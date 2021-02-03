package com.report.datamodel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
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





