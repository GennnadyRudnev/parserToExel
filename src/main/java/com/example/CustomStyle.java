package com.example;

import com.smartxls.RangeStyle;
import com.smartxls.WorkBook;

import java.awt.*;

/**
 * Класс служит для создания стиля и применения его к данным
 */
public final class CustomStyle {

    private int startRow, startCol, endRow, endCol;
    private String startRange, eRange, hdrRange, colRange, ftrRange, bodyRange;
    private WorkBook workBook;
    private RangeStyle m_RangeStyle;

    public CustomStyle(int startRow, int startCol, int endRow, int endCol, WorkBook book) throws Exception {
        this.startRow = startRow;
        this.startCol = startCol;
        this.endRow = endRow;
        this.endCol = endCol;

        workBook = book;

        startRange = workBook.formatRCNr(this.startRow, this.startCol, false);
        eRange = workBook.formatRCNr(this.startRow, this.endCol, false);
        hdrRange = startRange + ":" + eRange;

        eRange = workBook.formatRCNr(this.endRow, this.startCol, false);
        colRange = startRange + ":" + eRange;

        startRange = workBook.formatRCNr(this.endRow, this.startCol, false);
        eRange = workBook.formatRCNr(this.endRow, this.endCol, false);
        ftrRange = startRange + ":" + eRange;

        startRange = workBook.formatRCNr(this.startRow + 1, this.startCol + 1, false);
        eRange = workBook.formatRCNr(this.endRow - 1, this.endCol, false);
        bodyRange = startRange + ":" + eRange;

        m_RangeStyle = workBook.getRangeStyle();
    }

    /**
     * Устанавливает стиль для таблицы с данными
     * @throws Exception
     */
    void setStyleTable()
            throws Exception {

        workBook.setSelection(ftrRange);
        m_RangeStyle.setBottomBorder(RangeStyle.BorderMedium);
        m_RangeStyle.setTopBorder(RangeStyle.BorderNone);
        m_RangeStyle.setLeftBorder(RangeStyle.BorderMedium);
        m_RangeStyle.setRightBorder(RangeStyle.BorderMedium);
        workBook.setRangeStyle(m_RangeStyle, startRow, startCol, endRow, endCol);

        workBook.setSelection(hdrRange);
        m_RangeStyle.setTopBorder(RangeStyle.BorderMedium);
        m_RangeStyle.setBottomBorder(RangeStyle.BorderMedium);
        m_RangeStyle.setLeftBorder(RangeStyle.BorderMedium);
        m_RangeStyle.setRightBorder(RangeStyle.BorderMedium);
        m_RangeStyle.setHorizontalAlignment(RangeStyle.HorizontalAlignmentCenter);
        m_RangeStyle.setVerticalAlignment(RangeStyle.VerticalAlignmentCenter);
        workBook.setRangeStyle(m_RangeStyle);

    }


    void fontToItalic(int rowStart, int colStart, int rowEnd, int colEnd) throws Exception {


        m_RangeStyle.setTopBorder(RangeStyle.BorderNone);
        m_RangeStyle.setBottomBorder(RangeStyle.BorderNone);

        m_RangeStyle.setHorizontalAlignment(RangeStyle.HorizontalAlignmentLeft);
        m_RangeStyle.setVerticalAlignment(RangeStyle.VerticalAlignmentCenter);
        m_RangeStyle.setFontItalic(true);

        workBook.setRangeStyle(m_RangeStyle, rowStart, colStart, rowEnd, colEnd);

        m_RangeStyle.setLeftBorder(RangeStyle.BorderNone);
        m_RangeStyle.setRightBorder(RangeStyle.BorderNone);

        m_RangeStyle.setHorizontalAlignment(RangeStyle.HorizontalAlignmentRight);
        m_RangeStyle.setVerticalAlignment(RangeStyle.VerticalAlignmentCenter);

        workBook.setRangeStyle(m_RangeStyle, rowStart, 5, rowEnd, 5);

        m_RangeStyle.setLeftBorder(RangeStyle.BorderMedium);
        m_RangeStyle.setRightBorder(RangeStyle.BorderMedium);

    }

     void setColorForRow(int pallete, RangeStyle rangeStyle) throws Exception {
        setSolidPattern(workBook.getPaletteEntry(pallete), Color.BLUE.getRGB(), rangeStyle);
        workBook.setRangeStyle(rangeStyle);

    }

    private void setSolidPattern(int fcolor, int bcolor, RangeStyle rangeStyle) {
        short nPattern;
        nPattern = 1;
        rangeStyle.setPattern(nPattern);
        rangeStyle.setPatternFG(fcolor);
        rangeStyle.setPatternBG(bcolor);
    }

    public WorkBook getWorkBook() {
        return this.workBook;
    }

    public RangeStyle getM_RangeStyle() {
        return m_RangeStyle;
    }
}
