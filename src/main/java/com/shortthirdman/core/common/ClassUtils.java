package com.shortthirdman.core.common;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * A collection of class management utility methods.
 *
 * @version 1.0.0
 * @author Swetank Mohanty (shortthirdman)
 */
public class ClassUtils {

    /**
     * Return a resource URL.
     * BL: if this is command line operation, the classloading issues
     *     are more sane.  During servlet execution, we explicitly set
     *     the ClassLoader.
     *
     * @return The context classloader.
     * @exception MalformedURLException If a loading error occurs
     */
    public static URL getResource(String resource) throws MalformedURLException {
        return getClassLoader().getResource(resource);
    }

    /**
     * Return the context classloader.
     * BL: if this is command line operation, the classloading issues
     *     are more sane.  During servlet execution, we explicitly set
     *     the ClassLoader.
     *
     * @return The context classloader.
     */
    public static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }
}