package com.tasklist.backendspringboot.search;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class TaskSearchValues {

    //search params
    private String title;
    private Integer completed;
    private Long priorityId;
    private Long categoryId;

    //pageable params
    private Integer pageNumber;
    private Integer pageSize;

    //sort params
    private String sortColumn;
    private String sortDirection;
}
