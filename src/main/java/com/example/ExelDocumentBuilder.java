package com.example;

import com.example.domain.Person;
import com.smartxls.Table;
import com.smartxls.WorkBook;
import com.smartxls.enums.TableBuiltInStyles;

import java.util.List;

/**
 * Класс, который формирует exel файл
 */
public final class ExelDocumentBuilder {

    private final static String HEADER_TABLE = "Фамилия\tИмя\tОтчество\tПол\tДата рождения\tЦвет волос\tЦвет глаз\n\n";
    private final static String PATTERN = "%s\t%s\t%s\t%s\t%s\t%s\t%s\n";
    private WorkBook book;

    public ExelDocumentBuilder() {
        this.book = new WorkBook();
    }

    /**
     * Создает таблицу с переданными данными для xlsx файла
     * @param listPerson - список персон
     * @throws Exception
     */
    public void createTableForExel(List<Person> listPerson) throws Exception {

        StringBuilder builder = new StringBuilder(HEADER_TABLE);
        for (Person p : listPerson) {
            builder.append(String.format(
                    PATTERN,
                    p.getLastName(),
                    p.getFirstName(),
                    p.getMiddleName(),
                    p.getSex(),
                    p.getDate(),
                    p.getHairColor(),
                    p.getEyeColor()
            ));
        }

        this.book.setActiveCell(1, 1);
        this.book.setCSVString(builder.toString());
        Table table = this.book.addTable("DeptSales", 2, 1, 2 + listPerson.size(), 7, TableBuiltInStyles.TableStyleMedium2);

        this.book.setSheetName(0, "Справочник");

        for (int i = 1; i < 8; i++)
            this.book.setColWidthAutoSize(i, true);

        this.book.writeXLSX("result.xlsx");

    }

    public WorkBook getBook() {
        return this.book;
    }
}

