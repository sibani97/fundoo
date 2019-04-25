package com.bridgelabz.fundoonotes.label.service;

import java.util.List;
import java.util.Set;
//import java.util.Set;
//import java.util.stream.Collector;
import java.util.stream.Collectors;

//import org.hibernate.cfg.Environment;
//import org.jboss.logging.NDC;
import org.modelmapper.ModelMapper;
//import org.modelmapper.internal.asm.tree.LabelNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoonotes.exception.LabelException;
import com.bridgelabz.fundoonotes.exception.NoteException;
import com.bridgelabz.fundoonotes.exception.UserException;
import com.bridgelabz.fundoonotes.label.dto.LabelDto;
import com.bridgelabz.fundoonotes.label.repository.LabelRepository;
import com.bridgelabz.fundoonotes.labels.model.Labels;
import com.bridgelabz.fundoonotes.notes.model.Notes;
import com.bridgelabz.fundoonotes.notes.repository.NoteRepository;
import com.bridgelabz.fundoonotes.response.Response;
import com.bridgelabz.fundoonotes.user.model.User;
import com.bridgelabz.fundoonotes.user.repository.UserRepository;
import com.bridgelabz.fundoonotes.util.StatusHelper;
import com.bridgelabz.fundoonotes.util.UserToken;

@Service
public class LabelServiceImpl implements LabelService {
	@Autowired
	private Response response;
	@Autowired
	private ModelMapper modelmapper;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private LabelRepository labelRepository;
	@Autowired
	private UserToken userToken;

	@Autowired
	org.springframework.core.env.Environment environment;
	@Autowired
	private NoteRepository noteRepository;
	@Override
	
	
	
	public Response createlabel(LabelDto labeldto, String token) {
		
		if(labeldto.getLabelName()==null)
		{
			throw new LabelException(-200,environment.getProperty("label.invalid"));
		}
		Labels label = modelmapper.map(labeldto, Labels.class);
		Long userid=userToken.tokenVerify(token);
		Labels duplicate=null;
		User user=userRepository.findByUserId(userid).orElseThrow(()->new UserException(-200,environment.getProperty("user.invalid.login")));
		user.getLable().add(label);
		userRepository.save(user);
		response=StatusHelper.statusResponseInfo(environment.getProperty("label.created"),400);
		return response;
	}

	@Override
	public Response updateLabel(String token, Labels updateLabel) {
	Long userId=userToken.tokenVerify(token);
	User user=userRepository.findByUserId(userId).orElseThrow(()->new UserException(500,environment.getProperty("user.not.valid")));
	Labels label=user.getLable().stream().filter(data-> data.getLabelId()==updateLabel.getLabelId()).findFirst().orElseThrow(()->new LabelException(-200,environment.getProperty("label.update")));
	label.setLabelName(updateLabel.getLabelName());
	labelRepository.save(label);
	Response response=StatusHelper.statusResponseInfo(environment.getProperty("label.updated.successful"),700);
	return response;
	
	}
	
	@Override
	public Response deleteUserlabel(String token, Long labelId) {
		Long userId=userToken.tokenVerify(token);
		System.out.println("user-->"+userId);
		User user=userRepository.findById(userId).orElseThrow(()->new UserException(500,environment.getProperty("user.not.valid")));
		Labels label=labelRepository.findByLabelId(labelId);
		
		if(label == null) {
			throw new LabelException(500,environment.getProperty("label.not.valid"));
		
		
	}else
	{
		System.out.println("3");
//	List<Notes> notes=user.getNotes().stream().filter(data->data.getLabel().removeIf(noteLabel->noteLabel.getLabelId()==(labelId))).collect(Collectors.toList());

//	user.getNotes().addAll(notes);
	
	user.getLable().removeIf(data->data.getLabelId()==(labelId));

//		labelRepository.delete(label);
	
	userRepository.save(user);

	
	
	Response response=StatusHelper.statusResponseInfo(environment.getProperty("delete.label"),600);
	return response;
	}
	
	}

	@Override
	public Response addLabelToNote(Long noteId, String token, String labelTitle) {
	Long userId=userToken.tokenVerify(token);
	//User user=userRepository.findByUserId(userId).orElseThrow(()->new UserException(400,environment.getProperty("invalid.label")));
	LabelDto labelDto=new LabelDto();
	//labelDto.setLableTitle(labelTitle);
	labelDto.setLabelName(labelTitle);
	User user=userRepository.findByUserId(userId).orElseThrow(()->new UserException(500,environment.getProperty("user.login.invalid")));
	
	Labels label=user.getLable().stream().filter(data->data.getLabelName().equals(labelDto.getLabelName())).findFirst().orElse(modelmapper.map(labelDto,Labels.class));
	//label not available in user
	if(user.getLable().stream().filter(data->data.getLabelName().equals(labelDto.getLabelName())).findFirst().isPresent()==false);
	{
		user.getLable().add(label);
	    userRepository.save(user);
	}
	label=user.getLable().stream().filter(data->data.getLabelName().equals(labelDto.getLabelName())).findFirst().get();
	System.out.println(label.toString());
	Notes note=user.getNotes().stream().filter(data->data.getNoteId().equals(noteId)).findFirst().orElseThrow(()->new NoteException(100,environment.getProperty("note.invalid")));
	System.out.println(note);
	if(note.getLabel().stream().filter(data->data.getLabelName().equals(labelTitle)).findFirst().isPresent()==false)
	{
		System.out.println(label.toString());
		label.getNotes().add(note);
		labelRepository.save(label);
		note.getLabel().add(label);
		Notes save = noteRepository.save(note);
		System.out.println(save);
		response=StatusHelper.statusResponseInfo(environment.getProperty("label.note.added"),100);
		return response;
	}
	else {
		response=StatusHelper.statusResponseInfo(environment.getProperty("label.note.isPresent"),300);
		return response;
	}
	}

	@Override
	public List<Labels> getAllUserLabel(String token) {
		Long userId=userToken.tokenVerify(token);
		User user=userRepository.findByUserId(userId).orElseThrow(()->new UserException(200,environment.getProperty("user.invalid.login")));
		List<Labels>label=user.getLable();
		
		return label;
	}

	@Override
	public Response deleteNoteLabel(String token, Long noteId, Long labelId) {
	Long userId=userToken.tokenVerify(token);
	User user=userRepository.findByUserId(userId).orElseThrow(()->new UserException(200,environment.getProperty("user.invalid.login")));
	Notes note=user.getNotes().stream().filter(data->data.getNoteId().equals(noteId)).findFirst().get();
	boolean status=note.getLabel().removeIf(data->data.getLabelId()==(labelId));
	System.out.println("status of delete:"+status);
	user.getNotes().add(note);
	userRepository.save(user);
	response=StatusHelper.statusResponseInfo(environment.getProperty("note.label.delete"),200);
	return response;
	}

	@Override
	public List<Notes> getLabelNote(String token, Long labelId) {
	Long userId=userToken.tokenVerify(token);
	User user=userRepository.findByUserId(userId).orElseThrow(()->new UserException(200,environment.getProperty("user.invalid")));
	//Label label=labelRepository.findByLabelId(labelId).get();
	Labels label=labelRepository.findById(labelId).get();
	List<Notes> note = label.getNotes().stream().collect(Collectors.toList());
	System.out.println("notes---"+note);
		return note;
	}

	

}
