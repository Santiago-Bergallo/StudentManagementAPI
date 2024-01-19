package studentSystem.studentSystem.Dto;

import lombok.Getter;
import lombok.Setter;

public class LoginBody {
    @Getter @Setter
    private String username;

    @Getter @Setter
    private String password;
}
