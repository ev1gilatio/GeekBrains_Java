import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class ArrayProcessingTest {
    private ArrayProcessingTest() {

    }

    @ParameterizedTest
    @MethodSource("arraysForArrayTransformation")
    void testSuccessfulTransformationOfArray(int[] expectation, int[] arr) {
        Assertions.assertArrayEquals(expectation, (ArrayProcessing.transformArray(arr)));
    }

    @ParameterizedTest
    @MethodSource("arraysForArray1and4check")
    void testSuccessfulCheckFor1and4inArray(int[] arr) {
        boolean b1 = ArrayProcessing.checkArrayFor1and4(arr);
        Assertions.assertTrue(b1);
    }

    private static Stream<Arguments> arraysForArrayTransformation() {
        return Stream.of(
                Arguments.of(new int[] {0, 1, 2, 3}, new int[] {4, 0, 1, 2, 3}),
                Arguments.of(new int[] {0, 0, 0}, new int[] {1, 3, 5 ,7, 9}),
                Arguments.of(new int[] {1, 4}, new int[] {4, 4, 4})
        );
    }

    private static Stream<Arguments> arraysForArray1and4check() {
        return Stream.of(
                Arguments.of(new int[] {1, 2, 3, 4, 5, 6}),
                Arguments.of(new int[] {0}),
                Arguments.of(new int[] {0, 1, 1, 2, 3, 4, 4}),
                Arguments.of(new int[] {2, 3, 5})
        );
    }
}
