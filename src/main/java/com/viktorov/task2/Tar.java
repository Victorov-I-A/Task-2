package com.viktorov.task2;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Tar {
    private String[] fileNamesList;


    public Tar(String[] filesNameList)
    {
        this.fileNamesList = filesNameList;
    }


    public void tar(String option) throws IOException {
        if (option.equals("-out")) {
            outTar();
        } else {
            uTar();
        }
    }

    private void uTar() throws IOException {
        File inputFile = new File(fileNamesList[0]);
        FileReader fileReader = new FileReader(inputFile);
        long numOfLines = Files.lines(Paths.get(fileNamesList[0])).count();

        File out1 = new File("files\\OutputFiles\\output1.txt");
        File out2 = new File("files\\OutputFiles\\output2.txt");

        FileWriter out1Write = new FileWriter(out1);
        FileWriter out2Write = new FileWriter(out2);

        out1Write.write("     " + fileNamesList[0] + "(number of lines - " + Math.ceil(numOfLines / 2.0) + "):");
        out2Write.write("     " + fileNamesList[0] + "(number of lines - " + Math.floor(numOfLines / 2.0) + "):");

        BufferedReader bufferedReader = new BufferedReader(fileReader);
        int i = 0;

        while (bufferedReader.ready()) {
            if (i < Math.ceil(numOfLines / 2.0)) {
                   out1Write.write("\n");
                   out1Write.write(bufferedReader.readLine());
                   i++;
            } else {
                    out2Write.write("\n");
                    out2Write.write(bufferedReader.readLine());
                }
        }
        bufferedReader.close();
        out1Write.close();
        out2Write.close();
    }

    private void outTar() throws IOException {
        File output = new File(fileNamesList[fileNamesList.length - 1]);
        FileWriter fileWrite = new FileWriter(output);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWrite);

        for (int i = 0; i < fileNamesList.length - 1; i++) {
            FileReader fileReader = new FileReader(fileNamesList[i]);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            long numOfLines = Files.lines(Paths.get(fileNamesList[i])).count();
            bufferedWriter.write("     " + fileNamesList[i] + "(number of lines - " + numOfLines + "):");
            bufferedWriter.newLine();

            while (bufferedReader.ready()) {
                bufferedWriter.write(bufferedReader.readLine());
                bufferedWriter.newLine();
            }
            bufferedReader.close();
        }
        bufferedWriter.close();
    }
}