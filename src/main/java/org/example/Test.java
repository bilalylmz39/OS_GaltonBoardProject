package org.example;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

public class Test {
    public static void main(String[] args) {
        Options options = new Options();
        CmdLineParser parser = new CmdLineParser(options);

        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            parser.printUsage(System.err);
            System.exit(1);
        }
        int numThread = options.numThread;
        int numBins = options.numBins;

        GaltonBoard galtonBoard = new GaltonBoard(numBins, numThread);

        galtonBoard.run();

        System.out.println(galtonBoard);
    }
}