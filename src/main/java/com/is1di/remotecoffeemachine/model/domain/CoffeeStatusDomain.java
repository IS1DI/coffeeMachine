package com.is1di.remotecoffeemachine.model.domain;

import com.is1di.remotecoffeemachine.model.CoffeeStatus;
import com.is1di.remotecoffeemachine.model.StatusImpl;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.Duration;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class CoffeeStatusDomain extends StatusImpl implements CoffeeStatus {
    private Duration duration;

    public CoffeeStatusDomain(String status, Integer order, Duration duration) {
        super(status, order);
        this.duration = duration;
    }
}
