package com.soulgame.sgtrack.etl.core.facility;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Charles on 11/25/15.
 */
public class Environment {

    public static class Entry {
        private String k;
        private String v;
        public Entry(String k, String v) {
            this.k = k;
            this.v = v;
        }
        public String getKey() { return k; }
        public String getValue() { return v; }

        @Override
        public String toString() {
            return k + "=" + v;
        }
    }

    private static void put(ArrayList<Entry> l, String k, String v) {
        l.add(new Entry(k,v));
    }

    public static List<Entry> list() {
        ArrayList<Entry> list = new ArrayList<Entry>();

        try {
            put(list, "host.name",
                InetAddress.getLocalHost().getCanonicalHostName());
        } catch (UnknownHostException e) {
            put(list, "host.name", "<NA>");
        }

        put(list, "java.version",
            System.getProperty("java.version", "<NA>"));
        put(list, "java.vendor",
            System.getProperty("java.vendor", "<NA>"));
        put(list, "java.home",
            System.getProperty("java.home", "<NA>"));
        put(list, "java.class.path",
            System.getProperty("java.class.path", "<NA>"));
        put(list, "java.library.path",
            System.getProperty("java.library.path", "<NA>"));
        put(list, "java.io.tmpdir",
            System.getProperty("java.io.tmpdir", "<NA>"));
        put(list, "java.compiler",
            System.getProperty("java.compiler", "<NA>"));
        put(list, "os.name",
            System.getProperty("os.name", "<NA>"));
        put(list, "os.arch",
            System.getProperty("os.arch", "<NA>"));
        put(list, "os.version",
            System.getProperty("os.version", "<NA>"));
        put(list, "user.name",
            System.getProperty("user.name", "<NA>"));
        put(list, "user.home",
            System.getProperty("user.home", "<NA>"));
        put(list, "user.dir",
            System.getProperty("user.dir", "<NA>"));

        return list;
    }

    public static void logEnv() {
        List<Entry> env = Environment.list();
        for (Entry e : env) {
            System.out.println(e.toString());
        }
    }
}
