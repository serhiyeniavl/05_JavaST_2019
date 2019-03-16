package com.epam.info_handling.entity;

import com.epam.info_handling.constant.TextLevel;
import com.epam.info_handling.exception.InvalidIndexException;
import com.epam.info_handling.exception.UnsupportedMethodException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TextComponentImpl implements TextComponent {
    private static final Logger LOGGER
            = LogManager.getLogger(TextComponentImpl.class);

    private String name;
    private String data;
    private List<TextComponent> components = new ArrayList<>();

    public TextComponentImpl() {

    }

    public TextComponentImpl(final String componentName,
                             final String componentData) {
        this.name = componentName;
        this.data = componentData;
    }

    public String getName() {
        return name;
    }

    public String getData() {
        return data;
    }


    public void add(final TextComponent... textComponents) {
        this.components.addAll(Arrays.asList(textComponents));
    }

    public void add(final TextComponent component) {
        components.add(component);
    }

    public void remove(final TextComponent component) {
        components.remove(component);
    }

    public void remove(final TextComponent... textComponents) {
        this.components.removeAll(Arrays.asList(textComponents));
    }

    public List<String> acquireComponent(final TextLevel level) {
        List<String> requiredComponents = new ArrayList<>();
//        TextComponentImpl prevComponent = this;
//        for (int i = 1; i <= level.getValue(); i++) {
//            try {
//                TextComponentImpl currentComponent = prevComponent.getChild(0);
//                prevComponent = currentComponent;
//            } catch (InvalidIndexException e) {
//                LOGGER.error("Invalid index. Out of bound.", e);
////            } catch (UnsupportedMethodException e) {
////                LOGGER.error("Invalid method was called.", e);
////            }
//            }
//        }
//
//        for (TextComponent component : prevComponent.components) {
//            requiredComponents.add(component.getData());
//        }
        try {
            System.out.println(this.getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getChild(0).getName());
        } catch (InvalidIndexException e) {
            e.printStackTrace();
        } catch (UnsupportedMethodException e) {
            e.printStackTrace();
        }

        return requiredComponents;
    }

    public TextComponent getChild(final int index)
            throws InvalidIndexException {
        if (index < 0 || index >= components.size()) {
            throw new InvalidIndexException("Index out of bound.");
        }
        return components.get(index);
    }

}
