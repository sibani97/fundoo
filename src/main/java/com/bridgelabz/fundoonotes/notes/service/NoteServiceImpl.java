package com.bridgelabz.fundoonotes.notes.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.xml.crypto.Data;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonotes.exception.NoteException;
import com.bridgelabz.fundoonotes.exception.TokenException;
import com.bridgelabz.fundoonotes.exception.UserException;
import com.bridgelabz.fundoonotes.notes.controller.NoteController;
import com.bridgelabz.fundoonotes.notes.dto.NoteDto;
import com.bridgelabz.fundoonotes.notes.model.Notes;
import com.bridgelabz.fundoonotes.notes.repository.NoteRepository;
import com.bridgelabz.fundoonotes.response.Response;
import com.bridgelabz.fundoonotes.response.ResponseToken;
import com.bridgelabz.fundoonotes.user.model.User;
import com.bridgelabz.fundoonotes.user.repository.UserRepository;
import com.bridgelabz.fundoonotes.user.service.MymailService;
import com.bridgelabz.fundoonotes.util.StatusHelper;
import com.bridgelabz.fundoonotes.util.UserToken;
@Service
@PropertySource("classpath:fundoo.properties")
public class NoteServiceImpl implements NoteService{
	@Autowired
	private NoteRepository noteRepository;
	@Autowired
	private Environment environment;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ResponseToken responseStatus;
	
	@Autowired
	private MymailService myMail;
	@Autowired
	private UserToken userToken;
	
	@Autowired
	private Response response;
	StatusHelper status=new StatusHelper();
	
	Logger logger=org.slf4j.LoggerFactory.getLogger(NoteController.class);
	@Override
	public Response createNote(NoteDto noteDto, String token) {
	
	if(noteDto.getTitle().length()==0 && noteDto.getDescription().length()==0) {
		throw new NoteException(-200,environment.getProperty("note.error"));
	}
	else {
		
		Notes notes=modelMapper.map(noteDto, Notes.class);
		Long userid=userToken.tokenVerify(token);
		
		User user=userRepository.findByUserId(userid).orElseThrow(()->new UserException(-200,environment.getProperty("user.invalid.login")));
		
		notes.setCreateDate(LocalDate.now());
		user.getNotes().add(notes);
		userRepository.save(user);
		response=StatusHelper.statusResponseInfo(environment.getProperty("note.created"), 200);
		return response;
	}
	
	}
	@Override
	public Response updateNote(Long noteId, NoteDto noteDto, String token) throws UserException {
		System.out.println("Update the note");
		
		Long userId=userToken.tokenVerify(token);
		User user=userRepository.findByUserId(userId).orElseThrow(()->new UserException(environment.getProperty("note.not.used")));
		List<Notes> note1=user.getNotes();
		Notes note =  note1.stream()
				.filter(data-> data.getNoteId()
						.equals(noteId)).findAny()
				.orElseThrow(()->new UserException(environment.getProperty("Note.user")));
		note.setTitle(noteDto.getTitle());
		note.setDescription(noteDto.getDescription());
		note.setLastUpdateDate(LocalDate.now());
		note1.add(note);
		user.setNotes(note1);
		userRepository.save(user);
		response=StatusHelper.statusResponseInfo(environment.getProperty("Note.updated"), 200);
		return response;
	}
	@Override
	public Response trashNote(Long noteId, String token) throws TokenException {
		logger.info("delete note");
		Long userId=userToken.tokenVerify(token);
		User user=userRepository.findByUserId(userId).orElseThrow(()->new UserException(environment.getProperty("note.userNote")));
		Notes note = user.getNotes().stream().filter(data-> 
		data.getNoteId().equals(noteId)).findFirst()
				.orElseThrow(()->(new UserException(environment.getProperty("note.invalid"))));
		boolean noteStatus=note.isTrash();
		if(noteStatus==true)
		{
			note.setTrash(false);
			
			noteRepository.save(note);
			response=StatusHelper.statusResponseInfo(environment.getProperty("Note.untrash"), 100);
			return response;
		}else
		
		{
			note.setTrash(true);
			noteRepository.save(note);
			response=StatusHelper.statusResponseInfo(environment.getProperty("note.trash"),300);
			return response;
		}
		
		
	}
	
	@Override
	public List<Notes> getAllUserNotes(String token, boolean trash, boolean archive) throws UserException {
		Long userId=userToken.tokenVerify(token);
		User user=userRepository.findByUserId(userId).orElseThrow(()->new UserException(environment.getProperty("user.getNotes"))); 
		List<Notes> userNote=user.getNotes().stream().filter(data->(data.isTrash()==trash && data.isArchive()==archive)).collect(Collectors.toList());
		return userNote;
	}
	
	
	@Override
	public Response isPing(Long noteId, String token) throws TokenException {
		logger.info("note isPing");
		Long userId=userToken.tokenVerify(token);
		User user=userRepository.findByUserId(userId).orElseThrow(()->new UserException(environment.getProperty("note.isPing")));
		Notes note=user.getNotes().stream().filter(data->data.getNoteId().equals(noteId))
				.findFirst().orElseThrow(()->new UserException(environment.getProperty("note.invalid")));
		boolean noteStatus=note.isPin();
		if(noteStatus==true)
		{
			note.setPin(false);
			noteRepository.save(note);
			response=StatusHelper.statusResponseInfo(environment.getProperty("Note.notPin"),400);
			return response;
		}else
		{
			note.setPin(true);
			noteRepository.save(note);
			response=StatusHelper.statusResponseInfo(environment.getProperty("note.pin"),500);
			return response;
		}
		
		
	}
	@Override
	public Response archive(Long noteId, String token) throws TokenException {
		logger.info("note archive");
		Long userId=userToken.tokenVerify(token);
		User user=userRepository.findByUserId(userId).orElseThrow(()->new UserException(environment.getProperty("note.archive")));
		Notes note=user.getNotes().stream().filter(data->data.getNoteId().equals(noteId))
				.findFirst().orElseThrow(()->new UserException(environment.getProperty("note.invalid")));
		boolean noteStatus=note.isArchive();
		if(noteStatus==true)
		{
			note.setArchive(false);
			noteRepository.save(note);
			response=StatusHelper.statusResponseInfo(environment.getProperty("note.notin.archive"),505);
			return response;
		}
		else
		{
			note.setArchive(true);
			noteRepository.save(note);
			response=StatusHelper.statusResponseInfo(environment.getProperty("note.in.archive"),507);
			return response;
		}
	}
//	@Override
//	public List<Notes> getPingNotes(String token) {
//		
//		Long userId=userToken.tokenVerify(token);
//		User user=userRepository.findByUserId(userId).orElseThrow(()->new UserException(environment.getProperty("note.getpinNote")));
//		List<Notes> userNotes=(List<Notes>)user.getNotes().stream().filter(data->data.isPin()==true);
//		System.out.println(userNotes);
//		return userNotes;
//		
//			}
//	@Override
//	public List<Notes> getArchive(String token) {
//		Long userId=userToken.tokenVerify(token);
//		User user=userRepository.findByUserId(userId).orElseThrow(()->new UserException(environment.getProperty("note.getarchive")));
//		List<Notes> userNotes=(List<Notes>)user.getNotes().stream().filter(data->data.isArchive()==true);
//		System.out.println(userNotes);
//		
//		return userNotes;
//	}
	
	@Override
	public Response deleteNotes(Long noteId, String token) {
		Long userId=userToken.tokenVerify(token);
		Optional<User> user=userRepository.findByUserId(userId);
		if(user.isPresent())
		{
			Optional<Notes> notes = noteRepository.findById(noteId);
			System.out.println(notes.get().toString());
			if(notes.get().isTrash())
			{
				noteRepository.delete(notes.get());
			}
		}
		
		response=StatusHelper.statusResponseInfo(environment.getProperty("note.delete"),600);
		return response;
	}
	
	
	
	

}
