package studentSystem.studentSystem.Dto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import studentSystem.studentSystem.model.Course;
import studentSystem.studentSystem.model.StudentAddress;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class RegistrationBody {

    @Getter @Setter
    private String name;

    @Getter @Setter
    private String surname;

    @NotNull @NotBlank
    private String email;

    @Getter @Setter
    private List<StudentAddressRegistrationBody> studentAddresses = new ArrayList<>();

}
