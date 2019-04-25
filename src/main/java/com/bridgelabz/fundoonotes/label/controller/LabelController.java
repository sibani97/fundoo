package com.bridgelabz.fundoonotes.label.controller;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoonotes.label.dto.LabelDto;
import com.bridgelabz.fundoonotes.label.service.LabelServiceImpl;
import com.bridgelabz.fundoonotes.labels.model.Labels;
import com.bridgelabz.fundoonotes.notes.controller.NoteController;
import com.bridgelabz.fundoonotes.notes.model.Notes;
import com.bridgelabz.fundoonotes.response.Response;

@RestController
public class LabelController {
	
	
	
	@Autowired
	LabelServiceImpl labelserviceImpl;
	
	
	
	Logger logger=LoggerFactory.getLogger(NoteController.class);
	
	@PostMapping("/user/label/create")
	public Response createLabel(@RequestParam String token,@RequestBody LabelDto labelDto)
	{
		logger.info("create label");
		return labelserviceImpl.createlabel(labelDto, token);
		
	}
	
	@PutMapping("/update/Label")
	public Response updateLabel(@RequestParam String token,@RequestBody Labels label)
	{
		logger.info("update label");
		return labelserviceImpl.updateLabel(token, label);
	}
	
	@DeleteMapping("/delete/user/label")
	public Response deleteUserLabel(@RequestParam String token,@RequestParam Long labelId)
	{
		logger.info("delete user label");
		return labelserviceImpl.deleteUserlabel(token, labelId);
    }
	
	@PostMapping("/add/label/note")
	public Response addLabelToNote(@RequestParam Long noteId,@RequestParam String token,@RequestParam String labelTitle)
	{
		logger.info("add Label To Note");
		return labelserviceImpl.addLabelToNote(noteId, token, labelTitle);
		
	}
	
	@GetMapping("/getall/user/label")
	public List<Labels> getAllUserLabel(@RequestParam String token)
	
	{
		logger.info("get all user label");
		return labelserviceImpl.getAllUserLabel(token);
	}
	
	@DeleteMapping("/delete/note/label")
	public Response deleteNoteLabel(@RequestParam String token, @RequestParam Long noteId,@RequestParam Long labelId)
	{
		logger.info("delete note label");
		return labelserviceImpl.deleteNoteLabel(token, noteId, labelId);
	}
	
	@GetMapping("/get/label/notes")
	public List<Notes> getLabelNotes(@RequestParam String token,@RequestParam Long labelId)
	{
		logger.info("get label note");
		return labelserviceImpl.getLabelNote(token, labelId);
	}
	
}
