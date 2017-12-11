package excel.export.controller.enricher;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;

@Component
public class ResponseHeaderEnricher {

    public void enrich(HttpServletResponse response) {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Pragma", "public");
        response.setHeader("Cache-Control", "max-age=0");
        response.setHeader("Content-Disposition", "attachment; filename=result.xls");
    }

}
