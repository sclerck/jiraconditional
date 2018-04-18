package com.sclerck.jira.workflow;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.CustomFieldManager;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.fields.CustomField;
import com.opensymphony.module.propertyset.PropertySet;
import com.opensymphony.workflow.InvalidInputException;
import com.opensymphony.workflow.Validator;

public class CloseIssueWorkflowValidator implements Validator {
	private static final Logger log = LoggerFactory.getLogger(CloseIssueWorkflowValidator.class);
	public static final String FIELD_CATEGORY = "category";

	private final CustomFieldManager customFieldManager;

	public CloseIssueWorkflowValidator() {
		this.customFieldManager = ComponentAccessor.getCustomFieldManager();
	}

	public void validate(Map transientVars, Map args, PropertySet ps) throws InvalidInputException {
		Issue issue = (Issue) transientVars.get("issue");

		CustomField categoryField = customFieldManager.getCustomFieldObjectByName(FIELD_CATEGORY);

		String category = (String) issue.getCustomFieldValue(categoryField);

		if (category == null || category.equals("Uncategorized")) {
			throw new InvalidInputException("Category is unset!");
		}
	}
}
