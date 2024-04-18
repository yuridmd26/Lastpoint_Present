package br.com.present.courses.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import br.com.present.commons.docs.IDocsMsgConsts;
import br.com.present.commons.exception.dto.ApiExceptionResponseDTO;
import br.com.present.courses.model.CourseRequestDTO;
import br.com.present.courses.model.CourseResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Course Controller", description = "Operations with course")
public interface ICourseController {

	@Operation(summary = "Find course by id", description = "Find course by id")
	@ApiResponses(value = { 
		@ApiResponse(responseCode = "200", description = IDocsMsgConsts.Responses.MSG_200, content =
			@Content(mediaType = IDocsMsgConsts.MediaType.APP_JSON, schema = 
				@Schema(implementation = CourseResponseDTO.class))),
		@ApiResponse(responseCode = "404", description = IDocsMsgConsts.Responses.MSG_404, content = 
			@Content(mediaType = IDocsMsgConsts.MediaType.APP_JSON, schema = 
				@Schema(implementation = ApiExceptionResponseDTO.class))),
	})
	ResponseEntity<CourseResponseDTO> findById(Long id);
	
	@Operation(summary = "Create a course", description = "Create a course")
	@ApiResponses(value = { 
		@ApiResponse(responseCode = "201", description = IDocsMsgConsts.Responses.MSG_201, content =
			@Content(mediaType = IDocsMsgConsts.MediaType.APP_JSON, schema =
				@Schema(implementation = CourseResponseDTO.class))),
		@ApiResponse(responseCode = "400", description = IDocsMsgConsts.Responses.MSG_400, content =
			@Content(mediaType = IDocsMsgConsts.MediaType.APP_JSON, schema =
				@Schema(implementation = ApiExceptionResponseDTO.class))),
	})
	ResponseEntity<CourseResponseDTO> create(CourseRequestDTO courseDto);
	
	@Operation(summary = "Update a course", description = "Update a course")
	@ApiResponses(value = { 
		@ApiResponse(responseCode = "200", description = IDocsMsgConsts.Responses.MSG_200, content = 
			@Content(mediaType = IDocsMsgConsts.MediaType.APP_JSON, schema = 
				@Schema(implementation = CourseResponseDTO.class))),
		@ApiResponse(responseCode = "400", description = IDocsMsgConsts.Responses.MSG_400, content =
			@Content(mediaType = IDocsMsgConsts.MediaType.APP_JSON, schema =
				@Schema(implementation = ApiExceptionResponseDTO.class))),
		@ApiResponse(responseCode = "404", description = IDocsMsgConsts.Responses.MSG_404, content = 
			@Content(mediaType = IDocsMsgConsts.MediaType.APP_JSON, schema = 
				@Schema(implementation = ApiExceptionResponseDTO.class))),
	})
	ResponseEntity<CourseResponseDTO> update(Long id, CourseRequestDTO courseDto);
	
	@Operation(summary = "Delete a course", description = "Delete a course")
	@ApiResponses(value = { 
		@ApiResponse(responseCode = "204", description = IDocsMsgConsts.Responses.MSG_204),
		@ApiResponse(responseCode = "404", description = IDocsMsgConsts.Responses.MSG_404, content = 
			@Content(mediaType = IDocsMsgConsts.MediaType.APP_JSON, schema = 
				@Schema(implementation = ApiExceptionResponseDTO.class))),
	})
	ResponseEntity<Void> delete(Long id);
	
	@Operation(summary = "Find all courses", description = "Find all courses")
	@ApiResponses(value = { 
		@ApiResponse(responseCode = "200", description = IDocsMsgConsts.Responses.MSG_200, content = 
			@Content(mediaType = IDocsMsgConsts.MediaType.APP_JSON, schema = 
				@Schema(implementation = CourseResponseDTO.class)))
	})
	ResponseEntity<List<CourseResponseDTO>> findAll();

}
