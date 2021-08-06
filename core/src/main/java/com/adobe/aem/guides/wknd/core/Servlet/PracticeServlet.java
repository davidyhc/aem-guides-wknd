package com.adobe.aem.guides.wknd.core.Servlet;
import java.io.IOException;
import java.io.PrintWriter;

import javax.json.Json;
import javax.servlet.Servlet;
import javax.servlet.ServletException;

import com.day.cq.commons.JSONItem;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.json.JSONException;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = { Servlet.class })
@SlingServletResourceTypes(
    resourceTypes="wknd/components/page", 
    methods= "GET",
    extensions="html",
    selectors="hello")

public class PracticeServlet extends SlingSafeMethodsServlet {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        try {
            PrintWriter out = response.getWriter();
            out.println("Read + Write servlet got executed!!!");
            JSONObject jsob = new JSONObject();
            String contentpath = request.getContextPath();
            String contenttype = request.getContentType();
            String contentlength= String.valueOf(request.getContentLength());
            jsob.put("Content path", contentpath);
            jsob.put("Content type", contenttype);
            jsob.put("Content Length", contentlength);
            response.getWriter().println(jsob.toString());
            
           } catch (IOException | JSONException e) {
            log.error(e.getMessage(), e);
           }
    }
    
}
