package co.edu.javeriana.as.personapp.model.response;

import java.time.LocalDate;

import co.edu.javeriana.as.personapp.model.request.StudyRequest;

public class StudyResponse extends StudyRequest{
    
    private String status;
    
    public StudyResponse(String idProfession, String idCcPerson, LocalDate graduationDate, String university, String database, String status) {
        super(idProfession, idCcPerson, graduationDate, university, database);
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
