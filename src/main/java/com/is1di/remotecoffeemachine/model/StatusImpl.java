package com.is1di.remotecoffeemachine.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusImpl implements Status {
    private String status;
    private Integer order;
}
