package com.is1di.remotecoffeemachine.config;

import com.is1di.remotecoffeemachine.exception.NotFoundException;
import com.is1di.remotecoffeemachine.message.MessageBase;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;


/**
 * This a utility to convert units
 * <p>
 * Usage:
 * <pre>
 * {@code
 * double result = UsageConverter.convert(double amount, Unit unitfrom, Unit to);
 * }
 * </pre>
 * <p>
 * Units are only convertable to other units in the same category (UnitType)
 *
 * @author fusionlightcat / Enveed / Arthur Schüler (C) 2017
 * <p>
 * You are free to use this file in any project, as long you do not claim it as your own or remove this notice.
 */
public class UnitConverter {
    /**
     * Convert a unit to another
     *
     * @param amount the number
     * @param from   the unit the number is in
     * @param to     the unit the number should be converted to
     * @return the converted amount
     * @throws IncompatibleUnitTypesException when the units have different UnitTypes (are not from the same category)
     */
    public static double convert(double amount, Unit from, Unit to) throws IncompatibleUnitTypesException {
        if (!from.getType().equals(to.getType())) {
            throw new IncompatibleUnitTypesException();
        }

        double based;
        if (from.isBase()) {
            based = amount;
        } else {
            based = from.getTo().convert(amount);
        }

        double result;
        if (to.isBase()) {
            result = based;
        } else {
            result = to.getFrom().convert(based);
        }

        return result;
    }

    @Getter
    public enum Unit {

        //BASE UNITS
        KELVIN(UnitType.TEMPERATURE, MessageBase.MessageMethod.Units.KELVIN, UnitSystem.METRIC, "Kelvin", new Aliases("°k", "kelvin")),
        KILOGRAM(UnitType.MASS, MessageBase.MessageMethod.Units.KILOGRAM, UnitSystem.METRIC, "Kilogram", new Aliases("kilos", "kilo", "kilogram", "kgs", "kilograms")),
        LITRE(UnitType.VOLUME, MessageBase.MessageMethod.Units.LITRE, UnitSystem.METRIC, "Litre", new Aliases("litre", "litres", "liter", "liters")),
        //Temperature
        FAHRENHEIT(UnitType.TEMPERATURE, MessageBase.MessageMethod.Units.FAHRENHEIT, UnitSystem.IMPERIAL, "Fahrenheit", Unit.KELVIN, amount -> (5.0d / 9.0d * (amount - 32.0d)) + 273.0d, amount -> (9.0d / 5.0d) * (amount - 273.0d) + 32.0d, new Aliases("°F", "fahrenheit", "farenheit")),
        CELSIUS(UnitType.TEMPERATURE, MessageBase.MessageMethod.Units.CELSIUS, UnitSystem.METRIC, "Celsius", Unit.KELVIN, amount -> amount + 273.15d, amount -> amount - 273.15d, new Aliases("°C", "celsius")),
        //MASS
        OUNCE(UnitType.MASS, MessageBase.MessageMethod.Units.OUNCE, UnitSystem.IMPERIAL, "Ounce", Unit.KILOGRAM, 0.0283495d, 35.274d, new Aliases("ounce", "ounces")),
        POUND(UnitType.MASS, MessageBase.MessageMethod.Units.POUND, UnitSystem.IMPERIAL, "Pound", Unit.KILOGRAM, 0.453592d, 2.20462d, new Aliases("pounds", "lbs", "pound")),
        STONE(UnitType.MASS, MessageBase.MessageMethod.Units.STONE, UnitSystem.IMPERIAL, "Stone", Unit.KILOGRAM, 6.35029d, 0.157473d, new Aliases("stone", "stones", "sts")),
        IMPERIAL_TON(UnitType.MASS, MessageBase.MessageMethod.Units.IMPERIAL_TON, UnitSystem.IMPERIAL, "Imperial Tonne", Unit.KILOGRAM, 0.157473d, 0.000984207d),
        GRAM(UnitType.MASS, MessageBase.MessageMethod.Units.GRAM, UnitSystem.METRIC, "Gram", Unit.KILOGRAM, 0.001d, 1000.0d, new Aliases("gram", "grams", "gramm", "gramms")),
        TONNE(UnitType.MASS, MessageBase.MessageMethod.Units.TONNE, UnitSystem.METRIC, "Tonne", Unit.KILOGRAM, 1000.0d, 0.001d, new Aliases("ton", "tons", "tonnes", "tns")),
        //VOLUME
        FLUID_OUNCE(UnitType.VOLUME, MessageBase.MessageMethod.Units.FLUID, UnitSystem.IMPERIAL, "Fluid ounce", Unit.LITRE, 0.0284131d, 35.1951d),
        PINT(UnitType.VOLUME, MessageBase.MessageMethod.Units.PINT, UnitSystem.IMPERIAL, "Pint", Unit.LITRE, 0.568261d, 1.75975d, new Aliases("pint", "pints", "pts")),
        QUART(UnitType.VOLUME, MessageBase.MessageMethod.Units.QUART, UnitSystem.IMPERIAL, "Quart", Unit.LITRE, 1.13652d, 0.879877d, new Aliases("quart", "quarts")),
        GALLON(UnitType.VOLUME, MessageBase.MessageMethod.Units.GALLON, UnitSystem.IMPERIAL, "Gallon", Unit.LITRE, 4.54609d, 0.219969d, new Aliases("gallon", "gallons")),
        MILLILITRE(UnitType.VOLUME, MessageBase.MessageMethod.Units.MILLILITRE, UnitSystem.METRIC, "Millilitre", Unit.LITRE, 0.001d, 1000.0d, new Aliases("millilitres", "milliliters", "milliliter", "millilitre")),
        ;

        static {
            KILOGRAM.equivalent = POUND;
            LITRE.equivalent = PINT;

            FAHRENHEIT.equivalent = CELSIUS;
            CELSIUS.equivalent = FAHRENHEIT;

            OUNCE.equivalent = GRAM;
            POUND.equivalent = KILOGRAM;
            STONE.equivalent = KILOGRAM;
            IMPERIAL_TON.equivalent = TONNE;
            GRAM.equivalent = OUNCE;
            TONNE.equivalent = IMPERIAL_TON;

            FLUID_OUNCE.equivalent = MILLILITRE;
            PINT.equivalent = LITRE;
            QUART.equivalent = LITRE;
            GALLON.equivalent = LITRE;
            MILLILITRE.equivalent = FLUID_OUNCE;
        }

        @Getter
        private final UnitType type;
        private final MessageBase unit;
        private final UnitSystem system;
        private final String name;
        private final Unit reference;
        private final Converter to;
        private final Converter from;
        private final boolean base;
        private final Aliases aliases;
        private Unit equivalent;

        /**
         * Creates a new Unit
         *
         * @param type      unit category
         * @param unit      short name for this unit
         * @param system    unit system to use (imperial/metric)
         * @param name      unit name
         * @param reference the base unit of this category to convert to
         * @param multtoref the amount to multiply the amount with to convert it to the base unit
         */
        Unit(UnitType type, MessageBase.MessageMethod.Units unit, UnitSystem system, String name, Unit reference, double multtoref, double multfromref, Aliases aliases) {
            this(type, unit, system, name, reference, new MultiplicationConverter(multtoref), new MultiplicationConverter(multfromref), aliases);
        }

        /**
         * Creates a new Unit
         *
         * @param type      unit category
         * @param unit      short name for this unit
         * @param system    unit system to use (imperial/metric)
         * @param name      unit name
         * @param reference the base unit of this category to convert to
         * @param multtoref the amount to multiply the amount with to convert it to the base unit
         */
        Unit(UnitType type, MessageBase.MessageMethod.Units unit, UnitSystem system, String name, Unit reference, double multtoref, double multfromref) {
            this(type, unit, system, name, reference, new MultiplicationConverter(multtoref), new MultiplicationConverter(multfromref), null);
        }

        /**
         * Creates a new Unit
         *
         * @param type      unit category
         * @param unit      short name for this unit
         * @param system    unit system to use (imperial/metric)
         * @param name      unit name
         * @param reference the base unit of this category to convert to
         */
        Unit(UnitType type, MessageBase.MessageMethod.Units unit, UnitSystem system, String name, Unit reference, Converter converterto, Converter converterfrom) {
            this(type, unit, system, name, reference, converterto, converterfrom, null);
        }

        /**
         * Creates a new Unit
         *
         * @param type      unit category
         * @param unit      short name for this unit
         * @param system    unit system to use (imperial/metric)
         * @param name      unit name
         * @param reference the base unit of this category to convert to
         */
        Unit(UnitType type, MessageBase.MessageMethod.Units unit, UnitSystem system, String name, Unit reference, Converter converterto, Converter converterfrom, Aliases aliases) {
            this.type = type;
            this.unit = new MessageBase(unit);
            this.system = system;
            this.name = name;
            this.reference = reference;
            this.to = converterto;
            this.from = converterfrom;
            base = false;
            this.aliases = aliases;
        }

        /**
         * Creates a new Base Unit
         *
         * @param type   unit category
         * @param unit   short name for this unit
         * @param system unit system to use (imperial/metric)
         * @param name   unit name
         */
        Unit(UnitType type, MessageBase.MessageMethod.Units unit, UnitSystem system, String name, Aliases aliases) {
            this(type, unit, system, name, null, null, null, aliases);
        }

        public static Unit getBySymbol(String sym) {
            for (Unit unit : Unit.values()) {
                if (unit.getSymbol().method().getMessage().equalsIgnoreCase(sym)) return unit;
            }
            return null;
        }

        public static Collection<Unit> getByType(UnitType type) {
            Collection<Unit> col = new ArrayList<>();
            for (Unit unit : Unit.values()) {
                if (unit.getType().equals(type)) col.add(unit);
            }
            return col;
        }

        public static Unit getByName(String name) {
            return Arrays.stream(Unit.values())
                    .filter(it -> it.name.equals(name))
                    .findFirst()
                    .orElseThrow(() -> new NotFoundException(new MessageBase(MessageBase.MessageMethod.Units.ERROR_NOT_FOUND)));
        }

        public static Unit getByAlias(String alias) {
            for (Unit unit : Unit.values()) {
                if (unit.hasAlias(alias.toLowerCase())) return unit;
            }
            return null;
        }

        public static Unit getByTyped(String typed) {
            Unit unit = getBySymbol(typed);
            if (unit != null) return unit;
            unit = getByAlias(typed);
            return unit;
        }

        public MessageBase getSymbol() {
            return unit;
        }

        public boolean hasAlias(String al) {
            if (aliases == null) return false;
            else return aliases.contains(al);
        }
    }

    @Getter
    public enum UnitType {
        TEMPERATURE("Temperature"), VOLUME("Volume"), MASS("Mass"),
        ;

        private final String name;

        UnitType(String name) {
            this.name = name;
        }
    }

    @Getter
    public enum UnitSystem {
        IMPERIAL("Imperial"), METRIC("Metric"),
        ;

        private final String name;

        UnitSystem(String name) {
            this.name = name;
        }
    }

    // METHODS

    private interface Converter {
        double convert(double amount);
    }

    // CONVERTERS

    @Getter
    public static class Aliases {
        String[] aliases;

        public Aliases(String... aliases) {
            this.aliases = aliases;
        }

        public Collection<String> getAliasesCollection() {
            return Arrays.asList(aliases);
        }

        public boolean contains(String text) {
            return getAliasesCollection().contains(text);
        }
    }

    private static class MultiplicationConverter implements Converter {
        private final double fac;

        public MultiplicationConverter(double factor) {
            this.fac = factor;
        }

        @Override
        public double convert(double amount) {
            return amount * fac;
        }
    }

    //EXCEPTION
    public static class IncompatibleUnitTypesException extends Exception {
        public IncompatibleUnitTypesException(String message) {
            super(message);
        }

        public IncompatibleUnitTypesException() {
            super();
        }
    }
}
