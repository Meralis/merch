package org.klim.istock.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import java.util.LinkedList;
import java.util.List;

@Data
@ToString(exclude = {"parent"})
public class CategoryDTO {
    private final String name;
    @JsonIgnore
    private final CategoryDTO parent;
    private final List<CategoryDTO> children = new LinkedList<>();
}
