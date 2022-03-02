package Password;

import cc2.kernel.Password;
import cc2.kernel.exception.PasswordException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class PasswordTest {

    @Test
    void createInvalidPassword(){
        try{
            Password password = new Password("1234");
            fail("Should throw exception");
        }catch (PasswordException e){
            assert(true);
        }
    }


}
