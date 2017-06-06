package com.shortthirdman.core.common;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Utility class to manage localization resources. This class is not for public
 * usage but mainly for custom implementations developers to provide them
 * uniform access and organization of locale specific data.
 * <br>
 * Class usage is very simple. Put your locale sensible data into
 * <code>Resources.properties</code> files and save them near classes that you
 * are going to get localized. For {@link java.util.Locale} to file mapping
 * details see {@link ResourceBundle} documentation.
 * 
 * @author Swetank Mohanty (shortthirdman)
 * @version 1.0.0
 */
public final class ResourceManager {
    private static final Object FAKE_BUNDLE = new Object();
    private static final Map<String, Object> bundles =
        Collections.synchronizedMap(new HashMap<String, Object>());
    
    /**
     * @param packageName package name, used for
     *        <code>Resources.properties</code> file look-up
     * @param messageKey message key
     * @return message for {@link Locale#getDefault() default locale}
     */
    public static String getMessage(final String packageName,
            final String messageKey) {
        return getMessage(packageName, messageKey, Locale.getDefault(), null);
    }
    
    /**
     * @param packageName package name, used for
     *        <code>Resources.properties</code> file look-up
     * @param messageKey message key
     * @param data data for parameter placeholders substitution, may be
     *        <code>Object</code>, <code>array</code> or
     *        <code>Collection</code>.
     * @return message for {@link Locale#getDefault() default locale}
     */
    public static String getMessage(final String packageName, final String messageKey, final Object data) {
        return getMessage(packageName, messageKey, Locale.getDefault(), data);
    }

    /**
     * @param packageName package name, used for
     *        <code>Resources.properties</code> file look-up
     * @param messageKey message key
     * @param locale locale to get message for
     * @return message for given locale
     */
    public static String getMessage(final String packageName, final String messageKey, final Locale locale) {
        return getMessage(packageName, messageKey, locale, null);
    }

    /**
     * @param packageName package name, used for
     *        <code>Resources.properties</code> file look-up
     * @param messageKey message key
     * @param locale locale to get message for
     * @param data data for parameter placeholders substitution, may be
     *        <code>Object</code>, <code>array</code> or
     *        <code>Collection</code>.
     * @return message for given locale
     */
    public static String getMessage(final String packageName, final String messageKey, final Locale locale, final Object data) {
        Object obj = bundles.get(packageName + '|' + locale);
        if (obj == null) {
            try {
                obj = ResourceBundle.getBundle(packageName + ".Resources", //$NON-NLS-1$
                        locale);
            } catch (MissingResourceException mre) {
                obj = FAKE_BUNDLE;
            }
            bundles.put(packageName + '|' + locale, obj);
        }
        if (obj == FAKE_BUNDLE) {
            return "resource " + packageName + '.' + messageKey //$NON-NLS-1$
                + " not found for locale " + locale; //$NON-NLS-1$
        }
        try {
            String result = ((ResourceBundle) obj).getString(messageKey);
            return (data == null) ? result : processParams(result, data);
        } catch (MissingResourceException mre) {
            return "resource " + packageName + '.' + messageKey //$NON-NLS-1$
                + " not found for locale " + locale; //$NON-NLS-1$
        }
    }

    private static String processParams(final String str, final Object data) {
        String result = str;
        if ((data != null) && data.getClass().isArray()) {
            Object[] params = (Object[])data;
            for (int i = 0; i < params.length; i++) {
                result = replaceAll(result, "{" + i + "}", "" + params[i]); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            }
        } else if (data instanceof Collection) {
            int i = 0;
            for (Object object : (Collection) data) {
                result = replaceAll(result, "{" + i++ + "}", "" + object); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            }
        } else {
            result = replaceAll(result, "{0}", "" + data); //$NON-NLS-1$ //$NON-NLS-2$
        }
        return result;
    }

    private static String replaceAll(final String str, final String from, final String to) {
        String result = str;
        int p = 0;
        while (true) {
            p = result.indexOf(from, p);
            if (p == -1) {
                break;
            }
            result = result.substring(0, p) + to
                + result.substring(p + from.length());
            p += to.length();
        }
        return result;
    }

    private ResourceManager() {
        // no-op
    }
}