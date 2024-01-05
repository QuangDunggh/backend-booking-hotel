package com.rim.myproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rim.myproject.model.BookedRoom;

@Repository
public interface IBookedRoomRepository extends JpaRepository<BookedRoom, Long> {

}
