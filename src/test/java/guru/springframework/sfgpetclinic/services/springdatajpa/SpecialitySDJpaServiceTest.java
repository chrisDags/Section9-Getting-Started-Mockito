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
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTest {

    @Mock
    SpecialtyRepository specialtyRepository;

    @InjectMocks
    SpecialitySDJpaService service;



    @Test
    void testDeleteByObject() {

        // given
        Speciality speciality = new Speciality();

        //when
        service.delete(speciality);

        //then
        then(specialtyRepository).should().delete(any(Speciality.class));
    }

//    @Test
//    void findByIdTest(){
//        // object that the mock is going to return back
//        Speciality speciality = new Speciality();
//        // when the specialRepository.findById(1L) call happens, then return an optional of the speciality object above
//        when(specialtyRepository.findById(1L)).thenReturn(Optional.of(speciality));
//        // making the method call on the class that is being tested
//        Speciality foundSpeciality = service.findById(1L);
//        // ensure that we are getting a speciality object back
//        assertThat(foundSpeciality).isNotNull();
//        // verify that the findById() method was called 1 time
//        verify(specialtyRepository).findById(1L);
//    }

    // with BDD given
    @Test
    void findByIdBDDTest() {

        // given
        Speciality speciality = new Speciality();
        given(specialtyRepository.findById(1L)).willReturn(Optional.of(speciality));

        // when
        Speciality foundSpeciality = service.findById(1L);

        //then
        assertThat(foundSpeciality).isNotNull();
        // makes sure that the method was called once for any Long
        then(specialtyRepository).should().findById(anyLong());
        // makes sure that the specialityRepository has no further interactions
        then(specialtyRepository).shouldHaveNoMoreInteractions();


    }

    @Test
    void deleteById() {

        // given - none

        //when
        service.deleteById(1l);
        service.deleteById(1l);
        //then
        then(specialtyRepository).should(times((2))).deleteById(1L);
//        verify(specialtyRepository, times(2)).deleteById(1l);
    }

    @Test
    void deleteByIdAtLeast() {

        //given - none
        //when
        service.deleteById(1l);
        //then
        then(specialtyRepository).should(atLeastOnce()).deleteById(1L);
//        verify(specialtyRepository, atLeastOnce()).deleteById(1l);
    }

    @Test
    void deleteByIdAtMost() {

        //when
        service.deleteById(1l);

        //then
        then(specialtyRepository).should(atMost(1)).deleteById(1L);
//        verify(specialtyRepository, atMost(1)).deleteById(1l);
    }

    @Test
    void deleteByIdNever() {

        //when
        service.deleteById(1l);
        service.deleteById(1l);

        //then
        then(specialtyRepository).should(atLeastOnce()).deleteById(1L);
//        verify(specialtyRepository, atLeastOnce()).deleteById(1l);
        //verify that it is never called with 5L
        then(specialtyRepository).should(never()).deleteById(5L);
//        verify(specialtyRepository, never()).deleteById(5l);
    }


    @Test
    void testDelete() {
        //when
        service.delete(new Speciality());

        //then
        then(specialtyRepository).should().delete(any());

    }
}