package com.adobe.aem.guides.wknd.core.models.process;


import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.Repository;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;
import javax.jcr.Workspace;

import org.apache.jackrabbit.commons.JcrUtils;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;
import com.adobe.xfa.Document;
import com.day.cq.commons.jcr.JcrUtil;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;

@Component(property = {
	Constants.SERVICE_DESCRIPTION + "=Move a file from one folder to another",
	Constants.SERVICE_VENDOR + "=Adobe Systems",
	"process.label" + "=Move Files"
})
public class moveFileProcess implements WorkflowProcess {

	private static final Logger log = LoggerFactory.getLogger(moveFileProcess.class);
	@Reference
	QueryBuilder queryBuilder;

	@Override
	public void execute(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap processArguments)
	throws WorkflowException {
		// TODO Auto-generated method stub
		log.debug("The string I got was ..." + processArguments.get("PROCESS_ARGS", "string").toString());
		String addr = processArguments.get("PROCESS_ARGS", "string").toString();
        try {
            Repository repository = JcrUtils.getRepository("http://localhost:4502/crx/server");
            Session session = repository.login(new SimpleCredentials("admin","admin".toCharArray()));
            String payloadPath = workItem.getWorkflowData().getPayload().toString();
            log.info(payloadPath);
            Workspace workspace = session.getWorkspace();
            workspace.copy(payloadPath, addr);


        } catch (RepositoryException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
