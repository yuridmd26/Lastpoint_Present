package br.com.present.events.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import br.com.present.commons.docs.IDocsMsgConsts;
import br.com.present.commons.exception.dto.ApiExceptionResponseDTO;
import br.com.present.events.model.EventRequestDTO;
import br.com.present.events.model.EventResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Event Controller", description = "Operations with event")
public interface IEventController {
	
	@Operation(summary = "Find event by id", description = "Find event by id")
	@ApiResponses(value = { 
		@ApiResponse(responseCode = "200", description = IDocsMsgConsts.Responses.MSG_200, content =
			@Content(mediaType = IDocsMsgConsts.MediaType.APP_JSON, schema = 
				@Schema(implementation = EventResponseDTO.class))),
		@ApiResponse(responseCode = "404", description = IDocsMsgConsts.Responses.MSG_404, content = 
			@Content(mediaType = IDocsMsgConsts.MediaType.APP_JSON, schema = 
				@Schema(implementation = ApiExceptionResponseDTO.class))),
	})
	ResponseEntity<EventResponseDTO> findById(Long id);
	
	@Operation(summary = "Create a event", description = "Create a event")
	@ApiResponses(value = { 
		@ApiResponse(responseCode = "201", description = IDocsMsgConsts.Responses.MSG_201, content =
			@Content(mediaType = IDocsMsgConsts.MediaType.APP_JSON, schema =
				@Schema(implementation = EventResponseDTO.class))),
		@ApiResponse(responseCode = "400", description = IDocsMsgConsts.Responses.MSG_400, content =
			@Content(mediaType = IDocsMsgConsts.MediaType.APP_JSON, schema =
				@Schema(implementation = ApiExceptionResponseDTO.class))),
		@ApiResponse(responseCode = "404", description = IDocsMsgConsts.Responses.MSG_404, content =
			@Content(mediaType = IDocsMsgConsts.MediaType.APP_JSON, schema =
				@Schema(implementation = ApiExceptionResponseDTO.class)))
	})
	ResponseEntity<EventResponseDTO> create(EventRequestDTO eventDto);
	
	@Operation(summary = "Update a event", description = "Update a event")
	@ApiResponses(value = { 
		@ApiResponse(responseCode = "200", description = IDocsMsgConsts.Responses.MSG_200, content = 
			@Content(mediaType = IDocsMsgConsts.MediaType.APP_JSON, schema = 
				@Schema(implementation = EventResponseDTO.class))),
		@ApiResponse(responseCode = "400", description = IDocsMsgConsts.Responses.MSG_400, content =
			@Content(mediaType = IDocsMsgConsts.MediaType.APP_JSON, schema =
				@Schema(implementation = ApiExceptionResponseDTO.class))),
		@ApiResponse(responseCode = "404", description = IDocsMsgConsts.Responses.MSG_404, content = 
			@Content(mediaType = IDocsMsgConsts.MediaType.APP_JSON, schema = 
				@Schema(implementation = ApiExceptionResponseDTO.class))),
	})
	ResponseEntity<EventResponseDTO> update(Long id, EventRequestDTO eventDto);

	@Operation(summary = "Delete a event", description = "Delete a event")
	@ApiResponses(value = { 
		@ApiResponse(responseCode = "204", description = IDocsMsgConsts.Responses.MSG_204),
		@ApiResponse(responseCode = "404", description = IDocsMsgConsts.Responses.MSG_404, content = 
			@Content(mediaType = IDocsMsgConsts.MediaType.APP_JSON, schema = 
				@Schema(implementation = ApiExceptionResponseDTO.class))),
	})
	ResponseEntity<Void> delete(Long id);
	
	@Operation(summary = "Find all event", description = "Find all event")
	@ApiResponses(value = { 
		@ApiResponse(responseCode = "200", description = IDocsMsgConsts.Responses.MSG_200, content =
			@Content(mediaType = IDocsMsgConsts.MediaType.APP_JSON, schema = 
				@Schema(implementation = EventResponseDTO.class)))
	})
	ResponseEntity<List<EventResponseDTO>> findAll();
	
}