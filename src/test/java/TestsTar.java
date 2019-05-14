import  com.viktorov.task2.*;

import java.io.*;
import java.util.*;
import org.junit.*;

import static org.junit.Assert.*;

public class TestsTar {
    private Tar tar;


    private void assertFileContent(String inputFile1, String inputFile2) throws FileNotFoundException {
        Scanner scanner1 = new Scanner(new File(inputFile1));
        Scanner scanner2 = new Scanner(new File(inputFile2));

        List firstFile = new ArrayList<String>();
        List secondFile = new ArrayList<String>();

        while (scanner1.hasNextLine())
            firstFile.add(scanner1.nextLine());

        while (scanner2.hasNextLine())
            secondFile.add(scanner2.nextLine());

        assertEquals(firstFile, secondFile);
    }

    @After
    public void reset() {
        tar = null;
    }

    @Test
    public void testU() throws FileNotFoundException {
        tar = new Tar(new String[]{"files\\InputFiles\\inputU.txt"});
        try {
            tar.tar("-u");
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertFileContent("files\\OutputFiles\\output1.txt", "files\\OutputFiles\\UTest1.txt");
        assertFileContent("files\\OutputFiles\\output2.txt", "files\\OutputFiles\\UTest2.txt");
    }

    @Test
    public void testOut() throws FileNotFoundException {
        tar = new Tar(new String[]{
                "files\\InputFiles\\inputOut1.txt",
                "files\\InputFiles\\inputOut2.txt",
                "files\\InputFiles\\inputOut3.txt",
                "files\\OutputFiles\\output3.txt"
        });
        try {
            tar.tar("-out");
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertFileContent("files\\OutputFiles\\output3.txt", "files\\OutputFiles\\OutTest.txt");
    }
}
