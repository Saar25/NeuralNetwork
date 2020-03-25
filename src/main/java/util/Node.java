package util;

import java.util.List;

public interface Node<T> {

    List<? extends Node<T>> getChildren();

    T getValue();

}
