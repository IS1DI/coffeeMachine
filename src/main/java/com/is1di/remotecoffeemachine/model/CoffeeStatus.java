package com.is1di.remotecoffeemachine.model;

import java.time.Duration;

public interface CoffeeStatus extends Status {
    Duration getDuration();
}
