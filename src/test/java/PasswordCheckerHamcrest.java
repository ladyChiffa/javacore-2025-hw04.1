import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.stream.Stream;

public class PasswordCheckerHamcrest {
    static PasswordChecker sut;
    @BeforeEach
    public void beforeEach() {
        sut = new PasswordChecker();
        sut.setMinLength(8);
        sut.setMaxRepeats(3);
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
        assertThat(expected, equalTo(passwordFits));

    }


    @Test
    public void testConfigLength() {
        // Arrange
        int minLength = 7;

        // Act
        sut.setMinLength(minLength);

        // Assert
        assertThat(sut, hasProperty("minLength", equalTo(minLength)));
    }
}
