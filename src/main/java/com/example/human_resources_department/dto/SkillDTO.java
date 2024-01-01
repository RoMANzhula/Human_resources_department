package com.example.human_resources_department.dto;

import java.util.Objects;
import java.util.Set;

public class SkillDTO {
    private Long id;
    private String name;
    private String tag;
    private Set<Long> coworkerIds;

    public SkillDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Set<Long> getCoworkerIds() {
        return coworkerIds;
    }

    public void setCoworkerIds(Set<Long> coworkerIds) {
        this.coworkerIds = coworkerIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SkillDTO skillDTO = (SkillDTO) o;
        return Objects.equals(id, skillDTO.id) && Objects.equals(name, skillDTO.name) &&
                Objects.equals(tag, skillDTO.tag) && Objects.equals(coworkerIds, skillDTO.coworkerIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, tag, coworkerIds);
    }
}
