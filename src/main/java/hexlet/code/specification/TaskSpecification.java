package hexlet.code.specification;

import hexlet.code.dto.taskDto.TaskParamsDto;
import hexlet.code.model.Task;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskSpecification {

    public Specification<Task> build(TaskParamsDto data) {
        return titleCont(data.getTitleCont()).and(assigneeId(data.getAssigneeId()))
                .and(status(data.getStatus())).and(labelId(data.getLabelId()));
    }

    private Specification<Task> titleCont(String title) {
        return ((root, query, cb) -> title == null ? cb.conjunction()
                : cb.like(root.get("name"), title));
    }

    private Specification<Task> assigneeId(Long id) {
        return ((root, query, cb) -> id == null ? cb.conjunction()
                : cb.equal(root.get("assignee").get("id"), id));
    }

    private Specification<Task> status(String status) {
        return (root, query, cb) -> status == null ? cb.conjunction()
                : cb.equal(root.get("taskStatus").get("slug"), status);
    }

    private Specification<Task> labelId(Long id) {
        return ((root, query, cb) -> id == null ? cb.conjunction()
                : cb.equal(root.get("labels").get("id"), id));
    }
}
