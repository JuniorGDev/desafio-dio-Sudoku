package br.com.dio.models;

import lombok.Getter;

public enum GameStatusEnum {
    COMPLETED("JOGO COMPLETO"),
    INCOMPLETED("JOGO NAO COMPLETO"),
    NON_STARTED("JOGO NAO INICIADO");

    @Getter
    private final String label;

    GameStatusEnum(final String label) {
        this.label = label;
    }
}
