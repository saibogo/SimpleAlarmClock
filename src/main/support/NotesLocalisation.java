package main.support;

import java.util.ArrayList;
import java.util.List;

public class NotesLocalisation {

    private static List<String> notesRU;
    private static List<String> octavesRU;
    private static List<String> notesENG;
    private static List<String> octavesEng;
    static {
        notesRU = new ArrayList<>();
        notesRU.add("До");
        notesRU.add("До диез");
        notesRU.add("Ре");
        notesRU.add("Ре диез");
        notesRU.add("Ми");
        notesRU.add("Фа");
        notesRU.add("Фа диез");
        notesRU.add("Соль");
        notesRU.add("Соль диез");
        notesRU.add("Ля");
        notesRU.add("Ля диез");
        notesRU.add("Си");

        notesENG = new ArrayList<>();
        notesENG.add("C");
        notesENG.add("C#");
        notesENG.add("D");
        notesENG.add("D#");
        notesENG.add("E");
        notesENG.add("F");
        notesENG.add("F#");
        notesENG.add("G");
        notesENG.add("G#");
        notesENG.add("A");
        notesENG.add("A#");
        notesENG.add("B");

        octavesRU = new ArrayList<>();
        octavesRU.add("Октава -1");
        octavesRU.add("Субконтроктава");
        octavesRU.add("Контроктава");
        octavesRU.add("Большая октава");
        octavesRU.add("Малая октава");
        octavesRU.add("Первая октава");
        octavesRU.add("Вторая октава");
        octavesRU.add("Третья октава");
        octavesRU.add("Четвертая октава");
        octavesRU.add("Пятая октава");
        octavesRU.add("Высшая октава");

        octavesEng = new ArrayList<>();
        octavesEng.add("Octave -1");
        octavesEng.add("Sub-contra octave");
        octavesEng.add("Contra octave");
        octavesEng.add("Big octave");
        octavesEng.add("Small octave");
        octavesEng.add("One-line octave");
        octavesEng.add("Two-line octave");
        octavesEng.add("Three-line octave");
        octavesEng.add("Four-line octave");
        octavesEng.add("Five-line octave");
        octavesEng.add("Top-line octave");

    }

    public static String getNoteRu(int noteCode) {
        return notesRU.get(noteCode % notesRU.size()) + " " + octavesRU.get(noteCode / notesRU.size());
    }

    public static String getNoteEng(int noteCode) {
        return notesENG.get(noteCode % notesENG.size()) + " " + octavesEng.get(noteCode / notesENG.size());
    }
}
