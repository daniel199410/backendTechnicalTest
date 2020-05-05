package co.com.dcatano.Patient.service;

import co.com.dcatano.Patient.exception.PatientException;
import co.com.dcatano.Patient.model.Patient;
import co.com.dcatano.Patient.repository.PatientRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private PatientRepository patientRepository;

    public Patient save(Patient patient) throws PatientException {
        try {
            return patientRepository.save(patient);
        } catch (Exception e) {
            if(e.getCause() instanceof DataException) {
                throw new PatientException(messageSource.getMessage("INCORRECT_DATA", null, LocaleContextHolder.getLocale()));
            }
            if(e.getCause() instanceof ConstraintViolationException) {
                throw new PatientException(messageSource.getMessage("USER_EXISTS", null, LocaleContextHolder.getLocale()));
            }
            throw new PatientException(messageSource.getMessage("GENERAL_ERROR", null, LocaleContextHolder.getLocale()));
        }
    }

}
