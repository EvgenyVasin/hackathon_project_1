package ru.basisintellect.support_smis.exceptions;

import ru.basisintellect.support_smis.model.entities.SmisFileEntity;

public class InvalidEntityException extends FileStorageException  {

    public InvalidEntityException(Class<? extends SmisFileEntity> entity) {
        this(entity, "");
    }

    public InvalidEntityException(Class<? extends SmisFileEntity> entity, String message) {
        super("Invalid entity: " + entity.getSimpleName() + " caused by: " + message);
    }
}
