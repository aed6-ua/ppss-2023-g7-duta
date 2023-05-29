package ejercicio1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class MultiPath1Test {
    @Test
    public void casoPrueba1() {
        MultipathExample clasePrueba = new MultipathExample();
        Assertions.assertEquals(12, clasePrueba.multiPath1(6,6,0));
    }

    @Test
    public void casoPrueba2() {
        MultipathExample clasePrueba = new MultipathExample();
        Assertions.assertEquals(1, clasePrueba.multiPath1(1,1,1));
    }

    @Test
    public void casoPrueba3() {
        MultipathExample clasePrueba = new MultipathExample();
        Assertions.assertEquals(8, clasePrueba.multiPath1(3,6,2));
    }

    @ParameterizedTest(name = "a = {0}, b = {1}, c = {2}")
    @MethodSource("casosDePrueba1")
    public void casoPrueba4(int a, int b, int c, int result) {
        MultipathExample clasePrueba = new MultipathExample();
        Assertions.assertEquals(result, clasePrueba.multiPath2(a,b,c));
    }

    private static Stream<Arguments> casosDePrueba1() {
        return Stream.of(
                Arguments.of(6, 3, 6, 15),
                Arguments.of(5, 3, 5, 5),
                Arguments.of(6, 5, 5, 5)
        );
    }

    @ParameterizedTest(name = "a = {0}, b = {1}, c = {2}")
    @MethodSource("casosDePrueba2")
    public void casoPrueba5(int a, int b, int c, int result) {
        MultipathExample clasePrueba = new MultipathExample();
        Assertions.assertEquals(result, clasePrueba.multiPath3(a,b,c));
    }

    private static Stream<Arguments> casosDePrueba2() {
        return Stream.of(
                Arguments.of(6, 3, 6, 15),
                Arguments.of(5, 3, 5, 5),
                Arguments.of(6, 5, 5, 5),
                Arguments.of(5, 5, 5, 5)
        );
    }
}
