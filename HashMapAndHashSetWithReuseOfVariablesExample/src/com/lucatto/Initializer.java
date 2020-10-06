package com.lucatto;

import java.util.*;

public class Initializer {

    private Initializer() {
    }

    public static Initializer getInstance() {
        return new Initializer();
    }

    private final Map<String, Place> continent = new HashMap<>();
    private final Set<Place> countries = new HashSet<>();

    public void initializer() throws Exception {
        Place tempContinent;
        Place tempCountry;

        tempContinent = new Place("Europe", "continent", 741_400_000);
        continent.put(tempContinent.getName(), tempContinent);

        tempCountry = new Place("Ireland", "country", 4_904_000);
        countries.add(tempCountry);
        tempContinent.addSubArea(tempCountry);

        tempCountry = new Place("Italy", "country", 60_360_000);
        tempContinent.addSubArea(tempCountry);
        countries.add(tempCountry);

        tempCountry = new Place("France", "country", 66_990_000);
        tempContinent.addSubArea(tempCountry);
        countries.add(tempCountry);

        tempContinent = new Place("America", "continent", 1_002_000_000);
        continent.put(tempContinent.getName(), tempContinent);

        tempCountry = new Place("Canada", "country", 37_590_000);
        countries.add(tempCountry);
        tempContinent.addSubArea(tempCountry);

        tempCountry = new Place("Brazil", "country", 209_500_000);
        countries.add(tempCountry);
        tempContinent.addSubArea(tempCountry);

        System.out.println("All countries in this application:");
        for (Place country : countries) {
            System.out.println("\t" + country.getName());
        }
        System.out.println();
        Place continent1 = continent.get("Europe");
        System.out.println("Countries in " + continent1.getName() + ":");
        for (Place countries : continent1.getSubAreas()) {
            System.out.println("  " + countries.getName());
//            System.out.println("\t" + countries.getAreaType());
            System.out.println("\tPopulation: " + countries.getPopulation());
        }
        System.out.println();
        Place continent2 = continent.get("America");
        System.out.println("Countries in " + continent2.getName() + ":");
        for (Place countries : continent2.getSubAreas()) {
            System.out.println("  " + countries.getName());
//            System.out.println("\t" + countries.getAreaType());
            System.out.println("\tPopulation: " + countries.getPopulation());
        }
    }
}
