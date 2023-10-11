package Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {

    UI ui;
    Panel gp;

    File file = new File("./src/HighScores.txt");
    Scanner kb = new Scanner(System.in);
    Scanner readFile = new Scanner(file);
    PrintWriter fileWriter;
    ArrayList<String> highScores, highScoresNames;

    public FileHandler (UI ui, Panel gp) throws FileNotFoundException {
        this.ui = ui;
        this.gp  = gp;

        highScores = new ArrayList<>();
        highScoresNames = new ArrayList<>();
    }

    public void setHsArrayLists() {
        for (int i = 0; i < 5; i++)
            highScores.add(readFile.nextLine());

        for (int i = 0; i < 5; i++)
            highScoresNames.add(readFile.nextLine());
    }

    public void setHighScores() {
        int score = gp.score;

        for (int i = 0; i < highScores.size(); i++) {
            if (score > Integer.parseInt(highScores.get(i))) {
                highScores.add(i, ""+score);

                System.out.println("Enter Player Name:: ");
                highScoresNames.add(i, kb.nextLine());

                break;
            }
        }

        if (highScores.size() > 5) {
            highScores.remove(5);
            highScoresNames.remove(5);
        }

        setHighScoresFile();
    }

    private void setHighScoresFile() {
        try {
            System.out.println(highScores);
            System.out.println(highScoresNames);

            fileWriter = new PrintWriter(file);

            for (String i: highScores)
                fileWriter.println(i);

            for (String i: highScoresNames)
                fileWriter.println(i);

            fileWriter.close();
        }
        catch(Exception e) {
            System.out.println("Error:: " + e);
        }
    }
}