package br.com.dio.models;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Space {
    private final boolean isFixed;
    private final int expected;
    private Integer actual;

    public Space(boolean isFixed, int expected) {
        this.isFixed = isFixed;
        this.expected = expected;
        if (isFixed) {
            this.actual = expected;
        }
    }

    public void setActual(Integer actual) {
        if (isFixed) return;
        this.actual = actual;
    }

    public void clearSpace() {
        setActual(null);
    }
}
