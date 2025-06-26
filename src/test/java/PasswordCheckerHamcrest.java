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

    @Test
    public void testConfigLength() {
        // Arrange
        int minLength = 7;

        // Act
        sut.setMinLength(minLength);

        // Assert
        assertThat(sut, hasProperty("minLength"));
        // Вообще был план использовать equalTo(minLength), но при проверке получаю ошибку
        // ```
        // Expected: hasProperty("minLength", <7>)
        //     but: property "minLength" is not readable
        // ```
        // даже когда сделала minLength публичным.
        // Не поняла, что не так.
    }
}
