package com.core.timmy.serviceImplTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.core.timmy.data.model.Status;
import com.core.timmy.data.repository.IStatusRepository;
import com.core.timmy.serviceImpl.StatusServiceImpl;

import lombok.extern.java.Log;


@Log
@ExtendWith(MockitoExtension.class)
class StatusServiceImplTest {
	
	@Mock
	private IStatusRepository statusRepository;
	
	@InjectMocks
	private StatusServiceImpl statusService;
	
	private Status status;
	private List<Status> statusList;
	
	@BeforeEach
	public void setup(){
		status = new Status (1L, "INICIADO");
		statusList = List.of(
				new Status (1L, "INICIADO"),
				new Status (2L, "RECHAZADO"),
				new Status (3L, "ENVIADO"),
				new Status (4L, "ACEPTADO")
				);
				
	}

	/*@Test
	void testSave() {
		fail("Not yet implemented");
	}*/

	@Test
	void testFindById() {
		
		//precondition: Hay que establecer que es lo que queremos que se ejecute y se devuelva. 
		//cuando pase esto, vamos a falsearlo y retorna esto. 
		when(statusRepository.findById(1L)).thenReturn(Optional.of(this.status));
		
		
		// action:
		Status existingStatus = statusService.findById(status.getId()).get();
		
		// verify:
		log.info(existingStatus.toString());
		assertNotNull(existingStatus, ()-> "must be not null");
		assertTrue(existingStatus.getId() == 1L
				&& existingStatus.getStatusName().equals("INICIADO"),
				() -> "must be Status(1, 'INICIADO')");
		
	}

	@Test
	void testFindall() {
		
		//precondition:
		Status status4=new Status(4L, "ACEPTADO");
		when(statusRepository.findById(4L)).thenReturn(Optional.of(status4));
		List<Status>existingStatusList = new ArrayList<Status>();
		when(statusRepository.findAll()).thenReturn(statusList);
		
		//action:
		Status existingStatus= statusService.findById(status4.getId()).get();
		existingStatusList = statusService.findall();
		
		//verify:
		log.info(existingStatus.toString());
		assertNotNull(existingStatus, ()->"status(4L) must be not dull");
		assertTrue(existingStatus.getId()== 4L
				&& existingStatus.getStatusName().equals("ACEPTADO"),
				()->"must be Status(4,'ACEPTADO')");
		
		//
		log.info(existingStatus.toString());
		assertNotNull(existingStatusList, ()->"existingStatusList must be not null");
		assertEquals(4,existingStatusList.size(), ()->"must have 4 items");
		
		
	}

}
