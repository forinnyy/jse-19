package ru.forinnyy.tm.repository;

import ru.forinnyy.tm.api.repository.ITaskRepository;
import ru.forinnyy.tm.model.Task;
import java.util.ArrayList;
import java.util.List;

public final class TaskRepository extends AbstractRepository<Task> implements ITaskRepository {

    @Override
    public List<Task> findAllByProjectId(final String projectId) {
        final List<Task> result = new ArrayList<>();
        for (final Task task: models) {
            if (task.getProjectId() == null) continue;
            if (task.getProjectId().equals(projectId)) result.add(task);
        }
        return result;
    }

    @Override
    public Task create(final String name) {
        final Task task = new Task();
        task.setName(name);
        return add(task);
    }

    @Override
    public Task create(final String name, final String description) {
        final Task task = new Task();
        task.setName(name);
        task.setDescription(description);
        return add(task);
    }

}
