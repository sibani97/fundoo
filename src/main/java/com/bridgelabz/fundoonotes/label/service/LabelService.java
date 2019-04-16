package com.bridgelabz.fundoonotes.label.service;

import java.util.List;
import java.util.Set;

//import org.modelmapper.internal.asm.tree.LabelNode;

import com.bridgelabz.fundoonotes.label.dto.LabelDto;
import com.bridgelabz.fundoonotes.labels.model.Labels;
import com.bridgelabz.fundoonotes.notes.model.Notes;
import com.bridgelabz.fundoonotes.response.Response;


public interface LabelService {
	public Response createlabel(LabelDto labeldto,String token);
	public Response updateLabel(String token,Labels updateLabel);
	public Response deleteUserlabel(String token,Long labelId);
	public Response addLabelToNote(Long noteId,String token,String labelTitle );
	public List<Labels> getAllUserLabel(String token);
	public Response deleteNoteLabel(String token,Long noteId,Long labelId);
	public List<Notes> getLabelNote(String token,Long labelId);
}
