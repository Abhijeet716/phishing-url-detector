package com.phishing;

import java.net.*;

public class FeatureExtractor {

    public static double[] extractFeatures(String urlStr) {
        double[] features = new double[11];

        try {
            URL url = new URL(urlStr);
            String host = url.getHost();
            String path = url.getPath();

            // 1. URL Length
            features[0] = urlStr.length();

            // 2. Domain Length
            features[1] = host.length();

            // 3. IsDomainIP (1 if IP address, else 0)
            features[2] = host.matches("\\d+\\.\\d+\\.\\d+\\.\\d+") ? 1 : 0;

            // 4. TLD Length
            String[] domainParts = host.split("\\.");
            String tld = domainParts[domainParts.length - 1];
            features[3] = tld.length();

            // 5. Number of Subdomains
            features[4] = domainParts.length - 2;

            // 6. Number of Letters in URL
            int letters = urlStr.replaceAll("[^a-zA-Z]", "").length();
            features[5] = letters;

            // 7. Letter Ratio in URL
            features[6] = (double) letters / urlStr.length();

            // 8. Number of Digits in URL
            int digits = urlStr.replaceAll("[^0-9]", "").length();
            features[7] = digits;

            // 9. Digit Ratio in URL
            features[8] = (double) digits / urlStr.length();

            // 10. Number of Other Special Characters in URL
            int specialChars = urlStr.replaceAll("[a-zA-Z0-9:/\\.]", "").length();
            features[9] = specialChars;

            // 11. IsHTTPS (1 if https, else 0)
            features[10] = urlStr.startsWith("https") ? 1 : 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return features;
    }
}
