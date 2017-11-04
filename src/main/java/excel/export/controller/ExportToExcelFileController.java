package excel.export.controller;

import excel.export.service.ExportToExcelFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Slf4j
@RestController
@RequestMapping("/api/parking-lot")
public class ExportToExcelFileController {

    ExportToExcelFileService excelFileService;

    @Autowired
    public ExportToExcelFileController(ExportToExcelFileService excelFileService) {
        this.excelFileService = excelFileService;
    }

    @RequestMapping(value = "/{id}", method = GET)
    public String exportToExcelByParkingLotId(@PathVariable(name = "id") Long id) {
        log.info("Exporting to excel file for parking lot with id {}", id);

        return "Hello from export controller. Id passed: " + id;
    }

    @RequestMapping(method = GET)
    public String exportToExcelForAllParkingLots() {
        log.info("Exporting to excel file for all parking lots");

        return "Exporting to excel based on all parking lots data";
    }

}
