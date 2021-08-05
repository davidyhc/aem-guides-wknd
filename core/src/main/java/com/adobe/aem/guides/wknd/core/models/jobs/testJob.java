package com.adobe.aem.guides.wknd.core.models.jobs;

import javax.jcr.Node;
import javax.jcr.Repository;
import javax.jcr.Session;
import javax.xml.ws.Action;

import com.adobe.aem.guides.wknd.core.models.jobs.CreateFolderService.Config;

import org.apache.abdera.model.DateTime;
import org.apache.sling.jcr.api.SlingRepository;
import org.joda.time.LocalDateTime;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Component(service = Runnable.class)
@Designate(ocd = testJob.Config.class)

public class testJob implements Runnable{

    @ObjectClassDefinition(name="A job to ")
    public static @interface Config{
        @AttributeDefinition(name="cron-job expression")
        String scheduler_expression() default "*/30 * * * * ?";

        @AttributeDefinition(name="Concurrent Task", description="whether the job is concurrent or not")
        boolean scheduler_concurrent() default false;

        @AttributeDefinition(name="create path")
        String create_path() default "/var/PracticeCreateFolder";
    }
    private static final Logger LOGGER = LoggerFactory.getLogger(testJob.class);

    @Activate
    protected void activate(final Config config){

    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        LOGGER.info("This is the simple job");
        
        
        
    }
    
}
