package pro.sky.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pro.sky.Exception.ArrayLengthExceeded;
import pro.sky.Exception.ValueError;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static pro.sky.List.Constants.*;


class StringListImplTest {

    @BeforeEach
    public void beforeEach() {
//        String[] strings = {Hello, Book, Bye, News};
//        when(stringList.toArray()).thenReturn(strings);
    }

    private final StringList stringList = new StringListImpl();
    private final StringList stringList1 = new StringListImpl();

    @ParameterizedTest
    @MethodSource("params")
    void addPositive(String item) {
        String expected = item;
        assertThat(stringList.add(item)).isEqualTo(expected);
    }

    @Test
    void addNegative() {
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> stringList.add(zer));
    }

    @Test
    void testNegative() {
        assertThatExceptionOfType(ArrayLengthExceeded.class)
                .isThrownBy(() -> stringList.add(ONE, Hello));
    }

    @Test
    void setNegative() {
        assertThatExceptionOfType(ArrayLengthExceeded.class)
                .isThrownBy(() -> stringList.set(ONE, Hello));
    }

    @ParameterizedTest
    @MethodSource("params")
    void remove(String item) {
        assertThatExceptionOfType(ValueError.class)
                .isThrownBy(() -> stringList.remove(item));
        String expected = item;
        assertThat(stringList.add(item)).isEqualTo(expected);
        assertThat(stringList.remove(item)).isEqualTo(expected);


    }

    @ParameterizedTest
    @MethodSource("params")
    void testRemove(String item) {
        assertThatExceptionOfType(ArrayLengthExceeded.class)
                .isThrownBy(() -> stringList.remove(ONE));
        String expected = item;
        assertThat(stringList.add(item)).isEqualTo(expected);
        assertThat(stringList.remove(zero)).isEqualTo(expected);

    }

    @ParameterizedTest
    @MethodSource("params")
    void contains(String item) {
        Boolean expected = false;
        assertThat(stringList.contains(item)).isEqualTo(expected);

    }

    @ParameterizedTest
    @MethodSource("params")
    void indexOf(String item) {
        int expected = 0;
        String expected1 = item;
        assertThat(stringList.add(item)).isEqualTo(expected1);
        assertThat(stringList.indexOf(item)).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("params")
    void lastIndexOf(String item) {
        int expected = 0;
        String expected1 = item;
        assertThat(stringList.add(item)).isEqualTo(expected1);
        assertThat(stringList.lastIndexOf(item)).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("params")
    void get(String item) {
        String expected = item;
        assertThatExceptionOfType(ArrayLengthExceeded.class)
                .isThrownBy(() -> stringList.remove(ONE));
        assertThat(stringList.add(item)).isEqualTo(expected);
        assertThat(stringList.get(zero)).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("params")
    void testEquals(String item) {
        boolean expected = true;
        String expected1 = item;
        assertThat(stringList.add(item)).isEqualTo(expected1);
        assertThat(stringList1.add(item)).isEqualTo(expected1);
        assertThat(stringList.equals(stringList1)).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("params")
    void size(String item) {
        int expected = ONE;
        assertThat(stringList.add(item)).isEqualTo(item);
        assertThat(stringList.size()).isEqualTo(expected);
    }

    @Test
    void isEmpty() {
        boolean expected = true;
        assertThat(stringList.isEmpty()).isEqualTo(expected);
    }

//    @ParameterizedTest
//    @MethodSource("params")
//    void clear(String item) {
//        String[] expected = new String[0];
//    }

    @ParameterizedTest
    @MethodSource("params")
    void toArray(String item) {
        String[] expected = new String[]{item};
        assertThat(stringList.add(item)).isEqualTo(item);
        assertThat(stringList.toArray()).isEqualTo(expected);
    }

    public static Stream<Arguments> params() {
        return Stream.of(
                Arguments.of(Hello),
                Arguments.of(Book),
                Arguments.of(Bye),
                Arguments.of(News)
        );
    }
}