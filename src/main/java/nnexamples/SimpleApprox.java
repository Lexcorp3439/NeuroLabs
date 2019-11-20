package nnexamples;

import java.awt.Color;
import java.util.Arrays;

import org.deeplearning4j.nn.api.OptimizationAlgorithm;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.Updater;
import org.deeplearning4j.nn.conf.distribution.UniformDistribution;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.weights.WeightInit;
import org.deeplearning4j.optimize.listeners.ScoreIterationListener;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.markers.SeriesMarkers;
import org.nd4j.evaluation.classification.Evaluation;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.dataset.SplitTestAndTrain;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.lossfunctions.LossFunctions;

import org.nd4j.linalg.activations.Activation;

import org.nd4j.linalg.learning.config.Sgd;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import examples.task5.Task5;
import utils.MyUtils;
import utils.XYChartUtils;

public class SimpleApprox {

    private static final Logger log = LoggerFactory.getLogger(SimpleApprox.class);
    private Task5 task5 = new Task5();


    public void build() {
        int numInputs = 1;
        int numOutputs = 1;
        int numHiddenNodes = 160;

        int seed = 123456;
        int iterations = 10000;
        double learningRate = 0.04;
        int nTrain = 10000;
        int printIteration = 100;



        MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder()
                .seed(seed)
                .optimizationAlgo(OptimizationAlgorithm.STOCHASTIC_GRADIENT_DESCENT)
                .updater(new Sgd(learningRate))
                .biasInit(0)
                .l2(1e-2)
                .list()
                .layer(0, new DenseLayer.Builder()
                        .nIn(numInputs)
                        .nOut(numHiddenNodes)
                        .activation(Activation.SIGMOID)
                        .weightInit(new UniformDistribution(0, 1))
                        .build())
                .layer(1, new OutputLayer.Builder(LossFunctions.LossFunction.MSE)
                        .weightInit(new UniformDistribution(0, 1))
                        .activation(Activation.IDENTITY)
                        .nIn(numHiddenNodes)
                        .nOut(numOutputs)
                        .build())
                .build();

        MultiLayerNetwork network = new MultiLayerNetwork(conf);
        network.init();
        network.setListeners(new ScoreIterationListener(printIteration));

        var ds = createDataSet(nTrain);
        ds.shuffle();
        SplitTestAndTrain testAndTrain = ds.splitTestAndTrain(0.75);

        DataSet trainingData = testAndTrain.getTrain();
        DataSet testData = testAndTrain.getTest();
        // here the actual learning takes place
        for (int i = 0; i < iterations; i++) {
            network.fit(trainingData);
        }

        // create output for every training sample
        INDArray output = network.output(ds.getFeatures());
        System.out.println("ACTUAL" + Arrays.toString(output.toDoubleVector()));
        System.out.println("EXPECTED" + Arrays.toString(testData.getLabels().toDoubleVector()));

        XYChart chart = XYChartUtils.generateXYChart("SIMPLEAPPROX", 10);


        XYSeries series1 = chart.addSeries("Actual", ds.getFeatures().toDoubleVector(), output.toDoubleVector());
        series1.setMarkerColor(Color.RED);

        XYSeries series2 = chart.addSeries("Expected", ds.getFeatures().toDoubleVector(), ds.getLabels().toDoubleVector());
        series2.setMarkerColor(Color.BLACK);

        new SwingWrapper(chart).displayChart();
        // let Evaluation prints stats how often the right output had the highest value
        Evaluation eval = new Evaluation();
        eval.eval(testData.getLabels(), output);
        System.out.println(eval.stats());
        System.out.println( "ACCURACY" + eval.accuracy());

    }

    private DataSet createDataSet(int n) {
        var sample = task5.sample(n);
        var trainOut = task5.generate(sample);

        INDArray input = Nd4j.zeros(sample.length, 1);
        INDArray labels = Nd4j.zeros(trainOut.length, 1);
//

        for (int i = 0; i < sample.length; i++) {
            input.putScalar(new int[]{i, 0}, sample[i]);
            labels.putScalar(new int[]{i, 0}, trainOut[i]);
        }

        return new DataSet(input, labels);
    }

}
