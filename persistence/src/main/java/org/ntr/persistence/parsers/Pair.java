package org.ntr.persistence.parsers;

public class Pair<F, S> {

    private F first;
    private S second;


    public Pair(F first, S second) {
        this.first = first;
        this.second = second;
    }

    public S getSecond() {
        return second;
    }

    public void setSecond(S second) {
        this.second = second;
    }
}
