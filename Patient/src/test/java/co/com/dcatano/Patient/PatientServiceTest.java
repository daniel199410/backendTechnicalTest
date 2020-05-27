package co.com.dcatano.Patient;

import co.com.dcatano.Patient.exception.PatientException;
import co.com.dcatano.Patient.model.Patient;
import co.com.dcatano.Patient.repository.PatientRepository;
import co.com.dcatano.Patient.service.PatientService;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.DataException;
import org.hibernate.exception.GenericJDBCException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class PatientServiceTest {

    @Autowired
    private MessageSource realMessageSource;

    @Mock
    private MessageSource messageSource;

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientService patientService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldSaveUser() throws PatientException {
        Patient patient = new Patient();
        patient.setPatientId(1L);
        Mockito.when(patientRepository.save(Mockito.any(Patient.class))).thenReturn(patient);
        assertEquals(new Long(1), patientService.save(new Patient()).getPatientId());
    }

    @Test
    public void shouldCatchDataException() {
        DataException dataException = new DataException("", new SQLException());
        Exception dataIntegrityViolationException = new DataIntegrityViolationException("s", dataException);
        Mockito.doThrow(dataIntegrityViolationException).when(patientRepository).save(Mockito.any(Patient.class));
        try {
            patientService.save(new Patient());
        } catch (PatientException e) {
            assertEquals(messageSource.getMessage("INCORRECT_DATA", null, LocaleContextHolder.getLocale()), e.getMessage());
        }
    }

    @Test
    public void shouldCatchConstraintViolationException() {
        ConstraintViolationException constraintViolationException = new ConstraintViolationException("", new SQLException(), "");
        Exception dataIntegrityViolationException = new DataIntegrityViolationException("s", constraintViolationException);
        Mockito.doThrow(dataIntegrityViolationException).when(patientRepository).save(Mockito.any(Patient.class));
        try {
            patientService.save(new Patient());
        } catch (PatientException e) {
            assertEquals(messageSource.getMessage("USER_EXISTS", null, LocaleContextHolder.getLocale()), e.getMessage());
        }
    }

    @Test
    public void shouldCatchGeneralException() {
        Exception dataIntegrityViolationException = new GenericJDBCException("s", new SQLException());
        Mockito.doThrow(dataIntegrityViolationException).when(patientRepository).save(Mockito.any(Patient.class));
        try {
            patientService.save(new Patient());
        } catch (PatientException e) {
            assertEquals("Ocurrió un error", e.getMessage());
        }
    }
}
