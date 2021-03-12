package java2.application_target_list.core.junittests.matchers;

import java2.application_target_list.core.domain.Record;
import org.mockito.ArgumentMatcher;

public class RecordMatcher implements ArgumentMatcher<Record> {

    private Long targetId;
    private Long userId;

    public RecordMatcher(Long targetId, Long userId) {
        this.targetId = targetId;
        this.userId = userId;
    }

    @Override
    public boolean matches(Record record) {
        return record.getTargetId().equals(targetId)
                && record.getUserId().equals(userId);
    }
}
