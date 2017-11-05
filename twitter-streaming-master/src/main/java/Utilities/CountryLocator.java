package Utilities;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CountryLocator
{
    private static CountryLocator instance = null;

    protected CountryLocator()
    {

    }

    public static CountryLocator getInstance()
    {
        if(instance == null)
        {
            instance = new CountryLocator();
        }
        return instance;
    }

    private List<String> countries = new ArrayList<String>();

    private List<String> argentina = new ArrayList<String>();
    private List<String> bolivia = new ArrayList<String>();
    private List<String> chile = new ArrayList<String>();
    private List<String> colombia = new ArrayList<String>();
    private List<String> costaRica = new ArrayList<String>();
    private List<String> ecuador = new ArrayList<String>();
    private List<String> guatemala = new ArrayList<String>();
    private List<String> honduras = new ArrayList<String>();
    private List<String> mexico = new ArrayList<String>();
    private List<String> nicaragua = new ArrayList<String>();
    private List<String> panama = new ArrayList<String>();
    private List<String> paraguay = new ArrayList<String>();
    private List<String> peru = new ArrayList<String>();
    private List<String> salvador = new ArrayList<String>();
    private List<String> uruguay = new ArrayList<String>();
    private List<String> venezuela = new ArrayList<String>();

    private String finalCountry;

    public void loadCountries()
    {
        BufferedReader countryReader = null;
        try
        {
            countryReader = new BufferedReader(new FileReader(new File(
                    "./src/countries.txt")));
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        String word;

        try {
            while ((word = countryReader.readLine()) != null)
            {
                countries.add(word);
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        try
        {
            countryReader.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void loadCitiesByCountry()
    {
        BufferedReader argentinaReader = null;
        BufferedReader boliviaReader = null;
        BufferedReader chileReader = null;
        BufferedReader colombiaReader = null;
        BufferedReader costaRicaReader = null;
        BufferedReader ecuadorReader = null;
        BufferedReader guatemalaReader = null;
        BufferedReader hondurasReader = null;
        BufferedReader mexicoReader = null;
        BufferedReader nicaraguaReader = null;
        BufferedReader panamaReader = null;
        BufferedReader paraguayReader = null;
        BufferedReader peruReader = null;
        BufferedReader salvadorReader = null;
        BufferedReader uruguayReader = null;
        BufferedReader venezuelaReader = null;

        try
        {
            argentinaReader = new BufferedReader(new FileReader(new File(
                    "./src/citiesByCountry/argentina.txt")));
            boliviaReader = new BufferedReader(new FileReader(new File(
                    "./src/citiesByCountry/bolivia.txt")));
            chileReader = new BufferedReader(new FileReader(new File(
                    "./src/citiesByCountry/chile.txt")));
            colombiaReader = new BufferedReader(new FileReader(new File(
                    "./src/citiesByCountry/colombia.txt")));
            costaRicaReader = new BufferedReader(new FileReader(new File(
                    "./src/citiesByCountry/costaRica.txt")));
            ecuadorReader = new BufferedReader(new FileReader(new File(
                    "./src/citiesByCountry/ecuador.txt")));
            guatemalaReader = new BufferedReader(new FileReader(new File(
                    "./src/citiesByCountry/guatemala.txt")));
            hondurasReader = new BufferedReader(new FileReader(new File(
                    "./src/citiesByCountry/honduras.txt")));
            mexicoReader = new BufferedReader(new FileReader(new File(
                    "./src/citiesByCountry/mexico.txt")));
            nicaraguaReader = new BufferedReader(new FileReader(new File(
                    "./src/citiesByCountry/nicaragua.txt")));
            panamaReader = new BufferedReader(new FileReader(new File(
                    "./src/citiesByCountry/panama.txt")));
            paraguayReader = new BufferedReader(new FileReader(new File(
                    "./src/citiesByCountry/paraguay.txt")));
            peruReader = new BufferedReader(new FileReader(new File(
                    "./src/citiesByCountry/peru.txt")));
            salvadorReader = new BufferedReader(new FileReader(new File(
                    "./src/citiesByCountry/salvador.txt")));
            uruguayReader = new BufferedReader(new FileReader(new File(
                    "./src/citiesByCountry/uruguay.txt")));
            venezuelaReader = new BufferedReader(new FileReader(new File(
                    "./src/citiesByCountry/venezuela.txt")));
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        String word;

        try
        {
            while ((word = argentinaReader.readLine()) != null)
            {
                argentina.add(word);
            }
            while ((word = boliviaReader.readLine()) != null)
            {
                bolivia.add(word);
            }

            while ((word = chileReader.readLine()) != null)
            {
                chile.add(word);
            }

            while ((word = colombiaReader.readLine()) != null)
            {
                colombia.add(word);
            }
            while ((word = costaRicaReader.readLine()) != null)
            {
                costaRica.add(word);
            }
            while ((word = ecuadorReader.readLine()) != null)
            {
                ecuador.add(word);
            }
            while ((word = guatemalaReader.readLine()) != null)
            {
                guatemala.add(word);
            }

            while ((word = hondurasReader.readLine()) != null)
            {
                honduras.add(word);
            }
            while ((word = mexicoReader.readLine()) != null)
            {
                mexico.add(word);
            }
            while ((word = nicaraguaReader.readLine()) != null)
            {
                nicaragua.add(word);
            }

            while ((word = panamaReader.readLine()) != null)
            {
                panama.add(word);
            }

            while ((word = paraguayReader.readLine()) != null)
            {
                paraguay.add(word);
            }
            while ((word = peruReader.readLine()) != null)
            {
                peru.add(word);
            }
            while ((word = salvadorReader.readLine()) != null)
            {
                salvador.add(word);
            }

            while ((word = uruguayReader.readLine()) != null)
            {
                uruguay.add(word);
            }
            while ((word = venezuelaReader.readLine()) != null)
            {
                venezuela.add(word);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        try
        {
            argentinaReader.close();
            boliviaReader.close();
            chileReader.close();
            colombiaReader.close();
            costaRicaReader.close();
            ecuadorReader.close();
            guatemalaReader.close();
            hondurasReader.close();
            mexicoReader.close();
            nicaraguaReader.close();
            panamaReader.close();
            paraguayReader.close();
            peruReader.close();
            salvadorReader.close();
            uruguayReader.close();
            venezuelaReader.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void locateCountry(String input)
    {
        String[] words = null;
        //Se asume que el país es desconocido o no pertenece a sudamérica
        this.finalCountry = "unknown";

        //Si la ubicación del usuario no es null, se busca si pertenece a un país
        if(input != null)
        {
            //System.out.println("Original location: " + input);

            input = TextUtilities.limpiarAcentos(input);
            input = input.toLowerCase();
            input = input.trim();
            input = input.replaceAll("ñ", "n");
            input = input.replaceAll(",", " ");
            input = input.replaceAll("  ", " ");
            input = input.replaceAll("[^a-zA-Z0-9\\s]", "");

            //System.out.println("Modified location: " + input);

            words = input.split(" ");

            for (int i = 0; i < words.length; i++)
            {
                if (countries.contains(words[i]))
                {
                    this.finalCountry = words[i];
                }
            }
        }

        /*
        Si la ubicación del usuario no es null y no contiene el nombre de un país,
        se busca por las ciudades principales de cada país para determinar el país.
        */
        if(input != null && this.finalCountry.equals("unknown"))
        {
            //Buscar en argentina
            for (int i = 0; i < words.length; i++)
            {
                if (argentina.contains(words[i]))
                {
                    this.finalCountry = "argentina";
                }
            }
            //Buscar en bolivia
            for (int i = 0; i < words.length; i++)
            {
                if (bolivia.contains(words[i]))
                {
                    this.finalCountry = "bolivia";
                }
            }
            //Buscar en chile
            for (int i = 0; i < words.length; i++)
            {
                if (chile.contains(words[i]))
                {
                    this.finalCountry = "chile";
                }
            }
            //Buscar en colombia
            for (int i = 0; i < words.length; i++)
            {
                if (colombia.contains(words[i]))
                {
                    this.finalCountry = "colombia";
                }
            }
            //Buscar en costaRica
            for (int i = 0; i < words.length; i++)
            {
                if (costaRica.contains(words[i]))
                {
                    this.finalCountry = "rica";
                }
            }
            //Buscar en ecuador
            for (int i = 0; i < words.length; i++)
            {
                if (ecuador.contains(words[i]))
                {
                    this.finalCountry = "ecuador";
                }
            }
            //Buscar en guatemala
            for (int i = 0; i < words.length; i++)
            {
                if (guatemala.contains(words[i]))
                {
                    this.finalCountry = "guatemala";
                }
            }
            //Buscar en honduras
            for (int i = 0; i < words.length; i++)
            {
                if (honduras.contains(words[i]))
                {
                    this.finalCountry = "honduras";
                }
            }
            //Buscar en mexico
            for (int i = 0; i < words.length; i++)
            {
                if (mexico.contains(words[i]))
                {
                    this.finalCountry = "mexico";
                }
            }
            //Buscar en nicaragua
            for (int i = 0; i < words.length; i++)
            {
                if (nicaragua.contains(words[i]))
                {
                    this.finalCountry = "nicaragua";
                }
            }
            //Buscar en panama
            for (int i = 0; i < words.length; i++)
            {
                if (panama.contains(words[i]))
                {
                    this.finalCountry = "panama";
                }
            }
            //Buscar en paraguay
            for (int i = 0; i < words.length; i++)
            {
                if (paraguay.contains(words[i]))
                {
                    this.finalCountry = "paraguay";
                }
            }
            //Buscar en peru
            for (int i = 0; i < words.length; i++)
            {
                if (peru.contains(words[i]))
                {
                    this.finalCountry = "peru";
                }
            }
            //Buscar en salvador
            for (int i = 0; i < words.length; i++)
            {
                if (salvador.contains(words[i]))
                {
                    this.finalCountry = "salvador";
                }
            }
            //Buscar en uruguay
            for (int i = 0; i < words.length; i++)
            {
                if (uruguay.contains(words[i]))
                {
                    this.finalCountry = "uruguay";
                }
            }
            //Buscar en venezuela
            for (int i = 0; i < words.length; i++)
            {
                if (venezuela.contains(words[i]))
                {
                    this.finalCountry = "venezuela";
                }
            }
        }

        //System.out.println(">Located country: " + this.finalCountry);
    }

    public String getFinalCountry() {
        return finalCountry;
    }

    public void setFinalCountry(String finalCountry) {
        this.finalCountry = finalCountry;
    }
}
