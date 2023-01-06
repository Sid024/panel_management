package com.zensar.pm.panel.dto;
import java.util.Objects;
public class GradeDTO {

    private int gradeId;
    private String grade;
    public int getGradeId() {
        return gradeId;
    }
    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }
    public String getGrade() {
        return grade;
    }
    public void setGrade(String grade) {
        this.grade = grade;
    }
    @Override
    public int hashCode() {
        return Objects.hash(grade, gradeId);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        GradeDTO other = (GradeDTO) obj;
        return Objects.equals(grade, other.grade) && gradeId == other.gradeId;
    }
    @Override
    public String toString() {
        return "GradeDTO [gradeId=" + gradeId + ", grade=" + grade + "]";
    }
    public GradeDTO(int gradeId, String grade) {
        super();
        this.gradeId = gradeId;
        this.grade = grade;
    }
    public GradeDTO() {
        super();
    }



}