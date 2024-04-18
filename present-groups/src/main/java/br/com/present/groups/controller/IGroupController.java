package br.com.present.groups.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import br.com.present.commons.docs.IDocsMsgConsts;
import br.com.present.commons.exception.dto.ApiExceptionResponseDTO;
import br.com.present.groups.model.GroupRequestDTO;
import br.com.present.groups.model.GroupResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Group Controller", description = "Operations with group")
public interface IGroupController {
	
	@Operation(summary = "Find group by id", description = "Find group by id")
	@ApiResponses(value = { 
		@ApiResponse(responseCode = "200", description = IDocsMsgConsts.Responses.MSG_200, content =
			@Content(mediaType = IDocsMsgConsts.MediaType.APP_JSON, schema = 
				@Schema(implementation = GroupResponseDTO.class))),
		@ApiResponse(responseCode = "404", description = IDocsMsgConsts.Responses.MSG_404, content = 
			@Content(mediaType = IDocsMsgConsts.MediaType.APP_JSON, schema = 
				@Schema(implementation = ApiExceptionResponseDTO.class))),
	})
	ResponseEntity<GroupResponseDTO> findById(Long id);
	
	@Operation(summary = "Create a group", description = "Create a group")
	@ApiResponses(value = { 
		@ApiResponse(responseCode = "201", description = IDocsMsgConsts.Responses.MSG_201, content =
			@Content(mediaType = IDocsMsgConsts.MediaType.APP_JSON, schema =
				@Schema(implementation = GroupResponseDTO.class))),
		@ApiResponse(responseCode = "400", description = IDocsMsgConsts.Responses.MSG_400, content =
			@Content(mediaType = IDocsMsgConsts.MediaType.APP_JSON, schema =
				@Schema(implementation = ApiExceptionResponseDTO.class))),
	})
	ResponseEntity<GroupResponseDTO> create(GroupRequestDTO groupDto);

	@Operation(summary = "Update a group", description = "Update a group")
	@ApiResponses(value = { 
		@ApiResponse(responseCode = "200", description = IDocsMsgConsts.Responses.MSG_200, content = 
			@Content(mediaType = IDocsMsgConsts.MediaType.APP_JSON, schema = 
				@Schema(implementation = GroupResponseDTO.class))),
		@ApiResponse(responseCode = "400", description = IDocsMsgConsts.Responses.MSG_400, content =
			@Content(mediaType = IDocsMsgConsts.MediaType.APP_JSON, schema =
				@Schema(implementation = ApiExceptionResponseDTO.class))),
		@ApiResponse(responseCode = "404", description = IDocsMsgConsts.Responses.MSG_404, content = 
			@Content(mediaType = IDocsMsgConsts.MediaType.APP_JSON, schema = 
				@Schema(implementation = ApiExceptionResponseDTO.class))),
	})
	ResponseEntity<GroupResponseDTO> update(Long id, GroupRequestDTO groupDto);

	@Operation(summary = "Delete a group", description = "Delete a group")
	@ApiResponses(value = { 
		@ApiResponse(responseCode = "204", description = IDocsMsgConsts.Responses.MSG_204),
		@ApiResponse(responseCode = "404", description = IDocsMsgConsts.Responses.MSG_404, content = 
			@Content(mediaType = IDocsMsgConsts.MediaType.APP_JSON, schema = 
				@Schema(implementation = ApiExceptionResponseDTO.class))),
	})
	ResponseEntity<Void> delete(Long id);
	
	@Operation(summary = "Find all groups", description = "Find all groups")
	@ApiResponses(value = { 
		@ApiResponse(responseCode = "200", description = IDocsMsgConsts.Responses.MSG_200, content = 
			@Content(mediaType = IDocsMsgConsts.MediaType.APP_JSON, schema = 
				@Schema(implementation = GroupResponseDTO.class))),
	})
	ResponseEntity<List<GroupResponseDTO>> findAll();
	
}