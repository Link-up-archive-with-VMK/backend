package com.guide.run.user.entity;

import com.guide.run.global.converter.StringListConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArchiveData {
    @Id
    private String privateId;
    private String runningPlace;

    @Convert(converter = StringListConverter.class)
    private List<String> howToKnow = new ArrayList<>();

    private String motive;
    private String hopePrefs;
    private boolean privacy;
    private boolean portraitRights;

    @Convert(converter = StringListConverter.class)
    private List<String> deleteReasons = new ArrayList<>(); //탈퇴 사유

    public void editPermisson(
            boolean privacy,
            boolean portraitRights
    ){
        this.privacy = privacy;
        this.portraitRights = portraitRights;
    }

    public void editRunningInfo(
            List<String> howToKnow,
            String motive,
            String hopePrefs,
            String runningPlace
    ) {
        this.howToKnow = howToKnow;
        this.motive = motive;
        this.hopePrefs = hopePrefs;
        this.runningPlace = runningPlace;
    }

    public void deleteArchive(List<String> deleteReasons){
        this.runningPlace = null;
        this.motive = null;
        this.howToKnow = null;
        this.hopePrefs = null;
        this.privacy = false;
        this.portraitRights = false;
        this.deleteReasons = deleteReasons;
    }
}
