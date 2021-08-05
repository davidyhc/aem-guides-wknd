package com.adobe.aem.guides.wknd.core.models.process;
import javax.jcr.Repository; 
import javax.jcr.Session; 
import javax.jcr.SimpleCredentials; 
import javax.jcr.Node; 
import javax.jcr.Workspace;
import org.apache.jackrabbit.commons.JcrUtils;
public class test { 

    public static void main(String[] args) throws Exception { 

    try { 
    
    //Create a connection to the CQ repository running on local host 
     Repository repository = JcrUtils.getRepository("http://localhost:4502/crx/server");
    
       //Create a Session
       javax.jcr.Session session = repository.login( new SimpleCredentials("admin", "admin".toCharArray())); 
    
       Workspace workspace = session.getWorkspace();
        System.out.println(workspace.getName()); 
        //make sure you doesn't have test folder in /etc/mynodes/test it will create the test folder
    //   workspace.copy("/content/dam/geometrixx/portraits", "/etc/mynodes/test");
    //    System.out.println("workspace copy completed"); 
    
      session.logout();
      }
     catch(Exception e){
      e.printStackTrace();
      }
     } 
    }