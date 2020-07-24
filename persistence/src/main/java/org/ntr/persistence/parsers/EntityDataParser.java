package org.ntr.persistence.parsers;

import org.ntr.persistence.entities.relations.Entity;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;

public abstract class EntityDataParser<T extends Entity> implements DataParser<T> {


    @Override
    public Pair<String, String> parse(T data) {
        StringBuilder nameAppender = new StringBuilder();
        StringBuilder valueAppender = new StringBuilder();
        Arrays.stream(data.getClass().getDeclaredFields()).forEach(field -> concat(field, nameAppender, valueAppender));
        Pair<String, String> pair = new Pair<>(nameAppender.toString(), valueAppender.toString());

        return pair;
    }

    protected abstract String doParse(T data);

    private void concat(Field field, StringBuilder nameAppender, StringBuilder valueAppender) {
        field.setAccessible(true);
        nameAppender.append(field.getName());
        if(field.getType().equals(String.class) || field.getType().equals(LocalDateTime.class) ||
                field.getType().equals(LocalDate.class) || field.getType().equals(LocalTime.class));
//            valueAppender.append(String.format("'%s'"), field.get())
    }

}
