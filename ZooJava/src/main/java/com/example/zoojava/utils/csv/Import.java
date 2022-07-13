package com.example.zoojava.utils.csv;

import java.nio.file.Path;
import java.util.List;

public interface Import<T> {
    List<T> importData();
}
