package excel.export.controller;

import excel.export.controller.enricher.ResponseHeaderEnricher;
import excel.export.service.excel.ExportToExcelFileService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Slf4j
@RestController
@RequestMapping("excel-export-service/api/parking-lot")
public class ExportToExcelFileController {

    private final ExportToExcelFileService excelFileService;
    private final ResponseHeaderEnricher headerEnricher;

    @Autowired
    public ExportToExcelFileController(ExportToExcelFileService excelFileService, ResponseHeaderEnricher headerEnricher) {
        this.excelFileService = excelFileService;
        this.headerEnricher = headerEnricher;
    }

    @ApiOperation(value = "Returns excel file with detailed data of parking lot with given id.")
    @RequestMapping(value = "/{id}", method = GET)
    public void exportToExcelByParkingLotId(@PathVariable(name = "id") Long id, HttpServletResponse response) throws IOException {
        log.info("Exporting to excel file for parking lot with id {}", id);
        headerEnricher.enrich(response);

        HSSFWorkbook workbook = excelFileService.getExcelFileForParkingLot(id);

        workbook.write(response.getOutputStream());
        workbook.close();
    }

    @ApiOperation(value = "Returns excel file with detailed data of all parking lots.")
    @RequestMapping(method = GET)
    public void exportToExcelForAllParkingLots(HttpServletResponse response) throws IOException {
        log.info("Exporting to excel file for all parking lots");
        headerEnricher.enrich(response);

        HSSFWorkbook workbook = excelFileService.getExcelFile();

        workbook.write(response.getOutputStream());
        workbook.close();
    }

}
