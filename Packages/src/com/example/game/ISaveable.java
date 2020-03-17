package com.example.game;

import java.util.List;

public interface ISaveable {
    List<String> save();
    void load(List<String> savedData);
}
