package org.example;
import org.kohsuke.args4j.Option;

public class Options {

    @Option(name = "-numThread", usage = "Input number of threads")
    public int numThread;

    @Option(name = "-numBins", usage = "Input number of bins")
    public int numBins;
}
