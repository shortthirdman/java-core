import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class I18NHelper {

  public static String formatCurrency(double amount, int precision, String pattern, Locale locale) {
    NumberFormat nf = NumberFormat.getCurrencyInstance(locale);
    DecimalFormat df = (DecimalFormat) nf;
    df.setMinimumFractionDigits(precision);
    df.setMaximumFractionDigits(precision);
    df.setDecimalSeparatorAlwaysShown(true);
    df.applyPattern(pattern);
    return df.format(amount);
  }

  public static String formatNumber(double amount, int precision, String pattern, Locale locale) {
    NumberFormat nf = NumberFormat.getNumberInstance(locale);
    DecimalFormat df = (DecimalFormat) nf;
    df.setMinimumFractionDigits(precision);
    df.setMaximumFractionDigits(precision);
    df.setDecimalSeparatorAlwaysShown(true);
    df.applyPattern(pattern);
    return df.format(amount);
  }

  public static String formatCurrency(double amount, int precision, Locale locale) {
    NumberFormat nf = NumberFormat.getCurrencyInstance(locale);
    nf.setMinimumFractionDigits(precision);
    nf.setMaximumFractionDigits(precision);
    return nf.format(amount);
  }

  public static String formatNumber(double amount, int precision, Locale locale) {
    NumberFormat nf = NumberFormat.getNumberInstance(locale);
    nf.setMinimumFractionDigits(precision);
    nf.setMaximumFractionDigits(precision);
    return nf.format(amount);
  }
}