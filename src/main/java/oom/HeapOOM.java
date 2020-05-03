package oom;

import java.util.ArrayList;
import java.util.List;

public class HeapOOM {

    private static List<char[]> chars = new ArrayList<char[]>();
    public static void main(String[] args) {
        for (;;){
            chars.add(new char[1024]);
        }
    }
}
