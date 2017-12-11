package excel.export.service.excel;

import excel.export.model.ParkingLot;
import excel.export.service.ParkingLotService;
import excel.export.transformer.ParkingLotToXlsRowTransformer;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j
@Service
public class ExportToExcelFileService {
    private ParkingLotService parkingLotService;
    private ParkingLotToXlsRowTransformer parkingLotTransformer;

    @Autowired
    public ExportToExcelFileService(ParkingLotService parkingLotService, ParkingLotToXlsRowTransformer parkingLotTransformer) {
        this.parkingLotService = parkingLotService;
        this.parkingLotTransformer = parkingLotTransformer;
    }

    public HSSFWorkbook getExcelFile() {
        List<ParkingLot> allParkingLots = parkingLotService.getAllParkingLots();
        List<Object[]> excelRows = allParkingLots.stream()
                .map(parkingLot -> parkingLotTransformer.transform(parkingLot))
                .collect(toList());

        return new XlsBuilder()
                .withSheet(
                        Collections.singletonList(
                                new XlsSheetBuilder()
                                        .withName("Parking lots overview")
                                        .withTable(
                                                Collections.singletonList(
                                                        new XlsTableBuilder()
                                                                .withTitle("Parking Lots overview")
                                                                .withHeaders(HeaderFactory.getParkingLotHeader())
                                                                .withRows(excelRows)
                                                )
                                        )
                        )
                ).build();
    }

    public HSSFWorkbook getExcelFileForParkingLot(Long id) {
        ParkingLot parkingLot = parkingLotService.getParkingLotById(id);
        Object[] excelRows = parkingLotTransformer.transform(parkingLot);

        return new XlsBuilder()
                .withSheet(
                        Collections.singletonList(
                                new XlsSheetBuilder()
                                        .withName("Details of parking lot: " + id)
                                        .withTable(
                                                Collections.singletonList(
                                                        new XlsTableBuilder()
                                                                .withTitle("Parking Lots overview")
                                                                .withHeaders(HeaderFactory.getParkingLotHeader())
                                                                .withRows(Collections.singletonList(
                                                                        excelRows
                                                                ))
                                                )
                                        )
                        )
                )
                .build();
    }

}
