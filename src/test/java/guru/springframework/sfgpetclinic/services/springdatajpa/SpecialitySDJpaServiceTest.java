package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.SpecialtyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTest {

    @Mock
    SpecialtyRepository specialtyRepository;

    @InjectMocks
    SpecialitySDJpaService service;

    @Test
    void testDeleteByObject() {
        Speciality speciality = new Speciality();

        service.delete(speciality);

        verify(specialtyRepository).delete(any(Speciality.class));

    }

    @Test
    void findByIdTest(){
        // object that the mock is going to return back
        Speciality speciality = new Speciality();
        // when the specialRepository.findById(1L) call happens, then return an optional of the speciality object above
        when(specialtyRepository.findById(1L)).thenReturn(Optional.of(speciality));
        // making the method call on the class that is being tested
        Speciality foundSpeciality = service.findById(1L);
        // ensure that we are getting a speciality object back
        assertThat(foundSpeciality).isNotNull();
        // verify that the findById() method was called 1 time
        verify(specialtyRepository).findById(1L);
    }

    @Test
    void deleteById() {
        service.deleteById(1l);
        service.deleteById(1l);
        verify(specialtyRepository, times(2)).deleteById(1l);
    }

    @Test
    void deleteByIdAtLeast() {
        service.deleteById(1l);
        verify(specialtyRepository, atLeastOnce()).deleteById(1l);
    }

    @Test
    void deleteByIdAtMost() {
        service.deleteById(1l);
        verify(specialtyRepository, atMost(1)).deleteById(1l);
    }

    @Test
    void deleteByIdNever() {
        service.deleteById(1l);
        service.deleteById(1l);

        verify(specialtyRepository, atLeastOnce()).deleteById(1l);
        //verify that it is never called with 5L
        verify(specialtyRepository, never()).deleteById(5l);
    }



    @Test
    void testDelete() {
        service.delete(new Speciality());
    }
}