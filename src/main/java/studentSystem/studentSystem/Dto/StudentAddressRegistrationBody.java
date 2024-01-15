package studentSystem.studentSystem.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentAddressRegistrationBody {

    @Getter @Setter
    @Column(name = "street", nullable = false)
    private String street;

    @Getter @Setter
    @Column(name = "number", nullable = false)
    private String number;

    @Getter @Setter
    private RegistrationBody registrationBody;

}
