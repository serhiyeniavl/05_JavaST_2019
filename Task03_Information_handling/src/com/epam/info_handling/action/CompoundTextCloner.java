package com.epam.info_handling.action;

import com.epam.info_handling.entity.TextComponent;
import com.epam.info_handling.entity.TextComponentImpl;

/**
 * Class for cloning text component. Called copy constructor of
 * {@link TextComponentImpl} class.
 */
public class CompoundTextCloner {

    /**
     * Cloning text component.
     * @param textComponent component for clone.
     * @return cloned object of component.
     */
    public TextComponent acquireClone(final TextComponent textComponent) {
        return new TextComponentImpl(textComponent);
    }
}
