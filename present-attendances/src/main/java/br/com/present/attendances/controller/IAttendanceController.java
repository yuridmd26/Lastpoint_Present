package br.com.present.attendances.controller;

import java.util.List;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.present.attendances.model.AttendanceRequestDTO;
import br.com.present.attendances.model.AttendanceResponseDTO;
import br.com.present.attendances.model.AttendanceStudentsDTO;
import br.com.present.commons.docs.IDocsMsgConsts;
import br.com.present.commons.exception.dto.ApiExceptionResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Attendance Controller", description = "Operations with attendance")
public interface IAttendanceController {

	@Operation(summary = "Find attendance by id", description = "Find attendance by id")
	@ApiResponses(value = { 
		@ApiResponse(responseCode = "200", description = IDocsMsgConsts.Responses.MSG_200, content =
			@Content(mediaType = IDocsMsgConsts.MediaType.APP_JSON, schema = 
				@Schema(implementation = AttendanceResponseDTO.class))),
		@ApiResponse(responseCode = "404", description = IDocsMsgConsts.Responses.MSG_404, content = 
			@Content(mediaType = IDocsMsgConsts.MediaType.APP_JSON, schema = 
				@Schema(implementation = ApiExceptionResponseDTO.class))),
	})
	ResponseEntity<AttendanceResponseDTO> findById(Long id);
	
	@Operation(summary = "Create a attendance", description = "Create a attendance")
	@ApiResponses(value = { 
		@ApiResponse(responseCode = "201", description = IDocsMsgConsts.Responses.MSG_201, content =
			@Content(mediaType = IDocsMsgConsts.MediaType.APP_JSON, schema =
				@Schema(implementation = AttendanceResponseDTO.class))),
		@ApiResponse(responseCode = "400", description = IDocsMsgConsts.Responses.MSG_400, content =
			@Content(mediaType = IDocsMsgConsts.MediaType.APP_JSON, schema =
				@Schema(implementation = ApiExceptionResponseDTO.class))),
		@ApiResponse(responseCode = "404", description = IDocsMsgConsts.Responses.MSG_404, content =
			@Content(mediaType = IDocsMsgConsts.MediaType.APP_JSON, schema =
				@Schema(implementation = ApiExceptionResponseDTO.class))),
	})
	ResponseEntity<AttendanceResponseDTO> create(AttendanceRequestDTO attendanceDto);
	
	@Operation(summary = "Update a attendance", description = "Update a attendance")
	@ApiResponses(value = { 
		@ApiResponse(responseCode = "200", description = IDocsMsgConsts.Responses.MSG_200, content = 
			@Content(mediaType = IDocsMsgConsts.MediaType.APP_JSON, schema = 
				@Schema(implementation = AttendanceResponseDTO.class))),
		@ApiResponse(responseCode = "400", description = IDocsMsgConsts.Responses.MSG_400, content =
			@Content(mediaType = IDocsMsgConsts.MediaType.APP_JSON, schema =
				@Schema(implementation = ApiExceptionResponseDTO.class))),
		@ApiResponse(responseCode = "404", description = IDocsMsgConsts.Responses.MSG_404, content = 
			@Content(mediaType = IDocsMsgConsts.MediaType.APP_JSON, schema = 
				@Schema(implementation = ApiExceptionResponseDTO.class))),
	})
	ResponseEntity<AttendanceResponseDTO> update(@PathVariable Long id, @Valid @RequestBody AttendanceRequestDTO attendanceDto);
	
	@Operation(summary = "Delete a attendance", description = "Delete a attendance")
	@ApiResponses(value = { 
		@ApiResponse(responseCode = "204", description = IDocsMsgConsts.Responses.MSG_204),
		@ApiResponse(responseCode = "404", description = IDocsMsgConsts.Responses.MSG_404, content = 
			@Content(mediaType = IDocsMsgConsts.MediaType.APP_JSON, schema = 
				@Schema(implementation = ApiExceptionResponseDTO.class))),
	})
	ResponseEntity<Void> delete(@PathVariable Long id);
	
	@Operation(summary = "Find all attendances", description = "Find all attendances")
	@ApiResponses(value = { 
		@ApiResponse(responseCode = "200", description = IDocsMsgConsts.Responses.MSG_200),
			@ApiResponse(responseCode = "404", description = IDocsMsgConsts.Responses.MSG_404, content =
			@Content(mediaType = IDocsMsgConsts.MediaType.APP_JSON, array =
			@ArraySchema(schema =
					@Schema(implementation = AttendanceResponseDTO.class)))),
	})
	ResponseEntity<List<AttendanceResponseDTO>> findAll();

	@Operation(summary = "Find attendance students by id event", description = "Find attendance by id event")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = IDocsMsgConsts.Responses.MSG_200, content =
			@Content(mediaType = IDocsMsgConsts.MediaType.APP_JSON, array =
			@ArraySchema(schema =
				@Schema(implementation = AttendanceStudentsDTO.class)))),
	})
	ResponseEntity<List<AttendanceStudentsDTO>> findAttendanceStudents(@PathVariable Long id);

}
