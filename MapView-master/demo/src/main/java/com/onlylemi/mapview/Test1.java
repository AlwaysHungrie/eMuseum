package com.onlylemi.mapview;

import android.graphics.PointF;

import java.util.ArrayList;
import java.util.List;

/**
 * TestData
 *
 * @author: onlylemi
 */
public final class Test1 {

    private Test1() {}

    public static List<PointF> getMarks() {
        List<PointF> marks = new ArrayList<>();
        marks.add(new PointF(333, 632));
        marks.add(new PointF(321, 436));
        marks.add(new PointF(130, 422));
        marks.add(new PointF(138, 578));
        marks.add(new PointF(327, 129));
        marks.add(new PointF(130, 295));
        marks.add(new PointF(502, 565));
        marks.add(new PointF(504, 470));
        marks.add(new PointF(495,365));
        marks.add(new PointF(879,577));
        marks.add(new PointF(1049,417));
        marks.add(new PointF(844,347));
        marks.add(new PointF(1043, 242));
        marks.add(new PointF(1222, 606));
        marks.add(new PointF(877, 166));
        return marks;}

    public static List<String> getMarksName() {
        List<String> marksName = new ArrayList<>();
        marksName.add("Director's Office");
        marksName.add("First Floor Circle");;
        marksName.add("Miniature Painting Gallery");
        marksName.add("Office");
        marksName.add("Office");
        marksName.add("Krishna Gallery");
        marksName.add("Prints Gallery");
        marksName.add("Himalayan Art Gallery");
        marksName.add("Indian metalware and Decorative Art Gallery");
        marksName.add("The Museum shop");
        marksName.add("The coins Gallery");
        marksName.add("Khandalawala Gallery");
        marksName.add("Curator's Gallery");
        marksName.add("Premchand Roychand Gallery");
        marksName.add("Office");
        return marksName;
    }
}
