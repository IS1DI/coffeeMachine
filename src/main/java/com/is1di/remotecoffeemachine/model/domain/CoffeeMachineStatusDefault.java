package com.is1di.remotecoffeemachine.model.domain;

import com.is1di.remotecoffeemachine.model.CoffeeMachineStatus;
import com.is1di.remotecoffeemachine.model.StatusImpl;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class CoffeeMachineStatusDefault extends StatusImpl implements CoffeeMachineStatus {
    public CoffeeMachineStatusDefault(String status, Integer order) {
        super(status, order);
    }
}
