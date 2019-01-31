package art;

import java.util.LinkedList;

public class AsciiChar {
    private LinkedList<Character> characters;
    private int size;

    public AsciiChar() {
        size = 256;
        characters = new LinkedList<>();
        characters.add('#');
        characters.add('N');
        characters.add('F');
        characters.add('I');
        characters.add('*');
        characters.add('=');
        characters.add('-');
        characters.add('.');
        characters.add(' ');
    }

    Character getCharacter(int num) {
        if (num < size)
            return characters.get(num * characters.size() / size);
        else
            return ' ';
    }
}
