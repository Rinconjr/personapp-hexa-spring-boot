package co.edu.javeriana.as.personapp.model.request;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudyRequest {
    private String idProfession;
    private String idCcPerson;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate graduationDate;
    private String university;
    private String database;
    
}
