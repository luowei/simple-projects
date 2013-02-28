package com.xxxx.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: luowei
 * Date: 13-1-24
 * Time: 上午10:04
 * To change this template use File | Settings | File Templates.
 */
public class JSChart implements Serializable {

    Set<Element> datasets = new HashSet<Element>();
    Set<Option> optionset = new HashSet<Option>();



    public Set<Element> getDatasets() {
        return datasets;
    }

    public JSChart setDatasets(Set<Element> datasets) {
        this.datasets = datasets;
        return this;
    }

    public Set<Option> getOptionset() {
        return optionset;
    }

    public JSChart setOptionset(Set<Option> optionset) {
        this.optionset = optionset;
        return this;
    }

    public static class Element {
        String type;
        String id;
        List<Point> data = new ArrayList<Point>();

        public Element() {
        }

        public Element(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }

        public Element setType(String type) {
            this.type = type;
            return this;
        }

        public String getId() {
            return id;
        }

        public Element setId(String id) {
            this.id = id;
            return this;
        }

        public List<Point> getData() {
            return data;
        }

        public Element setData(List<Point> data) {
            this.data = data;
            return this;
        }
    }

    public static class Point{
        String unit;
        String value;

        public Point() {
        }

        public Point(String unit, String value) {
            this.unit=unit;
            this.value=value;
        }

        public String getUnit() {
            return unit;
        }

        public Point setUnit(String unit) {
            this.unit = unit;
            return this;
        }

        public String getValue() {
            return value;
        }

        public Point setValue(String value) {
            this.value = value;
            return this;
        }
    }

    public static class Option{
        String set;
        String value;

        public Option() {
        }

        public Option(String set, String value) {
            this.set = set;
            this.value = value;
        }

        public String getSet() {
            return set;
        }

        public Option setSet(String set) {
            this.set = set;
            return this;
        }

        public String getValue() {
            return value;
        }

        public Option setValue(String value) {
            this.value = value;
            return this;
        }
    }
}
