package com.djb.resttesting;

import freemarker.cache.FileTemplateLoader;
import freemarker.template.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TemplateHandler {

    private static final Logger LOG = LoggerFactory.getLogger(TemplateHandler.class);
    private static final String TEMPLATE_PATH = "/src/test/resources/templates/";

    private final Configuration cfg = new Configuration(Configuration.VERSION_2_3_26);
    private final Map<String, Object> data = new HashMap<>();

    private Template template;

    public TemplateHandler() {
    }

    public TemplateHandler(String template) throws IOException {
        this.template = cfg.getTemplate(TEMPLATE_PATH + template);
    }

    public void setTemplate(String template) throws IOException {
        FileTemplateLoader templateLoader = new FileTemplateLoader(new File("C:/Java_projects/other/RestTesting/src/test/resources/templates/"));
        cfg.setTemplateLoader(templateLoader);

        this.template = cfg.getTemplate( template);
    }

    public void setValue(String variable, String value) {
        data.put(variable, value);
    }

    public void setValue(String variable, int value) {
        data.put(variable, value);
    }

    public void setValue(String variable, List value) {
        data.put(variable, value);
    }

    public void setValue(String variable, Map value) {
        data.put(variable, value);
    }

    public String getOutput() throws TemplateException, IOException {
        try {
            Writer result = new StringWriter();
            template.process(data, result);
            return result.toString();
        } catch (IOException e) {
            LOG.error("Cannot generate output: \n{}", e);
        } catch (TemplateException e) {
            LOG.error("Cannot generate template output: \n{}", e);
        }
        return null;
    }

}

