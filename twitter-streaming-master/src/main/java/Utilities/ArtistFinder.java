package Utilities;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

//Singleton
public class ArtistFinder
{
    private static ArtistFinder instance = null;

    protected ArtistFinder()
    {
    }

    public static ArtistFinder getInstance()
    {
        if(instance == null)
        {
            instance = new ArtistFinder();
        }
        return instance;
    }

    private String finalArtist;

    private List<String> artists = new ArrayList<String>();

    private List<String> artistsFullName = new ArrayList<String>();

    public void loadArtists()
    {
        BufferedReader artistReader = null;
        BufferedReader artistsFullNameReader = null;
        try
        {
            artistReader = new BufferedReader(new FileReader(new File(
                    "./src/artistsToLoad.txt")));
            artistsFullNameReader = new BufferedReader(new FileReader(new File(
                    "./src/artistsToSave.txt")));
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        String word;

        try {
            while ((word = artistReader.readLine()) != null)
            {
                artists.add(word);
            }
            while ((word = artistsFullNameReader.readLine()) != null)
            {
                artistsFullName.add(word);
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        try
        {
            artistReader.close();
            artistsFullNameReader.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    public void findArtist(String input)
    {

        input = TextUtilities.limpiarAcentos(input);
        input = input.toLowerCase();
        input = input.trim();
        input = input.replaceAll("ñ", "n");
        input = input.replaceAll(",", " ");
        input = input.replaceAll("  ", " ");
        input = input.replaceAll("[^a-zA-Z0-9\\s]", "");

        //System.out.println("> Modified text: " + input);

        String[] words = input.split(" ");

        this.finalArtist = "unknown";

        for (int i = 0; i < words.length; i++)
        {
            if (artists.contains(words[i]))
            {
                int index = artists.indexOf(words[i]);
                this.finalArtist = artistsFullName.get(index);
            }

        }

        //System.out.println("> Artista: " + this.finalArtist);

    }

    public String getFinalArtist()
    {
        return this.finalArtist;
    }


}
