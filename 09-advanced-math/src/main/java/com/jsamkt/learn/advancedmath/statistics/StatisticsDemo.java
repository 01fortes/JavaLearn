package com.jsamkt.learn.advancedmath.statistics;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.math3.stat.inference.TTest;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;
import org.apache.commons.math3.stat.regression.SimpleRegression;

import java.util.Arrays;
import java.util.Random;

/**
 * Demonstrates statistical concepts and operations using Java libraries.
 * Statistics is a branch of mathematics that deals with the collection, analysis,
 * interpretation, presentation, and organization of data.
 */
public class StatisticsDemo {
    
    public static void demo() {
        System.out.println("\n----- STATISTICS -----");
        
        // Demonstrate descriptive statistics
        descriptiveStatisticsDemo();
        
        // Demonstrate hypothesis testing
        hypothesisTestingDemo();
        
        // Demonstrate probability distributions
        probabilityDistributionsDemo();
        
        // Demonstrate correlation and regression
        correlationRegressionDemo();
        
        System.out.println("----- END OF STATISTICS -----\n");
    }
    
    private static void descriptiveStatisticsDemo() {
        System.out.println("\n# Descriptive Statistics");
        
        // Create a data set
        double[] data = {10.2, 15.7, 20.5, 25.3, 30.8, 15.2, 22.6, 19.8, 27.3, 18.2};
        System.out.println("Data set: " + Arrays.toString(data));
        
        // Use Apache Commons Math DescriptiveStatistics
        DescriptiveStatistics stats = new DescriptiveStatistics();
        for (double value : data) {
            stats.addValue(value);
        }
        
        // Calculate basic statistics
        System.out.println("\nBasic statistics:");
        System.out.printf("Count: %d%n", (long)stats.getN());
        System.out.printf("Min: %.2f%n", stats.getMin());
        System.out.printf("Max: %.2f%n", stats.getMax());
        System.out.printf("Sum: %.2f%n", stats.getSum());
        System.out.printf("Mean: %.2f%n", stats.getMean());
        System.out.printf("Median: %.2f%n", stats.getPercentile(50));
        System.out.printf("Standard Deviation: %.2f%n", stats.getStandardDeviation());
        System.out.printf("Variance: %.2f%n", stats.getVariance());
        
        // Calculate percentiles
        System.out.println("\nPercentiles:");
        System.out.printf("25th Percentile (Q1): %.2f%n", stats.getPercentile(25));
        System.out.printf("50th Percentile (Median): %.2f%n", stats.getPercentile(50));
        System.out.printf("75th Percentile (Q3): %.2f%n", stats.getPercentile(75));
        System.out.printf("Interquartile Range (IQR): %.2f%n", 
                stats.getPercentile(75) - stats.getPercentile(25));
        
        // Calculate additional statistics
        System.out.println("\nAdditional statistics:");
        System.out.printf("Geometric Mean: %.2f%n", stats.getGeometricMean());
        System.out.printf("Kurtosis: %.2f%n", stats.getKurtosis());
        System.out.printf("Skewness: %.2f%n", stats.getSkewness());
    }
    
    private static void hypothesisTestingDemo() {
        System.out.println("\n# Hypothesis Testing");
        
        // Create two data sets for comparison
        double[] sample1 = {85, 90, 78, 92, 88, 76, 94, 89, 81, 87};
        double[] sample2 = {79, 84, 72, 88, 76, 70, 82, 74, 80, 78};
        
        System.out.println("Sample 1: " + Arrays.toString(sample1));
        System.out.println("Sample 2: " + Arrays.toString(sample2));
        
        // Calculate means
        double mean1 = Arrays.stream(sample1).average().orElse(0);
        double mean2 = Arrays.stream(sample2).average().orElse(0);
        
        System.out.printf("\nMean of Sample 1: %.2f%n", mean1);
        System.out.printf("Mean of Sample 2: %.2f%n", mean2);
        System.out.printf("Difference in means: %.2f%n", mean1 - mean2);
        
        // Perform t-test using Apache Commons Math
        System.out.println("\nPerforming t-test to determine if the means are significantly different:");
        TTest tTest = new TTest();
        
        // Two-sample t-test
        double pValue = tTest.tTest(sample1, sample2);
        System.out.printf("p-value: %.5f%n", pValue);
        
        // Interpret the result
        double significance = 0.05; // 5% significance level
        System.out.println("\nHypothesis test result:");
        System.out.println("Null hypothesis (H0): The means of the two samples are equal.");
        System.out.println("Alternative hypothesis (H1): The means of the two samples are different.");
        
        if (pValue < significance) {
            System.out.printf("Since p-value (%.5f) < significance level (%.2f), we reject the null hypothesis.%n", 
                    pValue, significance);
            System.out.println("Conclusion: There is a statistically significant difference between the two samples.");
        } else {
            System.out.printf("Since p-value (%.5f) >= significance level (%.2f), we fail to reject the null hypothesis.%n", 
                    pValue, significance);
            System.out.println("Conclusion: There is not enough evidence to suggest a significant difference between the samples.");
        }
        
        // One-sample t-test (testing if sample mean is equal to a specified value)
        double testValue = 85.0;
        System.out.printf("\nOne-sample t-test (comparing Sample 1 to a hypothesized mean of %.1f):%n", testValue);
        
        double oneSamplePValue = tTest.tTest(testValue, sample1);
        System.out.printf("p-value: %.5f%n", oneSamplePValue);
        
        if (oneSamplePValue < significance) {
            System.out.printf("Since p-value (%.5f) < significance level (%.2f), we reject the null hypothesis.%n", 
                    oneSamplePValue, significance);
            System.out.printf("Conclusion: The mean of Sample 1 is significantly different from %.1f.%n", testValue);
        } else {
            System.out.printf("Since p-value (%.5f) >= significance level (%.2f), we fail to reject the null hypothesis.%n", 
                    oneSamplePValue, significance);
            System.out.printf("Conclusion: There is not enough evidence to suggest the mean of Sample 1 differs from %.1f.%n", testValue);
        }
    }
    
    private static void probabilityDistributionsDemo() {
        System.out.println("\n# Probability Distributions");
        
        // Normal Distribution
        System.out.println("\nNormal Distribution Example:");
        
        // Create a normal distribution with mean=0 and standard deviation=1 (standard normal distribution)
        NormalDistribution normalDist = new NormalDistribution(0, 1);
        
        // Calculate probabilities
        System.out.println("Standard Normal Distribution (Mean=0, SD=1):");
        System.out.printf("P(X ≤ 0) = %.4f%n", normalDist.cumulativeProbability(0)); // Should be close to 0.5
        System.out.printf("P(X ≤ 1) = %.4f%n", normalDist.cumulativeProbability(1)); // Should be close to 0.8413
        System.out.printf("P(X ≤ 2) = %.4f%n", normalDist.cumulativeProbability(2)); // Should be close to 0.9772
        
        // Calculate probability between two values
        System.out.printf("P(-1 ≤ X ≤ 1) = %.4f%n", normalDist.probability(-1, 1)); // Should be close to 0.6827
        
        // Calculate inverse cumulative probability (quantile function)
        System.out.println("\nQuantile function (inverse cumulative probability):");
        System.out.printf("Value at 25th percentile: %.4f%n", normalDist.inverseCumulativeProbability(0.25));
        System.out.printf("Value at 50th percentile: %.4f%n", normalDist.inverseCumulativeProbability(0.50));
        System.out.printf("Value at 75th percentile: %.4f%n", normalDist.inverseCumulativeProbability(0.75));
        System.out.printf("Value at 95th percentile: %.4f%n", normalDist.inverseCumulativeProbability(0.95));
        
        // Generate random samples from the normal distribution
        System.out.println("\nRandom samples from normal distribution:");
        Random rng = new Random(42); // Seed for reproducibility
        
        System.out.print("Samples: ");
        for (int i = 0; i < 10; i++) {
            double sample = normalDist.sample();
            System.out.printf("%.4f ", sample);
        }
        System.out.println();
        
        // Example of using normal distribution: 68-95-99.7 rule
        System.out.println("\nThe 68-95-99.7 Rule for Normal Distributions:");
        System.out.printf("Approximately 68%% of values fall within 1 SD of the mean: %.2f%%%n", 
                normalDist.probability(-1, 1) * 100);
        System.out.printf("Approximately 95%% of values fall within 2 SD of the mean: %.2f%%%n", 
                normalDist.probability(-2, 2) * 100);
        System.out.printf("Approximately 99.7%% of values fall within 3 SD of the mean: %.2f%%%n", 
                normalDist.probability(-3, 3) * 100);
    }
    
    private static void correlationRegressionDemo() {
        System.out.println("\n# Correlation and Regression Analysis");
        
        // Create two variables (x and y) that are related
        // Let's say x is hours studied and y is exam score
        double[] hoursStudied = {1, 2, 3, 4, 5, 6, 7, 8, 10};
        double[] examScore = {45, 50, 60, 65, 70, 75, 80, 85, 95};
        
        System.out.println("Hours studied (x): " + Arrays.toString(hoursStudied));
        System.out.println("Exam scores (y): " + Arrays.toString(examScore));
        
        // Calculate Pearson's correlation coefficient
        PearsonsCorrelation correlation = new PearsonsCorrelation();
        double r = correlation.correlation(hoursStudied, examScore);
        
        System.out.printf("\nPearson's correlation coefficient (r): %.4f%n", r);
        
        // Interpret the correlation
        System.out.println("Interpretation of correlation:");
        if (r > 0.9) {
            System.out.println("Very strong positive correlation");
        } else if (r > 0.7) {
            System.out.println("Strong positive correlation");
        } else if (r > 0.5) {
            System.out.println("Moderate positive correlation");
        } else if (r > 0.3) {
            System.out.println("Weak positive correlation");
        } else if (r > -0.3) {
            System.out.println("No or negligible correlation");
        } else if (r > -0.5) {
            System.out.println("Weak negative correlation");
        } else if (r > -0.7) {
            System.out.println("Moderate negative correlation");
        } else if (r > -0.9) {
            System.out.println("Strong negative correlation");
        } else {
            System.out.println("Very strong negative correlation");
        }
        
        // Simple linear regression
        SimpleRegression regression = new SimpleRegression();
        
        // Add data points to the regression model
        for (int i = 0; i < hoursStudied.length; i++) {
            regression.addData(hoursStudied[i], examScore[i]);
        }
        
        // Get the regression parameters
        double intercept = regression.getIntercept();
        double slope = regression.getSlope();
        
        System.out.println("\nSimple Linear Regression results:");
        System.out.printf("Regression equation: y = %.2f + %.2f * x%n", intercept, slope);
        System.out.printf("Intercept (β₀): %.2f%n", intercept);
        System.out.printf("Slope (β₁): %.2f%n", slope);
        System.out.printf("R-squared (coefficient of determination): %.4f%n", regression.getRSquare());
        
        // Make predictions using the regression model
        System.out.println("\nPredictions using the regression model:");
        double[] predictHours = {2.5, 5.5, 9};
        
        for (double hours : predictHours) {
            double predicted = regression.predict(hours);
            System.out.printf("For %.1f hours of study, predicted exam score: %.2f%n", hours, predicted);
        }
        
        // Check if the regression is significant
        double significance = 0.05;
        double pValue = regression.getSignificance();
        
        System.out.println("\nRegression significance test:");
        System.out.printf("p-value for slope: %.5f%n", pValue);
        
        if (pValue < significance) {
            System.out.printf("Since p-value (%.5f) < significance level (%.2f), the regression slope is statistically significant.%n", 
                    pValue, significance);
            System.out.println("Conclusion: There is a significant linear relationship between hours studied and exam score.");
        } else {
            System.out.printf("Since p-value (%.5f) >= significance level (%.2f), the regression slope is not statistically significant.%n", 
                    pValue, significance);
            System.out.println("Conclusion: There is not enough evidence to suggest a significant linear relationship.");
        }
    }
}