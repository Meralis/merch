package org.klim.istock.DTO;

import lombok.Data;
import lombok.ToString;

import java.util.LinkedList;
import java.util.List;

@Data
@ToString(exclude = {"parent"})
public class CategoryDTO {
    private final String name;
    private final CategoryDTO parent;
    private final List<CategoryDTO> children = new LinkedList<>();
}
