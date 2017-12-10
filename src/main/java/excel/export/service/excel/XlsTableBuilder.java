package excel.export.service.excel;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;

import java.util.List;

public class XlsTableBuilder {
    private String title;
    private Object[] headers;
    private List<Object[]> rows;

    public XlsTableBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public XlsTableBuilder withHeaders(Object[] headers) {
        this.headers = headers;
        return this;
    }

    public XlsTableBuilder withRows(List<Object[]> rows) {
        this.rows = rows;
        return this;
    }

    public void build(HSSFSheet sheet) {
        int maxColumn;

        //TITLE
        Row titleRow = sheet.createRow(0);
        Cell titleCell = titleRow.createCell(0);
        // TODO prepare style for headers
        // titleCell.setCellStyle(styles.get("title"));

        titleCell.setCellValue(title);
        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$L$1"));

        //HEADER ROW
        Row headerRow = sheet.createRow(1);
        headerRow.setHeightInPoints(40);
        Cell headerCell;

        maxColumn = headers.length;
        for (int i = 0; i < headers.length; i++) {
            headerCell = headerRow.createCell(i);
            headerCell.setCellValue((String) headers[i]);
            // TODO add some styles
            // headerCell.setCellStyle(styles.get("header"));
        }

        int currentRow = 2;
        for (Object rowData[] : rows) {
            Row row = sheet.createRow(currentRow);
            //TODO add styling

            for (int i = 0; i < rowData.length; i++) {
                Cell cell = row.createCell(i);

                //TODO add support for numeric values
                cell.setCellValue((String) rowData[i]);

                if (i > maxColumn)
                    maxColumn = i;
            }

            currentRow++;
        }

        for (int i = 0; i <= maxColumn; i++)
            sheet.autoSizeColumn(i);
    }

}
