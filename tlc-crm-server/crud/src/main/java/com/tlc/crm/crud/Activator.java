package com.tlc.crm.crud;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * Activator used to start the bundle
 *
 * @author NandiniRakAS
 */
public class Activator implements BundleActivator {

    public void start(BundleContext context) {
        System.out.println("Starting crud bundle");
    }

    public void stop(BundleContext context) {
        System.out.println("Stopping crud bundle");
    }

}