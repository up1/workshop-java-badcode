package badcode;

import java.util.Arrays;

public class RegisterBusiness {

    public Integer register(SpeakerRepository repository, Speaker speaker) {
        Integer speakerId;
        // Fail fast
        if (isEmptyOrNull(speaker.getFirstName())) {
            throw new ArgumentNullException("First name is required.");
        }
        if (isEmptyOrNull(speaker.getLastName())) {
            throw new ArgumentNullException("Last name is required.");
        }
        if (isEmptyOrNull(speaker.getEmail())) {
            throw new ArgumentNullException("Email is required.");
        }

        if (isValidEmailDomain(speaker.getEmail())) {
            throw new SpeakerDoesntMeetRequirementsException("Speaker doesn't meet our standard rules.");
        }

        // Main process :: Save Speaker to Repository
        int exp = speaker.getExp();
        speaker.setRegistrationFee(getFee(exp));
        try {
            speakerId = repository.saveSpeaker(speaker);
        } catch (Exception exception) {
            throw new SaveSpeakerException("Can't save a speaker.");
        }

        return speakerId;
    }
    // Coupling vs Cohesion !!
    private boolean isValidEmailDomain(String email) {
        String[] domainsOfEmail = {"gmail.com", "live.com"};
        String emailDomain = getEmailDomain(email); // Avoid ArrayIndexOutOfBound
        return Arrays.stream(domainsOfEmail).filter(it -> it.equals(emailDomain)).count() != 1;
    }

    private boolean isEmptyOrNull(String firstName) {
        return firstName == null || firstName.trim().equals("");
    }

    int getFee(int experinceYear) {
        int fee = 0;
        if (experinceYear <= 1) {
            fee = 500;
        } else if (experinceYear <= 3) {
            fee = 250;
        } else if (experinceYear <= 5) {
            fee = 100;
        } else if (experinceYear <= 9) {
            fee = 50;
        }
        return fee;
    }

    public String getEmailDomain(String email) {
        String[] inputs = email.trim().split("@");
        if (inputs.length == 2) return inputs[1];
        throw new DomainEmailInvalidException();
    }

}
