package com.phishing;

import weka.classifiers.Classifier;
import weka.core.*;

//import java.io.FileInputStream;
import java.util.ArrayList;

public class PhishingDetector {

    static ArrayList<Attribute> attributes;
    static Instances dataset;
    static Classifier model;

    // ✅ Initialize model & attributes (runs once)
    public static void init() throws Exception {

    model = (Classifier) weka.core.SerializationHelper.read(
            PhishingDetector.class.getClassLoader()
                    .getResourceAsStream("phishing-model.model")
    );

    attributes = new ArrayList<>();

    attributes.add(new Attribute("URLLength"));
    attributes.add(new Attribute("DomainLength"));
    attributes.add(new Attribute("IsDomainIP"));
    attributes.add(new Attribute("TLDLength"));
    attributes.add(new Attribute("NoOfSubDomain"));
    attributes.add(new Attribute("NoOfLettersInURL"));
    attributes.add(new Attribute("LetterRatioInURL"));
    attributes.add(new Attribute("NoOfDegitsInURL"));
    attributes.add(new Attribute("DegitRatioInURL"));
    attributes.add(new Attribute("NoOfOtherSpecialCharsInURL"));
    attributes.add(new Attribute("IsHTTPS"));

    ArrayList<String> classValues = new ArrayList<>();
    classValues.add("0");
    classValues.add("1");
    attributes.add(new Attribute("label", classValues));

    dataset = new Instances("PhishingTest", attributes, 0);
    dataset.setClassIndex(dataset.numAttributes() - 1);
}


    // ✅ Prediction method (used by UI)
    public static String predict(String url) throws Exception {

        if (model == null) {
            init(); // initialize if not loaded
        }

        double[] features = FeatureExtractor.extractFeatures(url);

        DenseInstance instance = new DenseInstance(attributes.size());
        for (int i = 0; i < features.length; i++) {
            instance.setValue(attributes.get(i), features[i]);
        }
        instance.setDataset(dataset);

        double result = model.classifyInstance(instance);
        String prediction = dataset.classAttribute().value((int) result);

        return prediction.equals("1") ? "⚠️ Phishing URL" : "✅ Legit URL";
    }

    // Optional main (for testing without UI)
    /*public static void main(String[] args) throws Exception {
        System.out.println(predict("http://example-login.com"));
    }*/
}
