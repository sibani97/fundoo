package com.bridgelabz.fundoonotes.notes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.fundoonotes.notes.model.Notes;

public interface NoteRepository extends JpaRepository<Notes,Long>  {
public Notes findByNoteId(long noteId);
//public Notes findByUserId(long userId);

}
