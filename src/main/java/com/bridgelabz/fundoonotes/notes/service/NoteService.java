package com.bridgelabz.fundoonotes.notes.service;

import java.util.List;

import com.bridgelabz.fundoonotes.exception.TokenException;
import com.bridgelabz.fundoonotes.exception.UserException;
import com.bridgelabz.fundoonotes.notes.dto.NoteDto;
import com.bridgelabz.fundoonotes.notes.model.Notes;
import com.bridgelabz.fundoonotes.response.Response;

public interface NoteService {
	
public Response createNote(NoteDto noteDto,String token);
public Response updateNote(Long noteId,NoteDto noteDto,String token)throws UserException;
public Response trashNote(Long noteId,String token)throws TokenException;
public List<Notes> getAllUserNotes(String token,boolean trash,boolean archive)throws UserException;
public Response isPing(Long noteId,String token)throws TokenException;
public Response archive(Long noteId,String token)throws TokenException;
//public List<Notes> getPingNotes(String token);
//public List<Notes> getArchive(String token);
public Response deleteNotes(Long noteId,String token);

}
 