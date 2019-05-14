package com.viktorov.task2;

import org.kohsuke.args4j.Option;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

import java.io.IOException;

public class TarLauncher {
    @Argument(required = true, metaVar = "InputNames", usage = "Input file names")
    private String[] inputFileName;

    @Option(name = "-u")
    private boolean isU;

    @Option(name = "-out")
    private boolean isOut;


    public static void main(String[] args) {
        new TarLauncher().launch(args);
    }

    private void launch(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);
        }
        catch (CmdLineException e) {
            System.err.println(e.getMessage());
            parser.printUsage(System.err);
            return;
        }

        if (isU == isOut) {
            System.err.println("key limit exceeded");
            return;
        }

        Tar tar = new Tar(inputFileName);

        try {
            if (isOut = true) {
                if (!args[args.length - 2].equals("-out")) {
                    System.err.println("incorrect entered arguments");
                    return;
                }
                    tar.tar("-out");
            }
            else {
                tar.tar("-u");
            }
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
