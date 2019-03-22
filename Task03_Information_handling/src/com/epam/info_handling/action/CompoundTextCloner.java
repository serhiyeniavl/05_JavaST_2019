package com.epam.info_handling.action;

import com.epam.info_handling.entity.TextComponent;
import com.epam.info_handling.entity.TextComponentImpl;

public class CompoundTextCloner {

    public TextComponent acquireClone(final TextComponent textComponent) {
        return new TextComponentImpl(textComponent);
    }
}
