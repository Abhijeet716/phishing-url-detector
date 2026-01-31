package com.phishing;
import java.util.*;
import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter URL:");
        String url=sc.nextLine();
        //String testUrl = "http://example-login.com/account/update";

        double[] features = FeatureExtractor.extractFeatures(url);

        System.out.println("Extracted Features:");
        System.out.println(Arrays.toString(features));
    }
}
