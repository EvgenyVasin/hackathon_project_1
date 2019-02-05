package ru.basisintellect.support_smis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.basisintellect.support_smis.exceptions.EntityNotFoundException;
import ru.basisintellect.support_smis.exceptions.InvalidEntityException;
import ru.basisintellect.support_smis.model.entities.SmisFileEntity;
import ru.basisintellect.support_smis.repositories.SmisFileRepository;
import ru.basisintellect.support_smis.utils.EncryptionUtils;

import java.io.File;
import java.util.List;

import static ru.basisintellect.support_smis.utils.EncryptionUtils.getFileHash;

@Service
public class FileAssetService  implements FileStorageService<SmisFileEntity> {

    @Autowired
    private  SmisFileRepository fileAssetRepository;
    @Autowired
    private  FileStorageProvider fileStorageProvider;


    public SmisFileEntity createFileAsset(SmisFileEntity fileAsset, File content) {

        return fileAssetRepository.findByHash(getFileHash(content)).orElseGet(() -> {
            validateFileAsset(fileAsset);
            validateFileContent(content);

            File savedContent = fileStorageProvider.uploadFile(content);

            try {
                return persist(fileAsset, savedContent);
            } catch (RuntimeException ex) {
                fileStorageProvider.deleteFile(savedContent);
                throw ex;
            }
        });

    }

    public List<SmisFileEntity> getFileAssets() {
        return (List<SmisFileEntity>) fileAssetRepository.findAll();
    }

    public SmisFileEntity findByFileName(String fileName) {
        return (SmisFileEntity) fileAssetRepository
                .findByFileName(fileName)
                .orElse(null);
    }



    public void deleteFileAssets(List<SmisFileEntity> assets) {
        fileAssetRepository.deleteAll(assets);
    }

    public SmisFileEntity getFileAsset(Long id) {
        return fileAssetRepository
                .findById(id)
                .filter(fileAsset -> fileAsset
                        .getHash()
                        .equalsIgnoreCase(EncryptionUtils.getFileHash(fileStorageProvider.getFile(fileAsset.getFileName()))))
                .orElseThrow(() -> new EntityNotFoundException(SmisFileEntity.class));
    }

    public SmisFileEntity getFileAsset(String hash) {
        return fileAssetRepository
                .findByHash(hash)
                .filter((SmisFileEntity fileAsset) -> {
                    return fileAsset
                            .getHash()
                            .equalsIgnoreCase(EncryptionUtils.getFileHash(fileStorageProvider.getFile(fileAsset.getFileName())));
                })
                .orElseThrow(() -> new EntityNotFoundException(SmisFileEntity.class));
    }

    public void deleteFileAsset(Long id) {
        SmisFileEntity fileAsset = getFileAsset(id);
        fileStorageProvider.deleteFile(fileStorageProvider.getFile(fileAsset.getFileName()));
        fileAssetRepository.delete(fileAsset);
    }

    private SmisFileEntity persist(SmisFileEntity entity, File savedFile) {

        entity = setHash(entity, savedFile);
        entity = setFileName(entity, savedFile);
        return fileAssetRepository.save(entity);
    }

    private SmisFileEntity setFileName(SmisFileEntity entity, File savedFile) {
        entity.setFileName(savedFile.getName());
        return entity;
    }

    private SmisFileEntity setHash(SmisFileEntity entity, File savedFile) {
        entity.setHash(getFileHash(savedFile));
        return entity;
    }







    private void validateFileAsset(SmisFileEntity asset) {
        if (null == asset) {

            throw new InvalidEntityException(SmisFileEntity.class, "No file asset presented.");
        }

        if (null == asset.getName()) {
            throw new InvalidEntityException(SmisFileEntity.class, "No file name presented.");
        }
    }

    private void validateFileContent(File content) {
        if (null == content) {
            throw new InvalidEntityException(SmisFileEntity.class, "No file presented.");
        }

        if (0 == content.length()) {
            throw new InvalidEntityException(SmisFileEntity.class, "File is empty.");
        }
    }


    public File getFile(String fileName) {
       return fileStorageProvider.getFile(fileName);
    }
}
