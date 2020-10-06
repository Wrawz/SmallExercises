package com.lucatto;

import java.util.HashSet;
import java.util.Set;

public final class Place implements Comparable<Place> {

    private final String name;
    private final String areaType;
    private final long population;
    private final Set<Place> subAreas;

    public Place(String name, String areaType, long population) throws Exception {
        this.name = name;
        if (setAreaType(areaType)) this.areaType = areaType;
        else throw new Exception("Couldn't create \"" + this.name + "\" object with such parameters.");
        this.population = population;
        this.subAreas = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public String getAreaType() {
        return areaType;
    }

    public long getPopulation() {
        return population;
    }

    public Set<Place> getSubAreas() {
        return new HashSet<>(this.subAreas);
    }

    public boolean addSubArea(Place place) {
        return this.subAreas.add(place);
    }

    private boolean setAreaType(String areaType) {
        return areaType.toLowerCase().equals("continent") ||
                areaType.toLowerCase().equals("country");
    }

    @Override
    public int compareTo(Place p2) {
        return this.name.compareToIgnoreCase(p2.getName());
    }
}
