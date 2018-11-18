package com.example;

import com.example.domain.Person;
import com.smartxls.RangeStyle;
import com.smartxls.WorkBook;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class App {
    public static void main(String[] args) {
        WorkBook book = null;

        try {
            if (args == null || (args.length == 0 || args.length > 1)) {
                System.out.println("Укажите путь к файлу или вы указали больше одного файла");
                return;
            }

            if (!Files.exists(Paths.get(args[0]))) {
                System.out.println("Файл не существует или не найден");
                return;
            }


            Path path = Paths.get(args[0]);

            List<Person> list = Parser.parse(path);

            ExelDocumentBuilder builder = new ExelDocumentBuilder();
            builder.createTableForExel(list);

            book = builder.getBook();

            CustomStyle style = new CustomStyle(1, 1, list.size() + 2, 7, book);

            style.setStyleTable();

            RangeStyle rangeStyle = book.getRangeStyle();
            rangeStyle.setFontSize(220);

            style.setColorForRow(14, rangeStyle);

            book.setRangeStyle(rangeStyle);

            style.getWorkBook().setRangeStyle(rangeStyle, 1, 1, 1, 7);

            LocalDate localDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

            for (int i = 0; i < list.size(); i++) {

                String date = list.get(i).getDate();
                LocalDate parsedDate = LocalDate.parse(date, formatter);

                long resultYears = ChronoUnit.YEARS.between(parsedDate, localDate);
                if (resultYears > 35) {

                }

                if (list.get(i)
                        .getSex().equals("жен.")) {
                    style.fontToItalic((3 + i), 1, 3 + i, 7);
                }

            }

            book = style.getWorkBook();
            book.writeXLSX("result.xlsx");


        } catch (IOException e) {
            System.out.println("Ошибка при чтении/записи файла\n");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Возникла не предвиденная ошибка, по пробуйте еще раз\n");
            e.printStackTrace();

        } finally {
            if (book != null) {
                book.dispose();
            }
        }

    }
}
