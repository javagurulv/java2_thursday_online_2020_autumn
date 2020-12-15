package lv.estore.app.core.validators;

import java.math.BigDecimal;

public class Utils {
    private static final String PRICE_REGEX = "^(0|\\+?[1-9]\\d*)$[.][0-9]{2}";

    public static boolean isEmptyField (final String value) {
         return value == null || value.length() == 0;
    }

    public static boolean isEmptyPrice(final BigDecimal value) {
        return value == null || value.equals(BigDecimal.ZERO);
    }

    public static boolean isValidPrice(final BigDecimal value) {
        boolean isValid = false;
        if (!isEmptyPrice(value)) {
            String stringValue = value.stripTrailingZeros().toPlainString();
            isValid = stringValue.matches(PRICE_REGEX);
        }
        return isValid;
    }

    public static boolean isEmptyId(final Long value) {
        return value == null;
    }

    public static boolean isValidId(final Long value) {
        boolean isValid = false;
        if (!isEmptyId(value)) {
            isValid = value > 0;
        }
        return isValid;
    }

    public static boolean isValidPagingParameters(final Integer value) {
        return value >= 0;
    }

    public static boolean isValidOrderBy(final String value) {
        boolean isValid = false;
        if (!isEmptyField(value)) {
            isValid = "name".equalsIgnoreCase(value) || "price".equalsIgnoreCase(value);
        }
        return isValid;
    }

    public static boolean isValidDirection(final String value) {
        boolean isValid = false;
        if (!isEmptyField(value)) {
            isValid = "desc".equalsIgnoreCase(value) || "asc".equalsIgnoreCase(value);
        }
        return isValid;
    }
}
