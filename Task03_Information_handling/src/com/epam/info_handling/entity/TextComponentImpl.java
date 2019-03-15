package com.epam.info_handling.entity;

import com.epam.info_handling.exception.InvalidIndexException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TextComponentImpl implements TextComponent {
    private String name;
    private String data;
    private List<TextComponent> components = new ArrayList<>();

    public TextComponentImpl() {

    }

    public TextComponentImpl(String name, String data) {
        this.name = name;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void add(TextComponent... components) {
        this.components.addAll(Arrays.asList(components));
    }

    public void add(TextComponent component) {
        components.add(component);
    }

    public void remove(TextComponent component) {
        components.remove(component);
    }

    public void remove(TextComponent... components) {
        this.components.removeAll(Arrays.asList(components));
    }

    public void acquireComponent(final int level) {
        try {
            for (TextComponent component : this.getChild(0).getChild(0).getChild(1).getChild(1).components) {
                System.out.println(component.getData());
            }
        } catch (InvalidIndexException e) {
            e.printStackTrace();
        }
    }

    public TextComponentImpl getChild(final int index)
            throws InvalidIndexException {
        if (index < 0 || index >= components.size()) {
            throw new InvalidIndexException("Index out of bound.");
        }
        return (TextComponentImpl) components.get(index);
    }

}
