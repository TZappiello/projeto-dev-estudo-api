package com.zap.service;

import com.zap.model.Task;
import com.zap.repository.TaskRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Transactional(rollbackOn = Exception.class)
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    @Transactional(rollbackOn = Exception.class)
    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }

    public Task findById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
    }

    @Transactional(rollbackOn = Exception.class)
    public Task updata(Long id, Task task) {
        Task taskOld = findById(id);
        modelMapper.map(task, taskOld);

        return taskRepository.save(taskOld);
    }
}
