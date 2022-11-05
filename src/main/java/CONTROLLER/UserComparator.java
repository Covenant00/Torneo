/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLLER;

import java.util.Comparator;
import java.util.Map;

/**
 *
 * @author root
 */
public class UserComparator implements Comparator<Object> {

    Map<Integer, Integer> map;

    public UserComparator(Map<Integer, Integer> map) {
        this.map = map;
    }

    @Override
    public int compare(Object o1, Object o2) {
        if (map.get(o2) == map.get(o1)) {
            return 1;
        } else {
            return ((Integer) map.get(o1)).compareTo((Integer) map.get(o2));
        }
    }
}
