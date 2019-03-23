package com.epam.info_handling.action;

import com.epam.info_handling.entity.TextComponent;
import com.epam.info_handling.exception.UnsupportedMethodException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class TextCollector {
    private static final Logger LOGGER = LogManager.getLogger(
            TextCollector.class);

    public List<TextComponent> acquireSentences(
            final TextComponent textComponent) {
        try {
            List<TextComponent> sentences = new ArrayList<>();
            List<TextComponent> paragraphs = textComponent.getComponents();
            for (TextComponent sentence : paragraphs) {
                sentences.addAll(sentence.getComponents());
            }
            return sentences;
        } catch (UnsupportedMethodException e) {
            LOGGER.error(
                    "Error when try to get components from text.", e);
        }
        return new ArrayList<>();
    }

    public List<TextComponent> acquireLexemes(
            final TextComponent textComponent) {
        try {
            List<TextComponent> lexemes = new ArrayList<>();
            List<TextComponent> sentences = acquireSentences(textComponent);
            for (TextComponent lexeme : sentences) {
                lexemes.addAll(lexeme.getComponents());
            }
            return lexemes;
        } catch (UnsupportedMethodException e) {
            LOGGER.error(
                    "Error when try to get components from text.", e);
        }
        return new ArrayList<>();
    }
}

