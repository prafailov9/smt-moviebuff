package org.ntr.persistence.parsers;

public interface DataParser<T> {

    Pair<String, String> parse(T data);

}
