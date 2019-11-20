package labs.lab2.parts.part1;

import org.deeplearning4j.api.storage.StatsStorage;
import org.deeplearning4j.nn.api.OptimizationAlgorithm;
import org.deeplearning4j.nn.conf.BackpropType;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.distribution.UniformDistribution;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.weights.WeightInit;
import org.deeplearning4j.optimize.listeners.ScoreIterationListener;
import org.deeplearning4j.ui.api.UIServer;
import org.deeplearning4j.ui.stats.StatsListener;
import org.deeplearning4j.ui.storage.InMemoryStatsStorage;
import org.nd4j.evaluation.classification.Evaluation;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.learning.config.Sgd;
import org.nd4j.linalg.lossfunctions.LossFunctions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import examples.task5.Task5;
import utils.Kfold;
import utils.KfoldArr;

public class Part1 {

    private static final Logger log = LoggerFactory.getLogger(Part1.class);

    private Task5 task5 = new Task5();

    INDArray inputF;
    INDArray labelsF;

    public Part1() {

    }

    public void build() {
        int seed = 1234;        // number used to initialize a pseudorandom number generator.
        int nEpochs = 20;    // number of training epochs
        int n = 1000;

        log.info("Data preparation...");

        // create dataset object
        DataSet ds = createDataSet(n);

        log.info("Network configuration and training...");

        MultiLayerConfiguration conf = new Configuration().configure(seed);

        MultiLayerNetwork net = new MultiLayerNetwork(conf);
        net.init();


        //Initialize the user interface backend
        UIServer uiServer = UIServer.getInstance();

        StatsStorage statsStorage = new InMemoryStatsStorage();
        uiServer.attach(statsStorage);

        net.setListeners(new ScoreIterationListener(10), new StatsListener(statsStorage));

        System.out.println(net.summary());

        for (int i = 0; i < nEpochs; i++) {
            net.fit(ds);
        }

        // create output for every training sample
        INDArray output = net.output(inputF);
        System.out.println(output);

        // let Evaluation prints stats how often the right output had the highest value
        Evaluation eval = new Evaluation();
        eval.eval(labelsF, output);
        System.out.println(eval.stats());

    }

    private DataSet createDataSet(int n) {
        var sample = task5.sample(n);
        var kfold = new KfoldArr(sample);
        kfold.generate(8);

        var train = kfold.getTrain();
        var test = kfold.getTest();
        var trainOut = task5.generate(train);
        var testOut = task5.generate(test);

        INDArray input = Nd4j.zeros(train.length, 1);
        INDArray labels = Nd4j.zeros(trainOut.length, 1);

        inputF = Nd4j.zeros(test.length, 1);
        labelsF = Nd4j.zeros(testOut.length, 1);



        for (int i = 0; i < train.length; i++) {
            input.putScalar(new int[]{i}, train[i]);
            labels.putScalar(new int[]{i}, trainOut[i]);
        }

        for (int i = 0; i < test.length; i++) {
            inputF.putScalar(new int[]{i, 0}, test[i]);
            labelsF.putScalar(new int[]{i, 0}, testOut[i]);
        }

        return new DataSet(input, labels); //, inputF, labelsF
    }

    public static class Configuration {
        MultiLayerConfiguration configure(int seed) {
            return new NeuralNetConfiguration.Builder()
                    .updater(new Sgd(0.01))
//                    .optimizationAlgo(OptimizationAlgorithm.LINE_GRADIENT_DESCENT)
                    .seed(seed)
                    .biasInit(0)
                    .miniBatch(false)
                    .list()
                    .layer(
                            new DenseLayer.Builder()
                            .nIn(1)
                            .nOut(10)
                            .activation(Activation.IDENTITY)
                            // random initialize weights with values between 0 and 1
                            .weightInit(new UniformDistribution(0, 1))
                            .build()
                    )
                    .layer(
                            new OutputLayer.Builder(LossFunctions.LossFunction.MSE)
                            .nIn(10)
                            .nOut(1)
                            .activation(Activation.IDENTITY)
                            .weightInit(new UniformDistribution(0, 1))
                            .build()
                    )
                    .backpropType(BackpropType.Standard)
                    .build();
        }
    }
}
