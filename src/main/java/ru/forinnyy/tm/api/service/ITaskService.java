package ru.forinnyy.tm.api.service;

import ru.forinnyy.tm.enumerated.Status;
import ru.forinnyy.tm.exception.entity.AbstractEntityException;
import ru.forinnyy.tm.exception.field.AbstractFieldException;
import ru.forinnyy.tm.model.Task;

import java.util.List;

public interface ITaskService extends IService<Task> {

    Task updateById(String id, String name, String description) throws AbstractFieldException, AbstractEntityException;

    Task updateByIndex(Integer index, String name, String description) throws AbstractFieldException, AbstractEntityException;

    Task changeTaskStatusById(String id, Status status) throws AbstractFieldException, AbstractEntityException;

    Task changeTaskStatusByIndex(Integer index, Status status) throws AbstractFieldException, AbstractEntityException;

    List<Task> findAllByProjectId(String projectId);

    Task create(String name, String description) throws AbstractFieldException;

    Task create(String name) throws AbstractFieldException;

}
