package com.bridgelabz.fundoonotes.notes.controller;



import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoonotes.notes.dto.NoteDto;
import com.bridgelabz.fundoonotes.notes.model.Notes;
import com.bridgelabz.fundoonotes.notes.service.NoteServiceImpl;
import com.bridgelabz.fundoonotes.response.Response;
import com.bridgelabz.fundoonotes.response.ResponseToken;
@RestController
public class NoteController {
	@Autowired
	NoteServiceImpl noteServiceImpl;
	Logger logger=org.slf4j.LoggerFactory.getLogger(NoteController.class);
	
	@PostMapping(value="/note/created")
	public ResponseEntity<Response> createNote(HttpServletRequest request,@RequestBody NoteDto noteDto,@RequestParam String token)
	{
		logger.info("token---"+token);
	    logger.info((String)request.getAttribute("jwt_token"));
		System.out.println("title is"+noteDto.getTitle().length());
		Response response= noteServiceImpl.createNote(noteDto, token);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
		
		
	}
	@PutMapping(value="/note/update")
	public ResponseEntity<Response> updateNote(@RequestParam Long noteId,@RequestBody NoteDto noteDto,@RequestParam String token)
	{
		logger.info("note details"+noteDto.toString());
		logger.info("note id"+noteId.toString());
		logger.info("update");
		Response response=noteServiceImpl.updateNote(noteId, noteDto, token);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
			}
	
	@PutMapping(value="/note/trash")
	public ResponseEntity<Response>trashNote(@RequestParam Long noteId,@RequestParam String token)

	{
		logger.info("note noteId"+noteId);
		Response response=noteServiceImpl.trashNote(noteId, token);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
		
			}
	@PutMapping(value="/note/isPin")
	public ResponseEntity<Response>isPin(@RequestParam Long noteId,@RequestParam String token)
	{
		logger.info("note noteId"+noteId);
		Response response=noteServiceImpl.isPing(noteId,token);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	@PutMapping(value="/note/isArchive")
	public ResponseEntity<Response>isArchive(@RequestParam Long noteId,@RequestParam String token)
	{
		logger.info("note noteId"+noteId);
		Response response=noteServiceImpl.archive(noteId, token);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	@GetMapping(value="/user/getnote")
	public List<Notes> getNotes(@RequestParam String token,@RequestParam boolean trash,boolean archive)
	{
		logger.info("get all notes");
		List<Notes> note=noteServiceImpl.getAllUserNotes(token,trash,archive);
		return note;
	}
	
//	@GetMapping(value="/user/getPin")
//	public List<Notes> getisPinNotes(@RequestParam String token)
//	{
//		logger.info("get all pin notes");
//		List<Notes> note=noteServiceImpl.getPingNotes(token);
//		return note;
//	}
//	@GetMapping(value="/user/getarchive")
//	public List<Notes> getArchiveNotes(@RequestParam String token)
//	{
//		logger.info("get all archive notes");
//		List<Notes> note=noteServiceImpl.getArchive(token);
//		return note;
//	}
	@GetMapping(value="/user/deletenote")
	public ResponseEntity<Response> deleteNote(@RequestParam Long noteId,@RequestParam String token)
	{
		logger.info("note delete");
		Response response=noteServiceImpl.deleteNotes(noteId, token);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	
		}



