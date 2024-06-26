package ru.forinnyy.tm.service;

import ru.forinnyy.tm.api.repository.ITaskRepository;
import ru.forinnyy.tm.api.service.ITaskService;
import ru.forinnyy.tm.enumerated.Sort;
import ru.forinnyy.tm.enumerated.Status;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.entity.TaskNotFoundException;
import ru.forinnyy.tm.exception.field.*;
import ru.forinnyy.tm.model.Task;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public final class TaskService extends AbstractService<Task, ITaskRepository> implements ITaskService {

    public TaskService(ITaskRepository repository) {
        super(repository);
    }

    @Override
    public List<Task> findAllByProjectId(String projectId) {
        if (projectId == null || projectId.isEmpty()) return Collections.emptyList();
        return repository.findAllByProjectId(projectId);
    }

    @Override
    public Task create(final String name, final String description) throws AbstractFieldException {
        if (name == null || name.isEmpty()) throw new NameEmptyException();
        if (description == null || description.isEmpty()) throw new DescriptionEmptyException();
        return repository.create(name, description);
    }

    @Override
    public Task create(final String name) throws AbstractFieldException {
        if (name == null || name.isEmpty()) throw new NameEmptyException();
        return repository.create(name);
    }

    @Override
    public Task updateById(final String id, final String name, final String description) throws AbstractFieldException, AbstractEntityException {
        if (id == null || id.isEmpty()) throw new TaskIdEmptyException();
        if (name == null || name.isEmpty()) throw new NameEmptyException();
        final Task task = findOneById(id);
        if (task == null) throw new TaskNotFoundException();
        task.setName(name);
        task.setDescription(description);
        return task;
    }

    @Override
    public Task updateByIndex(final Integer index, final String name, final String description) throws AbstractFieldException, AbstractEntityException {
        if (index == null || index < 0 || index > repository.getSize()) throw new IndexIncorrectException();
        if (name == null || name.isEmpty()) throw new NameEmptyException();
        final Task task = findOneByIndex(index);
        if (task == null) throw new TaskNotFoundException();
        task.setName(name);
        task.setDescription(description);
        return task;
    }

    @Override
    public Task changeTaskStatusById(final String id, final Status status) throws AbstractFieldException, AbstractEntityException {
        if (id == null || id.isEmpty()) throw new TaskIdEmptyException();
        final Task task = findOneById(id);
        if (task == null) throw new TaskNotFoundException();
        task.setStatus(status);
        return task;
    }

    @Override
    public Task changeTaskStatusByIndex(final Integer index, final Status status) throws AbstractFieldException, AbstractEntityException {
        if (index == null || index < 0 || index > repository.getSize()) throw new IndexIncorrectException();
        if (index >= repository.getSize()) throw new IndexIncorrectException();
        final Task task = findOneByIndex(index);
        if (task == null) throw new TaskNotFoundException();
        task.setStatus(status);
        return task;
    }

}
