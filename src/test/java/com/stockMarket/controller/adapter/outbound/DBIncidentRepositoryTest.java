package com.stockMarket.controller.adapter.outbound;

import com.stockMarket.controller.adapter.dto.ActionEntity;
import com.stockMarket.controller.adapter.dto.IncidentEntity;
import com.stockMarket.controller.domain.ActionName;
import com.stockMarket.controller.domain.CheckType;
import com.stockMarket.controller.domain.Incident;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DBIncidentRepositoryTest {

    @Mock
    private JpaIncidentRepository jpaIncidentRepository;

    @Mock
    private JpaActionRepository jpaActionRepository;

    @InjectMocks
    private DBIncidentRepository dbIncidentRepository;

    private final String userEmail = "test@example.com";

    @BeforeEach
    void setUp() {
        dbIncidentRepository = new DBIncidentRepository(jpaIncidentRepository, jpaActionRepository);
    }

    @Test
    void shouldAddNewIncidentToCheck() {
        Incident incident = mock(Incident.class);
        ActionEntity actionEntity = mock(ActionEntity.class);
        CheckType checkType = mock(CheckType.class);

        when(jpaActionRepository.findByActionName(anyString())).thenReturn(Optional.of(actionEntity));
        when(incident.action()).thenReturn(mock(ActionName.class));
        when(incident.action().name()).thenReturn(ActionName.BUY.name());

        when(incident.checkType()).thenReturn(checkType);
        when(checkType.getSymbol()).thenReturn("<=");

        dbIncidentRepository.addNewIncidentToCheck(userEmail, incident);

        verify(jpaIncidentRepository, times(1)).save(any(IncidentEntity.class));
    }


    @Test
    void shouldThrowExceptionWhenActionNotFound() {
        Incident incident = mock(Incident.class);
        ActionName action = mock(ActionName.class);

        when(incident.action()).thenReturn(action);
        when(action.name()).thenReturn(ActionName.BUY.name());

        when(jpaActionRepository.findByActionName(anyString())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> dbIncidentRepository.addNewIncidentToCheck(userEmail, incident));
    }


    @Test
    void shouldGetAllIncidentsByEmail() {
        IncidentEntity entity = mock(IncidentEntity.class);
        ActionEntity actionEntity = mock(ActionEntity.class);

        when(entity.getId()).thenReturn(1L);
        when(entity.getCustomerEmail()).thenReturn(userEmail);
        when(entity.getCompanyName()).thenReturn("TestCompany");
        when(entity.getPriceThreshold()).thenReturn(100.0F);
        when(entity.getActionAmount()).thenReturn(10);

        when(entity.getAction()).thenReturn(actionEntity);
        when(actionEntity.getActionName()).thenReturn(ActionName.BUY.name());

        when(entity.getCompereSing()).thenReturn(">");

        when(jpaIncidentRepository.findAll()).thenReturn(List.of(entity));

        List<Incident> incidents = dbIncidentRepository.getAllIncidentByEmail(userEmail);

        assertEquals(1, incidents.size());
    }



    @Test
    void shouldDeleteIncidentById() {
        Long incidentId = 1L;
        doNothing().when(jpaIncidentRepository).deleteById(incidentId);

        dbIncidentRepository.deleteIncidentById(incidentId);

        verify(jpaIncidentRepository, times(1)).deleteById(incidentId);
    }
}
