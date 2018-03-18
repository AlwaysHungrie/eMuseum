package com.onlylemi.mapview;

import android.graphics.PointF;

import java.util.ArrayList;
import java.util.List;

/**
 * TestData
 *
 * @author: onlylemi
 */
public final class TestData {

    private TestData() {}

    public static List<PointF> getMarks() {
        List<PointF> marks = new ArrayList<>();
        marks.add(new PointF(415, 661));
        marks.add(new PointF(416, 343));
        marks.add(new PointF(226, 483));
        marks.add(new PointF(174, 299));
        marks.add(new PointF(464, 175));
        marks.add(new PointF(611, 358));
        marks.add(new PointF(818, 322));
        marks.add(new PointF(783, 431));
        marks.add(new PointF(961, 425));
        marks.add(new PointF(1090, 452));
        marks.add(new PointF(949, 162));
        marks.add(new PointF(1235, 448));
        marks.add(new PointF(1224, 717));
        return marks;
    }

    public static List<String> getMarksName() {
        List<String> marksName = new ArrayList<>();
        marksName.add("RECEPTION");
        marksName.add("KEY GALLERY");;
        marksName.add("COOMARASWAMY HALL");
        marksName.add("OFFICE");
        marksName.add(" PRE AND PROTO HISTORY GALLERY");
        marksName.add("SCULPTURE GALLERY");
        marksName.add("CAFETERIA");
        marksName.add("CAFETERIA");
        marksName.add("BIRD GALLERY");
        marksName.add("LIBRARY");
        marksName.add("MAMMAL GALLERY");
        marksName.add("FISH AND REPTILE GALLERY");
        marksName.add("OFFICE");
        return marksName;
    }
}
