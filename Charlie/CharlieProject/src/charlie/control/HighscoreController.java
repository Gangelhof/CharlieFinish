

package charlie.control;

import charlie.model.ScoreComparator;
import charlie.model.Stash;
import charlie.model.User;
import java.util.*;
import java.io.*;

public class HighscoreController implements java.io.Serializable
{
     // An arraylist of the type "score" we will use to work with the scores inside the class
    private ArrayList<User> scores;

    // The name of the file where the highscores will be saved
    private static final String highscoreFile = "scores.dat";

    //Initialising an in and outputStream for working with the file
    ObjectOutputStream outputStream = null;
    ObjectInputStream inputStream = null;

    public HighscoreController()
    {
        //initialising the scores-arraylist
        scores = new ArrayList<User>();
    }


    public ArrayList<User> getScores() 
    {
        loadScoreFile();
        sort();
        return scores;
    }
    
    
    private void sort() 
    {
        ScoreComparator comparator = new ScoreComparator();
        Collections.sort(scores, comparator);
    }


   public void addScore(String name, Stash stash ) 
   {
        loadScoreFile();
        scores.add(new User(name, stash));
        updateScoreFile();
   }
   
    public void loadScoreFile() 
    {
        try 
        {
            inputStream = new ObjectInputStream(new FileInputStream(highscoreFile));
            scores = (ArrayList<User>) inputStream.readObject();
        } 
        catch (FileNotFoundException e) 
        {
            System.out.println("[Laad] FNF Error: " + e.getMessage());
        } 
        catch (IOException e) 
        {
            System.out.println("[Laad] IO Error: " + e.getMessage());
        } 
        catch (ClassNotFoundException e) 
        {
            System.out.println("[Laad] CNF Error: " + e.getMessage());
        } 
        finally 
        {
            try 
            {
                if (outputStream != null) 
                {
                    outputStream.flush();
                    outputStream.close();
                }
            } 
            catch (IOException e) 
            {
                System.out.println("[Laad] IO Error: " + e.getMessage());
            }
        }
    }
    
    
    public void updateScoreFile() 
    {
        try 
        {
            outputStream = new ObjectOutputStream(new FileOutputStream(highscoreFile));
            outputStream.writeObject(scores);
        } 
        catch (FileNotFoundException e) 
        {
            System.out.println("[Update] FNF Error: " + e.getMessage() + ",the program will try and make a new file");
        } 
        catch (IOException e) 
        {
            System.out.println("[Update] IO Error: " + e.getMessage());
        } 
        finally 
        {
            try 
            {
                if (outputStream != null) 
                {
                    outputStream.flush();
                    outputStream.close();
                }
            } 
            catch (IOException e) 
            {
                System.out.println("[Update] Error: " + e.getMessage());
            }
        }
}
    
    public String getHighscoreString() 
    {
        String highscoreString = "";
	int max = 10;

        ArrayList<User> scores;
        scores = getScores();

        int i = 0;
        int x = scores.size();
        if (x > max) {
            x = max;
        }
        while (i < x) {
            highscoreString += (i + 1 + ": ") +  "\t\t" + scores.get(i).getStash().getMoney() + " " + scores.get(i).getName() + "\n";
            i++;
        }
        return highscoreString;
        
        
    }
}
