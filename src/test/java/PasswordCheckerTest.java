import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class PasswordCheckerTest {
    static PasswordChecker sut;
    @BeforeEach
    public void beforeEach() {
        sut = new PasswordChecker();
        sut.setMinLength(8);
        sut.setMaxRepeats(3);
    }

    @Test
    public void testSetMinLength() {
        // Arrange
        Class <IllegalArgumentException> expected = IllegalArgumentException.class;

        // Act
        Executable executable1 = () -> sut.setMinLength(-1);
        Executable executable2 = () -> sut.setMinLength(3);

        // Assert
        Assertions.assertThrowsExactly(expected, executable1);
        Assertions.assertDoesNotThrow(executable2);
    }

    @Test
    public void testSetMaxRepeats() {
        // Arrange
        Class <IllegalArgumentException> expected = IllegalArgumentException.class;

        // Act
        Executable executable1 = () -> sut.setMaxRepeats(-1);
        Executable executable2 = () -> sut.setMaxRepeats(3);

        // Assert
        Assertions.assertThrows(expected, executable1);
        Assertions.assertDoesNotThrow(executable2);
    }

    @Test
    public void testVerify() {
        // Arrange
        String password = "design.1";
        boolean passwordFits;

        // Act
        passwordFits = sut.verify(password);

        // Assert
        Assertions.assertEquals(true, passwordFits);

    }

    public static Stream<Arguments> testVerifyList(){
        return Stream.of(
                Arguments.of("",          false),
                Arguments.of("des",       false),
                Arguments.of("design",    false),
                Arguments.of("design.",   false),
                Arguments.of("design.1",  true),
                Arguments.of("design.11",      true),
                Arguments.of("dessign.11",     true),
                Arguments.of("desssign.11",    true),
                Arguments.of("desssssign.11",  false),
                Arguments.of("ddesssign.11",   true),
                Arguments.of("dddesssign.11",  true),
                Arguments.of("ddddesssign.11", false),
                Arguments.of("ddesssign.111",  true),
                Arguments.of("ddesssign.1111", false)
        );
    }

    @ParameterizedTest
    @MethodSource
    public void testVerifyList(String password, boolean expected) {
        // Arrange
        boolean passwordFits;

        // Act
        passwordFits = sut.verify(password);

        // Assert
        Assertions.assertEquals(expected, passwordFits);

    }


}
