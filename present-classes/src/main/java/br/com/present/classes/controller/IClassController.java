package br.com.present.classes.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import br.com.present.classes.model.ClassRequestDTO;
import br.com.present.classes.model.ClassResponseDTO;
import br.com.present.classes.model.ClassesNowAndFutureResponseDTO;
import br.com.present.commons.docs.IDocsMsgConsts;
import br.com.present.commons.exception.dto.ApiExceptionResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Class Controller", description = "Operations with class")
public interface IClassController {

	@Operation(summary = "Find class by id", description = "Find class by id")
	@ApiResponses(value = { 
		@ApiResponse(responseCode = "200", description = IDocsMsgConsts.Responses.MSG_200, content =
			@Content(mediaType = IDocsMsgConsts.MediaType.APP_JSON, schema = 
				@Schema(implementation = ClassResponseDTO.class))),
		@ApiResponse(responseCode = "404", description = IDocsMsgConsts.Responses.MSG_404, content = 
			@Content(mediaType = IDocsMsgConsts.MediaType.APP_JSON, schema = 
				@Schema(implementation = ApiExceptionResponseDTO.class))),
	})
	ResponseEntity<ClassResponseDTO> findById(Long id);
	
	@Operation(summary = "Create a class", description = "Create a class")
	@ApiResponses(value = { 
		@ApiResponse(responseCode = "201", description = IDocsMsgConsts.Responses.MSG_201, content =
			@Content(mediaType = IDocsMsgConsts.MediaType.APP_JSON, schema =
				@Schema(implementation = ClassResponseDTO.class))),
		@ApiResponse(responseCode = "400", description = IDocsMsgConsts.Responses.MSG_400, content =
			@Content(mediaType = IDocsMsgConsts.MediaType.APP_JSON, schema =
				@Schema(implementation = ApiExceptionResponseDTO.class))),
		@ApiResponse(responseCode = "404", description = IDocsMsgConsts.Responses.MSG_404, content =
			@Content(mediaType = IDocsMsgConsts.MediaType.APP_JSON, schema =
				@Schema(implementation = ApiExceptionResponseDTO.class)))
	})
	ResponseEntity<ClassResponseDTO> create(ClassRequestDTO classDto);
	
	@Operation(summary = "Update a class", description = "Update a class")
	@ApiResponses(value = { 
		@ApiResponse(responseCode = "200", description = IDocsMsgConsts.Responses.MSG_200, content = 
			@Content(mediaType = IDocsMsgConsts.MediaType.APP_JSON, schema = 
				@Schema(implementation = ClassResponseDTO.class))),
		@ApiResponse(responseCode = "400", description = IDocsMsgConsts.Responses.MSG_400, content =
			@Content(mediaType = IDocsMsgConsts.MediaType.APP_JSON, schema =
				@Schema(implementation = ApiExceptionResponseDTO.class))),
		@ApiResponse(responseCode = "404", description = IDocsMsgConsts.Responses.MSG_404, content = 
			@Content(mediaType = IDocsMsgConsts.MediaType.APP_JSON, schema = 
				@Schema(implementation = ApiExceptionResponseDTO.class)))
	})
	ResponseEntity<ClassResponseDTO> update(Long id, ClassRequestDTO classDto);
	
	@Operation(summary = "Delete a class", description = "Delete a class")
	@ApiResponses(value = { 
		@ApiResponse(responseCode = "204", description = IDocsMsgConsts.Responses.MSG_204),
		@ApiResponse(responseCode = "404", description = IDocsMsgConsts.Responses.MSG_404, content = 
			@Content(mediaType = IDocsMsgConsts.MediaType.APP_JSON, schema = 
				@Schema(implementation = ApiExceptionResponseDTO.class)))
	})
	ResponseEntity<Void> delete(Long id);
	
	@Operation(summary = "Find all classes", description = "Find all classes")
	@ApiResponses(value = { 
		@ApiResponse(responseCode = "200", description = IDocsMsgConsts.Responses.MSG_200, content = 
			@Content(mediaType = IDocsMsgConsts.MediaType.APP_JSON, schema = 
				@Schema(implementation = ClassResponseDTO.class)))
	})
	ResponseEntity<List<ClassResponseDTO>> findAll();
	
	@Operation(summary = "Find classes for type by user id", description = "Find classes for type by user id")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = IDocsMsgConsts.Responses.MSG_200, content = 
            @Content(mediaType = IDocsMsgConsts.MediaType.APP_JSON, schema = 
                @Schema(implementation = ClassesNowAndFutureResponseDTO.class))),
		@ApiResponse(responseCode = "400", description = IDocsMsgConsts.Responses.MSG_400, content =
			@Content(mediaType = IDocsMsgConsts.MediaType.APP_JSON, schema =
				@Schema(implementation = ApiExceptionResponseDTO.class))),
		@ApiResponse(responseCode = "404", description = IDocsMsgConsts.Responses.MSG_404, content =
			@Content(mediaType = IDocsMsgConsts.MediaType.APP_JSON, schema =
				@Schema(implementation = ApiExceptionResponseDTO.class)))
    })
    ResponseEntity<List<ClassesNowAndFutureResponseDTO>> findClassesUserForTypeByUserId(Long id);
	
}
