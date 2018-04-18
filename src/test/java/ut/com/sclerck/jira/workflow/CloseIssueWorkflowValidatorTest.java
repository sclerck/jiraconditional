package ut.com.sclerck.jira.workflow;

import com.sclerck.jira.workflow.CloseIssueWorkflowValidator;

import com.atlassian.jira.issue.MutableIssue;
import com.opensymphony.workflow.InvalidInputException;
import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CloseIssueWorkflowValidatorTest
{
    public static final String FIELD_WORD = "word";

    protected CloseIssueWorkflowValidator validator;
    protected MutableIssue issue;

    @Before
    public void setup() {
        issue = mock(MutableIssue.class);
        validator = new CloseIssueWorkflowValidator();
    }

    @Test
    public void testValidates() throws Exception {
        Map transientVars = new HashMap();
        transientVars.put(FIELD_WORD, "test");
        transientVars.put("issue", issue);
        when(issue.getDescription()).thenReturn("This description has test in it.");

        // Should not throw an exception
        validator.validate(transientVars, null, null);
    }

    @Test(expected = InvalidInputException.class)
    public void testFailsValidation() throws Exception {
        Map transientVars = new HashMap();
        transientVars.put(FIELD_WORD, "test");
        transientVars.put("issue", issue);
        when(issue.getDescription()).thenReturn("This description does not have the magic word in it.");

        // Should throw the expected exception
        validator.validate(transientVars, null, null);
    }

}
