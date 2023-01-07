package com.zensar.pm.panel.dto;
import java.util.Objects;

 

public class PanelAvailabilityPHDTO {

    private int panelId;
    private String PanelName;
    private String grade;
    public int getPanelId() {
        return panelId;
    }
    public void setPanelId(int panelId) {
        this.panelId = panelId;
    }
//    public String getPanelName() {
//        return PanelName;
//    }
//    public void setPanelName(String panelName) {
//        PanelName = panelName;
//    }
    public String getGrade() {
        return grade;
    }
    public void setGrade(String grade) {
        this.grade = grade;
    }
    @Override
    public int hashCode() {
        return Objects.hash(PanelName, grade, panelId);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PanelAvailabilityPHDTO other = (PanelAvailabilityPHDTO) obj;
        return Objects.equals(PanelName, other.PanelName) && Objects.equals(grade, other.grade)
&& panelId == other.panelId;
    }
    @Override
    public String toString() {
        return "PanelAvailabilitySelfDTO [panelId=" + panelId + ", PanelName=" + PanelName + ", grade=" + grade + "]";
    }
    public PanelAvailabilityPHDTO(int panelId, String panelName, String grade) {
        super();
        this.panelId = panelId;
        PanelName = panelName;
        this.grade = grade;
    }
    public PanelAvailabilityPHDTO() {
        super();
    }
    public PanelAvailabilityPHDTO(int panelId, String grade) {
        super();
        this.panelId = panelId;
        this.grade = grade;
    }


}