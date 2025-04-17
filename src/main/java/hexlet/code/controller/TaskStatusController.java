package hexlet.code.controller;

import hexlet.code.dto.TaskStatusCreateDto;
import hexlet.code.dto.TaskStatusDto;
import hexlet.code.dto.TaskStatusUpdateDto;
import hexlet.code.exeption.ResourceNotFoundException;
import hexlet.code.mapper.TaskStatusMapper;
import hexlet.code.model.TaskStatus;
import hexlet.code.repository.TaskStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/task_statuses")
public class TaskStatusController {

    @Autowired
    private TaskStatusMapper taskStatusMapper;

    @Autowired
    private TaskStatusRepository taskStatusRepository;

    @GetMapping(path = "")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskStatusDto> index() {
        List<TaskStatus> taskStatuses = taskStatusRepository.findAll();
        return taskStatuses.stream().map(taskStatusMapper::map).toList();
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskStatusDto show(@PathVariable long id) {
        TaskStatus taskStatus = taskStatusRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Task Status with id " + id + " not found"));
        return taskStatusMapper.map(taskStatus);
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public TaskStatusDto create(@RequestBody TaskStatusCreateDto data) {
        TaskStatus taskStatus = taskStatusMapper.map(data);
        taskStatusRepository.save(taskStatus);
        return taskStatusMapper.map(taskStatus);
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskStatusDto update(@PathVariable long id, @RequestBody TaskStatusUpdateDto data) {
        TaskStatus taskStatus = taskStatusRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Task Status with id " + id + " not found"));
        taskStatusMapper.update(data, taskStatus);
        taskStatusRepository.save(taskStatus);
        return taskStatusMapper.map(taskStatus);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        TaskStatus taskStatus = taskStatusRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Task Status with id " + id + " not found"));
        taskStatusRepository.delete(taskStatus);
    }
}
