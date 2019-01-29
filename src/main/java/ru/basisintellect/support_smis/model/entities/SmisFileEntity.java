package ru.basisintellect.support_smis.model.entities;

import javax.persistence.*;

@Entity
@Table(name="smis_file")
public class SmisFileEntity extends CustomEntity{
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "smis_id", nullable = false)
    SmisEntity smis;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_id", nullable = false)
    FileEntity file;

    public SmisEntity getSmis() {
        return smis;
    }

    public void setSmis(SmisEntity smis) {
        this.smis = smis;
    }

    public FileEntity getFile() {
        return file;
    }

    public void setFile(FileEntity file) {
        this.file = file;
    }
}
