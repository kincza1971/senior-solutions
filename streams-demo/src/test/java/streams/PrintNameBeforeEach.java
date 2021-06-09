package streams;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;

public interface PrintNameBeforeEach {
    @BeforeEach
    default void printTestName (TestInfo testInfo) {
        System.out.println(testInfo.getDisplayName());
    }
}
