package com.is1di.remotecoffeemachine.message;

import lombok.Getter;

public record MessageBase(Message method, Object... args) {

    @Getter
    public enum MessageMethod implements Message {
        COFFEE_NOT_FOUND("coffee.error.notFound"),
        ORDER_NOT_FOUND("order.error.notFound"),
        ORDER_LIMIT_OUT_OF_BOUNDS("order.limit.error.outOfBounds"),
        MACHINE_IS_NOT_ENABLED("machine.error.isNotEnabled"),
        MACHINE_IS_NOT_READY("machine.error.notReady");

        private final String message;

        MessageMethod(String message) {
            this.message = message;
        }

        @Getter
        public enum Units implements Message {
            ERROR_NOT_FOUND("unit.error.notFound"),
            LITRE("unit.volume.litre"),
            KELVIN("unit.temp.kelvin"),
            KILOGRAM("unit.volume.kilogram"),
            FAHRENHEIT("unit.temp.fahrenheit"),
            CELSIUS("unit.temp.celsium"),
            OUNCE("unit.mass.ounce"),
            POUND("unit.mass.pound"),
            STONE("unit.mass.stone"),
            IMPERIAL_TON("unit.mass.imp_tonne"),
            GRAM("unit.mass.gram"),
            TONNE("unit.mass.tonne"),
            FLUID("unit.volume.fluid"),
            PINT("unit.volume.pint"),
            QUART("unit.volume.quart"),
            GALLON("unit.volume.gallon"),
            MILLILITRE("unit.volume.millilitre");
            private final String message;

            Units(String message) {
                this.message = message;
            }
        }
    }

    public interface Message {
        String getMessage();
    }
}
