package org.concordion.internal.listener;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.concordion.api.Element;
import org.concordion.api.Resource;
import org.concordion.api.listener.SpecificationProcessingEvent;
import org.concordion.api.listener.SpecificationProcessingListener;

public class PageFooterRenderer implements SpecificationProcessingListener {

    protected static final String CONCORDION_WEBSITE_URL = "http://www.concordion.org";
    private long startMillis;
    
    public PageFooterRenderer() {
    }

    public void beforeProcessingSpecification(SpecificationProcessingEvent event) {
        startMillis = System.currentTimeMillis();
    }

    public void afterProcessingSpecification(SpecificationProcessingEvent event) {
        try {
            long millisTaken = System.currentTimeMillis() - startMillis;
            addFooterToDocument(event.getRootElement(), event.getResource(), millisTaken);
        } catch (Throwable t) {
            t.printStackTrace();
            System.err.println("Failed to write page footer. " + t.getMessage());
        }
    }

    private void addFooterToDocument(Element rootElement, Resource resource, long millisTaken) {
        Element body = rootElement.getFirstChildElement("body");
        
        if (body != null) {
            Element footer = new Element("div");
            footer.addStyleClass("footer");

            addResultsGeneratedBy(footer);
            addDateGeneratedAt(millisTaken, footer);
            
            body.appendChild(footer);
        }
    }

    protected void addDateGeneratedAt(long millisTaken, Element footer) {
        Element dateDiv = new Element("div");
        dateDiv.addStyleClass("testTime");
        dateDiv.appendText("in " + (millisTaken + 1) + " ms ");
        dateDiv.appendText(new SimpleDateFormat("'on' dd-MMM-yyyy 'at' HH:mm:ss z").format(new Date()));
        footer.appendChild(dateDiv);
    }

    protected void addResultsGeneratedBy(Element footer) {
        footer.appendText("Results generated by ");

        Element link = new Element("a");
        link.addAttribute("href", CONCORDION_WEBSITE_URL);
        link.addAttribute("style", "font-weight: bold; text-decoration: none; color: #89C;");
        footer.appendChild(link);
        link.appendText("Concordion");
    }
}
