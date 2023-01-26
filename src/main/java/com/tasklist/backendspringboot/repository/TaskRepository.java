package com.tasklist.backendspringboot.repository;

import com.tasklist.backendspringboot.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query( "SELECT t FROM Task t WHERE " +
            "(:title IS NULL OR :title ='' OR LOWER(t.title) LIKE LOWER(CONCAT('%', :title, '%'))) AND " +
            "(:completed IS NULL OR t.completed=:completed) AND " +
            "(:priorityId IS NULL OR t.completed=:priorityId) AND " +
            "(:categoryId IS NULL OR t.completed=:categoryId)" )
    Page<Task> findByParams(@Param("title") String title,
                            @Param("completed") Integer completed,
                            @Param("priorityId") Long priorityId,
                            @Param("categoryId") Long categoryId,
                            Pageable pageable);

}
