package org.example.utils;

import lombok.Value;

@Value
public class Pair<T,U> {
    T firstElement;
    U secondElement;
}
