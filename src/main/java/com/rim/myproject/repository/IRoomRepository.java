package com.rim.myproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rim.myproject.model.Room;

@Repository
public interface IRoomRepository extends JpaRepository<Room, Long>{

}
