package excel.export.service.excel;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
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
        CellStyle titleStyle = getTitleStyle(sheet.getWorkbook());
        titleCell.setCellStyle(titleStyle);
        titleCell.setCellValue(title);
        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$I$1"));

        //HEADER ROW
        Row headerRow = sheet.createRow(1);
        headerRow.setHeightInPoints(40);
        Cell headerCell;

        maxColumn = headers.length;
        CellStyle columnHeaderStyle = getHeaderStyle(sheet.getWorkbook());

        for (int i = 0; i < headers.length; i++) {
            headerCell = headerRow.createCell(i);
            headerCell.setCellStyle(columnHeaderStyle);
            headerCell.setCellValue((String) headers[i]);
            headerCell.setCellStyle(columnHeaderStyle);
        }

        //ACTUAL DATA
        int currentRow = 2;
        CellStyle cellStyle = getCellStyle(sheet.getWorkbook());
        for (Object rowData[] : rows) {
            Row row = sheet.createRow(currentRow);

            for (int i = 0; i < rowData.length; i++) {
                Cell cell = row.createCell(i);
                cell.setCellStyle(cellStyle);

                if (rowData[i] instanceof String)
                    cell.setCellValue((String) rowData[i]);
                else
                    cell.setCellValue((Long) rowData[i]);

                if (i > maxColumn)
                    maxColumn = i;
            }

            currentRow++;
        }

        for (int i = 0; i <= maxColumn; i++)
            sheet.autoSizeColumn(i);
    }

    private CellStyle getTitleStyle(HSSFWorkbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontHeightInPoints((short) 16);
        font.setBold(true);

        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setFillForegroundColor(IndexedColors.BRIGHT_GREEN.getIndex());

        return style;
    }

    private CellStyle getHeaderStyle(HSSFWorkbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontHeightInPoints((short) 14);
        font.setColor(IndexedColors.WHITE.getIndex());

        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setFillForegroundColor(IndexedColors.GREY_80_PERCENT.getIndex());

        return style;
    }

    private CellStyle getCellStyle(HSSFWorkbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setColor(IndexedColors.GREY_80_PERCENT.getIndex());
        font.setFontHeightInPoints((short) 14);

        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setWrapText(true);
        style.setVerticalAlignment(VerticalAlignment.CENTER);

        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);

        return style;
    }

}
