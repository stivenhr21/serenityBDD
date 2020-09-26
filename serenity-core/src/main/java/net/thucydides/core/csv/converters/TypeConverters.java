package net.thucydides.core.csv.converters;

import java.util.Arrays;
import java.util.List;

public class TypeConverters {
    private static final List<TypeConverter> DEFAULT_TYPE_CONVERTERS = Arrays.asList(
            new StringTypeConverter(),
            new IntegerTypeConverter(),
            new BigDecimalTypeConverter(),
            new BooleanTypeConverter()
    );

    public static List<TypeConverter> getDefaultTypeConverters() {
        return DEFAULT_TYPE_CONVERTERS;
    }


    public static TypeConverter getTypeConverterFor(Class<?> type) {
        for(TypeConverter typeConverter : DEFAULT_TYPE_CONVERTERS) {
            if (typeConverter.appliesTo(type)) {
                return typeConverter;
            }
        }
        throw new IllegalArgumentException("No applicable type converter found for " + type);
    }
}
