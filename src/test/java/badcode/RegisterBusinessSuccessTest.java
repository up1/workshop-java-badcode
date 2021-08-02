package badcode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RegisterBusinessSuccessTest {

    @Test
    @DisplayName("สามารถบันทึก speaker ลง database ได้")
    public void case07() {
        RegisterBusiness registerBusiness = new RegisterBusiness();
        Speaker speaker = new Speaker();
        speaker.setFirstName("Somkiat");
        speaker.setLastName("Pui");
        speaker.setEmail("somkiat@gmail.com");
        // DAO = Data Access Object
        // DTO = Data Transfer Object
        // BO = Business Object
        // VO = Value Object
        int id = registerBusiness.register(new SpeakerRepository() {
            @Override
            public Integer saveSpeaker(Speaker speaker) {
                return 100;
            }
        }, speaker);
        assertEquals(100, id);
    }

}