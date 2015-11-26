package com.soulgame.sgtrack.etl.core.facility;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by hatakawa on 11/26/15.
 */
public class XProperties extends Properties {

    private static final String DEFAULT_PLACEHOLDER_PREFIX = "${";
    private static final String DEFAULT_PLACEHOLDER_SUFFIX = "}";

    @Override
    public String getProperty(String key) {
        String originalValue = super.getProperty(key);
        if (originalValue == null) {
            return null;
        }
        Pattern p = Pattern.compile("\\$\\{\\w+(.\\w+)+}");
        Matcher m = p.matcher(originalValue);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            String match = m.group();
            String varName = match.substring(DEFAULT_PLACEHOLDER_PREFIX.length(), match.indexOf(DEFAULT_PLACEHOLDER_SUFFIX));
            String varValue = super.getProperty(varName);
            if (varValue == null) {
                varValue = System.getProperty(varName);
            }
            if (varValue == null) {
                varValue = System.getenv(varName);
            }
            if (varValue == null) {
                throw new RuntimeException("Cann't resolve property pair: " + key + "=" + originalValue);
            }
            String v = Matcher.quoteReplacement(varValue);

            m.appendReplacement(sb, v);
        }
        m.appendTail(sb);
        return sb.toString();
    }
}
