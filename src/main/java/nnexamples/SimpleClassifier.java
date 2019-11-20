package nnexamples;

import java.util.Arrays;

import org.deeplearning4j.api.storage.StatsStorage;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.distribution.UniformDistribution;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.optimize.listeners.ScoreIterationListener;
import org.deeplearning4j.ui.api.UIServer;
import org.deeplearning4j.ui.stats.StatsListener;
import org.deeplearning4j.ui.storage.InMemoryStatsStorage;
import org.nd4j.evaluation.classification.Evaluation;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.dataset.SplitTestAndTrain;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.learning.config.Sgd;
import org.nd4j.linalg.learning.regularization.Regularization;
import org.nd4j.linalg.lossfunctions.LossFunctions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import examples.task1.Task1;

public class SimpleClassifier {

    private static final Logger log = LoggerFactory.getLogger(SimpleClassifier.class);

    public void build() {

        int seed = 123456;        // number used to initialize a pseudorandom number generator.
        int nEpochs = 150;    // number of training epochs

        log.info("Data preparation...");

        // create dataset object
        DataSet ds = createDataSet();
        ds.shuffle();
        SplitTestAndTrain testAndTrain = ds.splitTestAndTrain(0.75);

        DataSet trainingData = testAndTrain.getTrain();
        DataSet testData = testAndTrain.getTest();

        log.info("Network configuration and training...");

        MultiLayerConfiguration conf = new Configuration().configure(seed);

        MultiLayerNetwork net = new MultiLayerNetwork(conf);
        net.init();


        //Initialize the user interface backend
        UIServer uiServer = UIServer.getInstance();

        //Configure where the network information (gradients, activations, score vs. time etc) is to be stored
        //Then add the StatsListener to collect this information from the network, as it trains
        StatsStorage statsStorage = new InMemoryStatsStorage();

        //Attach the StatsStorage instance to the UI: this allows the contents of the StatsStorage to be visualized
        uiServer.attach(statsStorage);

        // add an listener which outputs the error every 100 parameter updates
        net.setListeners(new ScoreIterationListener(10), new StatsListener(statsStorage));

        // C&P from LSTMCharModellingExample
        // Print the number of parameters in the network (and for each layer)
        System.out.println(net.summary());

        // here the actual learning takes place
        for (int i = 0; i < nEpochs; i++) {
            net.fit(trainingData);
        }

        // create output for every training sample
        INDArray output = net.output(testData.getFeatures());
        System.out.println(output);

        // let Evaluation prints stats how often the right output had the highest value
        Evaluation eval = new Evaluation();
        eval.eval(testData.getLabels(), output);
        System.out.println(eval.stats());

    }


    private DataSet createDataSet() {
        var task1 = new Task1();
        var sample = task1.sample();
        var generate = task1.generate(sample);

        INDArray input = Nd4j.zeros(sample.length, 2);

        INDArray labels = Nd4j.zeros(sample.length, 1);


        for (int i = 0; i < sample.length; i++) {
            input.putScalar(new int[] {i, 0}, sample[i][0]);
            input.putScalar(new int[] {i, 1}, sample[i][1]);

            labels.putScalar(new int[] {i, 0}, generate[i]);
        }

        return new DataSet(input, labels); //, inputF, labelsF
    }


    public static class Configuration {
        MultiLayerConfiguration configure(int seed) {
            return new NeuralNetConfiguration.Builder()
                    .updater(new Sgd(0.001))
                    .seed(seed)
                    .biasInit(0) // init the bias with 0 - empirical value, too
                    // from "http://deeplearning4j.org/architecture": The networks can
                    // process the input more quickly and more accurately by ingesting
                    // minibatches 5-10 elements at a time in parallel.
                    // this example runs better without, because the dataset is smaller than
                    // the mini batch size
                    .miniBatch(false)
                    .l2(1e-2)
                    .list()
                    .layer(new DenseLayer.Builder()
                            .nIn(2)
                            .nOut(12)
                            .activation(Activation.IDENTITY)
                            // random initialize weights with values between 0 and 1
                            .weightInit(new UniformDistribution(0, 1))
                            .build())
                    .layer(new OutputLayer.Builder(LossFunctions.LossFunction.MSE)
                            .nOut(1)
                            .activation(Activation.IDENTITY)
                            .weightInit(new UniformDistribution(0, 1))
                            .build())
                    .build();
        }
    }
}
