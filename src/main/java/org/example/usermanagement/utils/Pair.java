package org.example.usermanagement.utils;

import lombok.Value;

@Value
public class Pair<T,U> {
    T firstElement;
    U secondElement;
}
