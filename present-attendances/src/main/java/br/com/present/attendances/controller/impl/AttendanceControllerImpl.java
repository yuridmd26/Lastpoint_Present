package br.com.present.attendances.controller.impl;

import java.util.List;

import br.com.present.attendances.model.AttendanceStudentsDTO;
import br.com.present.attendances.queues.publisher.QueueAttendancesPublisher;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.present.attendances.controller.IAttendanceController;
import br.com.present.attendances.model.AttendanceRequestDTO;
import br.com.present.attendances.model.AttendanceResponseDTO;
import br.com.present.attendances.service.AttendanceService;
import br.com.present.commons.util.consts.MicroservicesConsts;

@RestController
@AllArgsConstructor
@RequestMapping(MicroservicesConsts.ApiVersions.V1 + MicroservicesConsts.Attendance.ROUTE)
public final class AttendanceControllerImpl implements IAttendanceController {

    private final AttendanceService attendanceService;
	private final QueueAttendancesPublisher attendancePublisher;

	@Override
	@GetMapping("{id}")
    public ResponseEntity<AttendanceResponseDTO> findById(@PathVariable Long id) {
		attendancePublisher.publish(1);
		return ResponseEntity.ok(attendanceService.findById(id));
    }

	@Override
	@PostMapping
    public ResponseEntity<AttendanceResponseDTO> create(@Valid @RequestBody AttendanceRequestDTO attendanceDto) {
		return new ResponseEntity<>(attendanceService.create(attendanceDto), HttpStatus.CREATED);
    }

	@Override
	@PutMapping("{id}")
    public ResponseEntity<AttendanceResponseDTO> update(@PathVariable Long id, @Valid @RequestBody AttendanceRequestDTO attendanceDto) {
		return ResponseEntity.ok(attendanceService.update(id, attendanceDto));
    }

	@Override
	@DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
		attendanceService.delete(id);
		return ResponseEntity.noContent().build();
    }

	@Override
	@GetMapping("/all")
    public ResponseEntity<List<AttendanceResponseDTO>> findAll() {
		return ResponseEntity.ok(attendanceService.findAll());
    }

	@Override
	@GetMapping("/status/{classId}")
	public ResponseEntity<List<AttendanceStudentsDTO>> findAttendanceStudents(@PathVariable Long classId)
	{
		return ResponseEntity.ok(attendanceService.findAttendanceStudents(classId));
	}

}
