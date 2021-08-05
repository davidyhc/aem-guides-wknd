package com.adobe.aem.guides.wknd.core.models.jobs;

import org.slf4j.*;

import javax.jcr.LoginException;
import javax.jcr.Node;
import javax.jcr.Repository;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;
import javax.xml.ws.Action;

import org.apache.abdera.model.DateTime;
import org.apache.jackrabbit.commons.JcrUtils;
import org.apache.poi.ss.formula.functions.T;
import org.apache.sling.jcr.api.SlingRepository;
import org.joda.time.LocalDateTime;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.slf4j.Logger;

@Component(service = Runnable.class)
@Designate(ocd = CreateFolderService.Config.class)
public class CreateFolderService implements Runnable {
    
    @ObjectClassDefinition(name="A job to create folder every hour")
    public static @interface Config{
        @AttributeDefinition(name="cron-job expression")
        String scheduler_expression() default "*/30 * * * * ?";

        @AttributeDefinition(name="Concurrent Task", description="whether the job is concurrent or not")
        boolean scheduler_concurrent() default false;

        @AttributeDefinition(name="create path")
        String create_path() default "/var/PracticeCreateFolder";
    }
    private static final Logger LOGGER = LoggerFactory.getLogger(CreateFolderService.class);
    private String createPath;
    @Reference
    private SlingRepository repository;


    @Activate
    protected void activate(final Config config) throws LoginException, RepositoryException{
      
        this.createPath = (String.valueOf(config.create_path())!= null) ? String.valueOf(config.create_path()): null;
        LOGGER.info("createpath = "+ createPath);
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        Session session = null;
        try{
            
            // LOGGER.info("Obtain the service");
            // session = repository.loginService(null, repository.getDefaultWorkspace());
            session = repository.login(new SimpleCredentials("admin", "admin".toCharArray()));

            // LOGGER.info("Obtain the service1");
            // if(repository == null){
            //     LOGGER.info(" repo is null");
            // }
            // LOGGER.info("repo is not null");

            // session = this.repository.loginService(null,repository.getDefaultWorkspace());
            // Repository repository = JcrUtils.getRepository("http://localhost:4502/crx/server");
            // session = repository.login(new SimpleCredentials("admin","admin".toCharArray()));
            
            LOGGER.info("session is "+ (session == null)+ " null");
           
            if(session.itemExists(createPath) == true){
                LOGGER.info("Obtain the block");
                Node folder = session.getNode(createPath);
                LocalDateTime time = new LocalDateTime().now();
                String title = String.format("%d_%d_%d_%d", time.getYear(), time.getMonthOfYear(),time.getDayOfMonth(),time.getHourOfDay());
                folder.addNode(title);
                
            }
            session.save();
        }catch(Exception e){
            LOGGER.info("repository exception", e);
        }
        finally{
            if(session != null){
                session.logout();
            }
        }
        
    }
    
}
