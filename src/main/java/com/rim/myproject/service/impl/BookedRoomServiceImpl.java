package com.rim.myproject.service.impl;

import org.springframework.stereotype.Service;

import com.rim.myproject.repository.IBookedRoomRepository;
import com.rim.myproject.service.IBookedRoomService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookedRoomServiceImpl implements IBookedRoomService{
	
	private final IBookedRoomRepository bookedRoomRepository;
	
	

}
