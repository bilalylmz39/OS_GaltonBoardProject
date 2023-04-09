package org.example;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;



public class Test {
    public static void main(String[] args) {
        Options opt = new Options();
        CmdLineParser parser = new CmdLineParser(opt);

        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            parser.printUsage(System.err);
            System.exit(1);
        }
        int numThread = opt.numThread;
        int numBins = opt.numBins;

        GaltonBoard galtonBoard = new GaltonBoard(numBins, numThread);

        galtonBoard.run();

        System.out.println(galtonBoard);
    }
}