package excel.export.transformer;

import excel.export.model.ParkingLot;
import org.springframework.stereotype.Component;

@Component
public class ParkingLotToXlsRowTransformer implements Transformer<ParkingLot, Object[]>{
    @Override
    public Object[] transform(ParkingLot source) {
        return new Object[0];
    }
}
