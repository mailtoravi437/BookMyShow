package org.example.bookmyshow.model;

import lombok.Data;
import lombok.NonNull;
import java.util.ArrayList;

import java.util.List;

@Data
public class Theatre {
    private final String id;
    private final String name;
    private final List<Screen> screens;
    private City city;


    public Theatre(@NonNull final String id, @NonNull final String name, @NonNull final City city) {
        this.id = id;
        this.name = name;
        this.screens = new ArrayList<>();
        this.city = city;
    }

    public void addScreens(@NonNull final Screen screen) {
        screens.add(screen);
    }
}
