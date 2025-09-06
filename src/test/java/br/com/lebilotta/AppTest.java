package br.com.lebilotta;


import br.com.lebilotta.Enum.Status;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    //Testes Tradicionais
    @Test
    void deveValidarStringComConteudo() {
        assertTrue(StringUtils.isNotBlank("Java"));
    }

    @Test
    void deveValidarStringComEspacos() {
        assertFalse(StringUtils.isNotBlank("   "));
    }

    @Test
    void deveValidarStringVazia() {
        assertFalse(StringUtils.isNotBlank(""));
    }

    //Teste utilizando @ValueSource
    @ParameterizedTest
    @ValueSource(strings = {"Java", "JUnit", "Spring"})
    void deveValidarStringsNaoVazias(String palavra) {
        assertTrue(palavra.length() > 0);
    }

    //Teste utilizando @NullSource e @EmptySource
    @ParameterizedTest
    @NullSource
    @EmptySource
    @ValueSource(strings = {" ", "\t"})
    void deveRetornarFalsoParaStringsInvalidas(String texto) {
        assertFalse(StringUtils.isNotBlank(texto));
    }

    //Testes utilizando @EnumSource
    @ParameterizedTest
    @EnumSource(Status.class)
    void deveValidarStatusNaoNulo(Status status) {
        assertNotNull(status);
    }

    @ParameterizedTest
    @EnumSource(value = Status.class, names = { "ATIVO", "INATIVO"})
    void deveValidarApenasStatusPermitidos(Status status) {
        assertTrue(status == Status.ATIVO || status == Status.INATIVO);
    }

    //Teste utilizando @MethodSource
    @ParameterizedTest
    @MethodSource("fornecerNumerosParaSoma")
    void deveSomarNumerosCorretamente(int a, int b, int esperado) {
        assertEquals(esperado, a + b);
    }

    static Stream< Arguments > fornecerNumerosParaSoma() {
        return Stream.of(
                Arguments.of(1, 2, 3),
                Arguments.of(10, 5, 15),
                Arguments.of(-1, 1, 0)
        );
    }
}
