package br.com.fiap.tc.gestaoglicemicaapi.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum CategoriaDiabeteEnum {
    PRE_DIABETES,
    DIABETE_I,
    DIABETE_II,
    DIABETE_GESTACIONAL
}
