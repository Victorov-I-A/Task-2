package com.viktorov.task2;

import org.kohsuke.args4j.Option;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

import java.io.IOException;

public class TarLauncher
{

    @Option(name = "-u")
    private boolean isU;

    @Option(name = "-out")
    private boolean isOut;

    @Argument(required = true, metaVar = "InputName", usage = "Input file name")
    private String[] inputFileName;

    public static void main(String[] args)
    {
        new TarLauncher().launch(new String[]{"-u", "inputU.txt"});
    }

    private void launch(String[] args)
    {
        CmdLineParser parser = new CmdLineParser(this);

        try
        {
            parser.parseArgument(args);
        }
        catch (CmdLineException e)
        {
            System.err.println(e.getMessage());
            parser.printUsage(System.err);
            return;
        }

        if (isU == isOut)
        {
            System.err.println("key limit exceeded");
            return;
        }

        Tar tar = new Tar(inputFileName);

        try
        {
          if (isU = true)
              tar.tar("-u");
          if (isOut = true)
              tar.tar("-out");
        }
        catch (IOException e)
        {
            System.err.println(e.getMessage());
        }
    }
}
