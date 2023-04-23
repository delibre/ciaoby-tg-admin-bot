package by.ciao.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class User {

    private String username;
    private String fullName;
    private String referral;
    private String phone;
    private String englishLvl;
    private String numOfCorrectAnswers;

    @Override
    public String toString() {
        return """
                User{
                    username: '%s',
                    fullName: '%s',
                    referral: '%s',
                    phone: '%s',
                    englishLvl: '%s',
                    numOfCorrectAnswers: '%s',
                }
                """.formatted(username, fullName, referral, phone, englishLvl, numOfCorrectAnswers);
    }
}
