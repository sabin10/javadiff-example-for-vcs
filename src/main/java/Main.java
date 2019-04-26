
import difflib.*;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        String RIVISED = "file1.txt";
        String ORIGINAL = "file2.txt";
//        String result = getDiff(ORIGINAL, getPatch(ORIGINAL, RIVISED));
//        System.out.println(result);
        Patch patch = getPatch(ORIGINAL, RIVISED);


        List<ChunkSimulate> chunkSimulateList = new ArrayList<ChunkSimulate>();
        for (Delta delta : patch.getDeltas()) {
            System.out.println(delta);
            String deltaType;
            int positionOrig;
            String linesOrig;
            int positionRevis;
            String linesRevis;

            deltaType = delta.getType().toString();
            positionOrig = delta.getOriginal().getPosition();
            linesOrig = linesToString(delta.getOriginal().getLines());

            positionRevis = delta.getRevised().getPosition();
            linesRevis = linesToString(delta.getRevised().getLines());

            chunkSimulateList.add(new ChunkSimulate(deltaType, positionOrig, linesOrig, positionRevis, linesRevis));
        }


        List<Delta> myDeltas = new ArrayList<Delta>();

        for (ChunkSimulate chunkSimulate : chunkSimulateList) {
//            System.out.println(chunkSimulate.getDeltaType());
            String deltaType = chunkSimulate.getDeltaType();
            int positionOriginal = chunkSimulate.getPositionOriginal();
            int positionRevised = chunkSimulate.getPositionRevised();
            List<?> listLinesOriginal = stringToLinesList(chunkSimulate.getLinesOriginal());
            List<?> listLinesRevised = stringToLinesList(chunkSimulate.getLinesRevised());

            if (deltaType.equals("INSERT")) {
                myDeltas.add(new InsertDelta(new Chunk(positionOriginal, listLinesOriginal), new Chunk(positionRevised, listLinesRevised)));
            } else if (deltaType.equals("CHANGE")) {
                myDeltas.add(new ChangeDelta(new Chunk(positionOriginal, listLinesOriginal), new Chunk(positionRevised, listLinesRevised)));
            } else if (deltaType.equals("DELETE")) {
                myDeltas.add(new DeleteDelta(new Chunk(positionOriginal, listLinesOriginal), new Chunk(positionRevised, listLinesRevised)));
            }
        }

        System.out.println("=====================");
        Patch myPatch = new Patch();
        for (Delta myDelta : myDeltas) {
            System.out.println(myDelta);
            myPatch.addDelta(myDelta);
        }

        String result = getDiff(ORIGINAL, myPatch);
        System.out.println(result);

    }

    private static String linesToString(List<?> lines) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < lines.size(); i++) {
            res.append(lines.get(i));
            res.append("\n");
        }
        return res.toString();
    }

    private static List<?> stringToLinesList(String str) {
        String[] arr = str.split("\n");
        List<?> list = Arrays.asList(arr);
        return list;
    }

    private static List<String> fileToLines(String filename) {
        List<String> lines = new LinkedList<String>();
        String line = "";
        try {
            BufferedReader in = new BufferedReader(new FileReader(filename));
            while ((line = in.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public static Patch getPatch(String ORIGINAL, String RIVISED) {
        List<String> original = fileToLines(ORIGINAL);
        List<String> revised  = fileToLines(RIVISED);

        Patch patch = DiffUtils.diff(original, revised);
        return patch;
    }

    public static String getDiff(String ORIGINAL, Patch patch) {
        List<String> original = fileToLines(ORIGINAL);

        try {
            List<String> result = (List<String>) patch.applyTo(original);

            StringBuilder stringList = new StringBuilder();

            for(int i = 0; i < result.size(); i++) {
                String s = result.get(i);
                if(i != result.size() - 1)
                    stringList.append(s + "\n");
                else
                    stringList.append(s);
            }

            String merge = String.valueOf(stringList);
            return merge;
        } catch (PatchFailedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
