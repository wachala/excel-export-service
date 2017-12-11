package excel.export.transformer;

import excel.export.model.ParkingLotType;
import org.springframework.stereotype.Component;

import java.util.Map;

import static java.util.Objects.nonNull;

@Component
public class CapacityTransformer implements Transformer<Map<ParkingLotType, Integer>, String> {
    @Override
    public String transform(Map<ParkingLotType, Integer> source) {
        StringBuilder builder = new StringBuilder();

        for (ParkingLotType parkingLotType : ParkingLotType.values()) {
            Integer result = source.get(parkingLotType);

            builder.append(nonNull(result) ? parkingLotType + ": " + result : parkingLotType + ": -");
        }

        return builder.toString();
    }
}
