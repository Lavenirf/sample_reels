package com.core.utils;

import com.core.entity.Score;
import org.apache.poi.ss.usermodel.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtils {

    public static List<Score> parseScoreExcel(InputStream inputStream) throws IOException {
        List<Score> scores = new ArrayList<>();

        Workbook workbook = WorkbookFactory.create(inputStream);
        Sheet sheet = workbook.getSheetAt(0);

        // 跳过表头，从第二行开始解析数据
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                Cell studentNameCell = row.getCell(0);
                Cell studentNumCell = row.getCell(1);
                Cell scoreCell = row.getCell(2);
                // 不可为空
                if (studentNameCell != null && studentNumCell != null && scoreCell != null) {
                    // 这里根据实际情况解析每个单元格的数据，并构造 Score 对象
                    String studentName = studentNameCell.getStringCellValue();
                    Long studentNum = (long) studentNumCell.getNumericCellValue();
                    Long scoreValue = (long) scoreCell.getNumericCellValue();

                    Score score = new Score();
                    score.setStudentName(studentName);
                    score.setStudentNum(studentNum);
                    score.setScore(scoreValue);

                    scores.add(score);
                }
            }
        }

        workbook.close();
        return scores;
    }
}
