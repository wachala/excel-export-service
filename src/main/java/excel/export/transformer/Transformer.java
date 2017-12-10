package excel.export.transformer;

import java.util.List;

import static java.util.stream.Collectors.toList;

public interface Transformer<SRC, DEST> {

    DEST transform(SRC source);

    default List<DEST> transform(List<SRC> source) {
        return source.stream()
                .map(this::transform)
                .collect(toList());
    }
}
