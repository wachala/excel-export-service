package excel.export.transformer;

import excel.export.model.ParkingLot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ParkingLotToXlsRowTransformer implements Transformer<ParkingLot, Object[]> {

    private final CapacityTransformer capacityTransformer;

    @Autowired
    public ParkingLotToXlsRowTransformer(CapacityTransformer capacityTransformer) {
        this.capacityTransformer = capacityTransformer;
    }

    @Override
    public Object[] transform(ParkingLot source) {
        return new Object[]{
                source.getId(),
                source.getLocation(),
                source.getAddress(),
                source.getPricing(),
                source.getSecurity(),
                source.getRestrictions(),
                source.getAdditionalInformation(),
                source.getGoogleStreetViewLink(),
                capacityTransformer.transform(source.getParkingCapacity()),
        };
    }
}
