package excel.export.service;

import excel.export.model.ParkingLot;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ExportToExcelFileService {
    private ParkingLotService parkingLotService;

    @Autowired
    public ExportToExcelFileService(ParkingLotService parkingLotService) {
        this.parkingLotService = parkingLotService;
    }

    public void getExcelFile() {
        //TODO implement excel generation here
        List<ParkingLot> allParkingLots = parkingLotService.getAllParkingLots();
    }

}
