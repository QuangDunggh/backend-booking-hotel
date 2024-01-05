package com.rim.myproject.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rim.myproject.response.RoomResponse;
import com.rim.myproject.service.IRoomService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/rooms")
@RequiredArgsConstructor
@CrossOrigin
public class RoomController {
	
	private final IRoomService roomService;

	@PostMapping
	public ResponseEntity<RoomResponse> addNewRoom(@RequestParam MultipartFile photo, @RequestParam String roomType,
			@RequestParam BigDecimal roomPrice) throws SQLException, IOException {
		return ResponseEntity.ok(roomService.addNewRoom(photo, roomType, roomPrice));
	}

}
