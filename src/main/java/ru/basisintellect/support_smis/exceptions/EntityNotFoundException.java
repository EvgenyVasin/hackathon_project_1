package ru.basisintellect.support_smis.exceptions;

import ru.basisintellect.support_smis.model.entities.SmisFileEntity;

public class EntityNotFoundException extends FileStorageException {
    public EntityNotFoundException(Class<? extends SmisFileEntity> entity) {
        super("Entity not found: " + entity.getSimpleName());
    }
}
