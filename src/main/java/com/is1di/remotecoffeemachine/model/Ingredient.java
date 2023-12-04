package com.is1di.remotecoffeemachine.model;

import com.is1di.remotecoffeemachine.config.UnitConverter;

public interface Ingredient {
    UnitConverter.Unit getUnit();

    Double getAmount();

    String getIngredient();

    /*default double fromUnit() {
        switch (getUnit().getType()) {
            case MASS -> {
                try {
                    return UnitConverter.convert(getAmount(), getUnit(), UnitConverter.Unit.GRAM);
                } catch (UnitConverter.IncompatibleUnitTypesException e) {
                    throw new RuntimeException(e);
                }
            }
            case VOLUME -> {
                try {
                    return UnitConverter.convert(getAmount(), getUnit(), UnitConverter.Unit.MILLILITRE);
                } catch (UnitConverter.IncompatibleUnitTypesException e) {
                    throw new RuntimeException(e);
                }
            }
            case TEMPERATURE -> {
                try {
                    return UnitConverter.convert(getAmount(), getUnit(), UnitConverter.Unit.CELSIUS);
                } catch (UnitConverter.IncompatibleUnitTypesException e) {
                    throw new RuntimeException(e);
                }
            }
            default -> {
                return 0.0d;
            }
        }
    }*/
}

